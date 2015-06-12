package com.common.swing.view.component.table.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.view.bean.RowBean;
import com.common.util.business.tool.ReflectUtil;
import com.common.util.business.tool.collection.ArrayUtil;
import com.common.util.business.tool.collection.CollectionUtil;

/**
 * Define un modelo de una tabla de base.
 * 
 * @since 23/04/2014
 * @author Guillermo Mazzali
 * @version 1.1
 * 
 * @param <B>
 *            La clase de beans que vamos a tener dentro de este modelo.
 */
public abstract class BaseTableModel<B extends RowBean> extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(BaseTableModel.class);

	/**
	 * El indice no encontrado.
	 */
	private static final int INDEX_NOT_FOUND = -1;

	/**
	 * La colección de beans que vamos a tener dentro de la tabla.
	 */
	protected List<B> rowBeans;
	/**
	 * Los identificadores de las columnas y las que van a ser editables.
	 */
	protected List<String> visibleProperties;
	/**
	 * El nombre de las columnas.
	 */
	protected Map<String, String> visiblePropertiesNames;
	/**
	 * Los tipos de parámetros.
	 */
	protected Map<String, Class<?>> classProperties;
	/**
	 * Los getters para las propiedades.
	 */
	protected Map<String, Method> getterProperties;
	/**
	 * Los setters para las propiedades editables.
	 */
	protected Map<String, Method> setterProperties;

	/**
	 * Constructor de un modelo de una tabla de base.
	 * 
	 * @param visibleProperties
	 *            Las propiedades que vamos a desplegar en las columnas de la tabla.
	 * @param editableProperties
	 *            Las propiedades que vamos a poder editar dentro de la tabla.
	 * @param visiblePropertiesName
	 *            Los nombre de las columnas, discriminados por propiedades.
	 */
	public BaseTableModel(String[] visibleProperties, String[] editableProperties, Map<String, String> visiblePropertiesName) {
		Class<?> entityClass = null;
		try {
			entityClass = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception ex) {
			LOGGER.error("The generic parameter of this class cannot be empty", ex);
			throw new SwingException(ex);
		}
		this.rowBeans = new ArrayList<B>();
		this.visibleProperties = new ArrayList<String>();
		this.classProperties = new HashMap<String, Class<?>>();
		this.getterProperties = new HashMap<String, Method>();
		this.setterProperties = new HashMap<String, Method>();
		try {
			Map<String, Field> fields = ReflectUtil.getAllDeclaratedFields(entityClass);
			for (String property : visibleProperties) {
				Field field = fields.get(property);
				this.visibleProperties.add(property);
				this.classProperties.put(property, field.getType());
				this.getterProperties.put(property, ReflectUtil.getGetter(entityClass, property));
			}
			if (ArrayUtil.isNotEmpty(editableProperties)) {
				for (String editableProperty : editableProperties) {
					this.setterProperties.put(editableProperty,
							ReflectUtil.getSetter(entityClass, editableProperty, this.classProperties.get(editableProperty)));
				}
			}
		} catch (Exception ex) {
			LOGGER.error("The visible properties cannot be initialize", ex);
			throw new SwingException(ex);
		}
		this.visiblePropertiesNames = visiblePropertiesName;
	}

	@Override
	public int getRowCount() {
		return this.rowBeans.size();
	}

	@Override
	public int getColumnCount() {
		return this.visibleProperties.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex < this.getColumnCount()) {
			String property = this.visibleProperties.get(columnIndex);
			LOGGER.debug("Get column name for property: " + property);
			if (this.visiblePropertiesNames.containsKey(property)) {
				return this.visiblePropertiesNames.get(property);
			} else {
				return property;
			}
		}
		LOGGER.debug("Get column name overflow");
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex < this.getColumnCount()) {
			String property = this.visibleProperties.get(columnIndex);
			LOGGER.debug("Get column class for property: " + property);
			return this.classProperties.get(property);
		}
		LOGGER.debug("Get column class overflow");
		return Object.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return this.setterProperties.keySet().contains(this.getColumnProperty(columnIndex));
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		B bean = this.getRow(rowIndex);
		if (bean != null) {
			if (columnIndex >= 0 && columnIndex < this.getColumnCount()) {
				String property = this.visibleProperties.get(columnIndex);
				try {
					return this.getterProperties.get(property).invoke(bean);
				} catch (Exception e) {
					LOGGER.warn("Fail get value", e);
				}
			}
		}
		return null;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		B bean = this.getRow(rowIndex);
		if (bean != null) {
			if (columnIndex >= 0 && columnIndex < this.getColumnCount()) {
				String property = this.visibleProperties.get(columnIndex);
				try {
					this.setterProperties.get(property).invoke(bean, value);
				} catch (Exception e) {
					LOGGER.warn("Fail set value", e);
				}
			}
		}
		this.fireTableRowsUpdated(rowIndex, rowIndex);
	}

	/**
	 * Permite vaciar el contenido del modelo.
	 */
	public void clear() {
		int size = this.getRowCount();
		if (size > 0) {
			this.rowBeans.clear();
			this.fireTableRowsDeleted(0, size - 1);
		}
	}

	/**
	 * Dado el indice de la columna devuelve la propiedad del bean.
	 * 
	 * @param columnIndex
	 *            El indice de la columna, comenzando de 0.
	 * @return El nombre de la propiedad que corresponde con el indice, en caso de que sea superior a la cantidad de columnas, retornamos
	 *         <code>null</code>.
	 */
	public String getColumnProperty(int columnIndex) {
		if (columnIndex < this.getColumnCount()) {
			return this.visibleProperties.get(columnIndex);
		}
		return null;
	}

	/**
	 * Permite agregar un bean al modelo de la tabla.
	 * 
	 * @param rowBean
	 *            El bean que vamos a agregar al modelo. En caso que este valor sea <code>null</code>, no se va a insertar a la misma.
	 */
	public void addRow(B rowBean) {
		if (rowBean != null) {
			int size = this.getRowCount();
			this.rowBeans.add(rowBean);
			this.fireTableRowsInserted(size, size);
		}
	}

	/**
	 * Permite agregar una lista de beans al modelo de la tabla.
	 * 
	 * @param rowBeans
	 *            El listado de los beans que vamos a agregar al modelo. En caso que este valor sea <code>null</code> o este vacia la lista, no se va
	 *            a insertar nada.
	 */
	public void addRows(Collection<B> rowBeans) {
		if (CollectionUtil.isNotEmpty(rowBeans)) {
			int from = this.getRowCount();
			for (B rowBean : rowBeans) {
				this.rowBeans.add(rowBean);
			}
			int to = this.getRowCount();
			this.fireTableRowsInserted(from, to);
		}
	}

	/**
	 * Permite recuperar un bean dado el índice real del modelo.
	 * 
	 * @param realIndex
	 *            El indice real del bean dentro del modelo.
	 * @return El bean que corresponde al indice, en caso de se un indice fuera rango de valores, retornamos <code>null</code>.
	 */
	public B getRow(int realIndex) {
		if (realIndex >= 0 && realIndex < this.getRowCount()) {
			return this.rowBeans.get(realIndex);
		}
		return null;
	}

	/**
	 * Permite quitar un bean del modelo de la tabla.
	 * 
	 * @param rowIndex
	 *            El indice del bean que queremos eliminar.
	 */
	public void removeRow(int rowIndex) {
		if (rowIndex >= 0 && rowIndex < this.getRowCount()) {
			this.rowBeans.remove(rowIndex);
			this.fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}

	/**
	 * Permite quitar una linea dentro de la tabla.
	 * 
	 * @param rowBean
	 *            La fila dentro de la tabla quer vamos a quitar.
	 */
	public void removeRow(B rowBean) {
		int index = this.getIndex(rowBean);
		if (index != INDEX_NOT_FOUND) {
			this.removeRow(index);
		}
	}

	/**
	 * Permite saber si un bean se encuentra dentro del modelo o no.
	 * 
	 * @param rowBean
	 *            El rowBean que vamos a ver si se encuentra dentro del modelo.
	 * @return <code>true</code> en caso de que el bean se encuentre dentro del modelo, en caso de que el bean sea <code>null</code> o no se
	 *         encuentre, retornamos <code>false</code>.
	 */
	public boolean contains(B rowBean) {
		return this.getIndex(rowBean) != INDEX_NOT_FOUND;
	}

	/**
	 * Permite saber si el modelo se encuentra vacio.
	 * 
	 * @return <code>true</code> en caso de que el modelo se encuentre vacio, en caso que contenga al menos 1 elemento, retornamos <code>false</code>.
	 */
	public boolean isEmpty() {
		return this.rowBeans.isEmpty();
	}

	/**
	 * Permite recuperar el indice del modelo en el que se encuentra el bean que tenemos.
	 * 
	 * @param rowBean
	 *            El bean del que queremos saber el indice. En caso que sea <code>null</code> retornamos {@link BaseTableModel#INDEX_NOT_FOUND}.
	 * @return El indice (comenzando desde 0) donde se encuentra el bean dentro del modelo, en caso de el bean sea <code>null</code> o no se
	 *         encuentre, retornamos -1.
	 */
	public int getIndex(B rowBean) {
		if (rowBean != null) {
			int index = 0;
			for (B bean : this.rowBeans) {
				if (bean.equals(rowBean)) {
					return index;
				}
				index++;
			}
		}
		return INDEX_NOT_FOUND;
	}
}