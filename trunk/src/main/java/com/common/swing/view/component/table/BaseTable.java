package com.common.swing.view.component.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.table.formatter.CellFormatter;
import com.common.swing.view.component.table.formatter.impl.StringCellFormatter;
import com.common.swing.view.component.table.model.BaseTableModel;
import com.common.swing.view.component.table.renderer.HeaderRenderer;
import com.common.swing.view.component.table.sorter.BaseRowSorter;
import com.common.util.business.tool.VerifierUtil;
import com.common.util.business.tool.collection.CollectionUtil;

/**
 * Representa una tabla para desplegarse en formularios hechos con SWING y que contiene dentro de si misma el listado de las entidades que vamos a
 * desplegar dentro de la misma.
 * 
 * @since 23/04/2014
 * @author Guillermo Mazzali
 * @version 1.1
 * 
 * @param <B>
 *            La clase de las entidades que vamos a cargar dentro de la tabla.
 */
public abstract class BaseTable<B extends RowBean> extends JTable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(BaseTable.class);

	/**
	 * El valor booleano que nos indica si va a seleccionarse o no la primer fila cuando se carguen datos.
	 */
	private boolean firstSelected;
	/**
	 * Las propiedades que vamos a mostrar en al tabla.
	 */
	protected String[] visibleProperties;
	/**
	 * Las columnas de la tabla.
	 */
	protected Map<String, TableColumn> propertiesColumn;
	/**
	 * El modelo de la tabla.
	 */
	protected BaseTableModel<B> tableModel;
	/**
	 * El ordenador de la tabla.
	 */
	protected TableRowSorter<BaseTableModel<B>> rowSorter;
	/**
	 * Los objectos de exclusion de fila y de tabla.
	 */
	private final Object rowMutex = new Object();
	private final Object tableMutex = new Object();

	/**
	 * Constructor de una tabla que recibe las propiedades de las entidades que vamos a listar.
	 * 
	 * @param visibleProperties
	 *            El listado de las propiedades que vamos a listar.
	 */
	public BaseTable(String[] visibleProperties) {
		this(visibleProperties, null);
	}

	/**
	 * Constructor de una tabla que recibe las propiedades de las entidades que vamos a listar y los nombres que van a recibir cada una de ellas.
	 * 
	 * @param visibleProperties
	 *            El listado de las propiedades que vamos a listar.
	 * @param visiblePropertiesName
	 *            El mapa de los nombres de las propiedades que vamos a listar.
	 */
	public BaseTable(String[] visibleProperties, Map<String, String> visiblePropertiesName) {
		this(visibleProperties, visiblePropertiesName, null);
	}

	/**
	 * Constructor de una tabla que recibe las propiedades de las entidades que vamos a listar, los nombres que van a recibir cada una de ellas y el
	 * ancho de cada una.
	 * 
	 * @param visibleProperties
	 *            El listado de las propiedades que vamos a listar.
	 * @param editableProperties
	 *            El listado de las propiedades que vamos a poder editar dentro de la tabla.
	 * @param visiblePropertiesName
	 *            El mapa de los nombres de las propiedades que vamos a listar.
	 * @param visiblePropertiesWidth
	 *            Los anchos que va a tener cada una de las columnas.
	 */
	public BaseTable(String[] visibleProperties, Map<String, String> visiblePropertiesName, Map<String, Integer> visiblePropertiesWidth) {
		this(visibleProperties, new String[] {}, visiblePropertiesName, visiblePropertiesWidth, true);
	}

	/**
	 * Constructor de una tabla que recibe las propiedades de las entidades que vamos a listar, los nombres que van a recibir cada una de ellas y el
	 * ancho de cada una.
	 * 
	 * @param visibleProperties
	 *            El listado de las propiedades que vamos a listar.
	 * @param editableProperties
	 *            El listado de las propiedades que vamos a poder editar dentro de la tabla.
	 * @param editableProperties
	 *            El listado de las propiedades que vamos a poder editar dentro de la tabla.
	 * @param visiblePropertiesName
	 *            El mapa de los nombres de las propiedades que vamos a listar.
	 * @param visiblePropertiesWidth
	 *            Los anchos que va a tener cada una de las columnas.
	 */
	public BaseTable(String[] visibleProperties, String[] editableProperties, Map<String, String> visiblePropertiesName,
			Map<String, Integer> visiblePropertiesWidth) {
		this(visibleProperties, editableProperties, visiblePropertiesName, visiblePropertiesWidth, true);
	}

	/**
	 * Constructor de una tabla que recibe las propiedades de las entidades que vamos a listar, los nombres que van a recibir cada una de ellas y el
	 * ancho de cada una, como asi tambien si va a seleccionarse la primer fila o no cuando se carguen datos.
	 * 
	 * @param visibleProperties
	 *            El listado de las propiedades que vamos a listar.
	 * @param editableProperties
	 *            El listado de las propiedades que vamos a poder editar dentro de la tabla.
	 * @param visiblePropertiesName
	 *            El mapa de los nombres de las propiedades que vamos a listar.
	 * @param visiblePropertiesWidth
	 *            El mapa de los anchos que va a tener cada una de las columnas.
	 * @param firstSelected
	 *            El valor booleano que nos va a indicar si va a seleccionarse o no la primer fila cuando se carguen datos.
	 */
	public BaseTable(String[] visibleProperties, String[] editableProperties, Map<String, String> visiblePropertiesName,
			Map<String, Integer> visiblePropertiesWidth, boolean firstSelected) {
		this(new ArrayList<B>(), visibleProperties, editableProperties, visiblePropertiesName, visiblePropertiesWidth, firstSelected);
	}

	/**
	 * Constructor de una tabla que recibe las propiedades de las entidades que vamos a listar, los nombres que van a recibir cada una de ellas y el
	 * ancho de cada una.
	 * 
	 * @param entities
	 *            Las entidades que vamos a cargar dentro de la tabla.
	 * @param visibleProperties
	 *            El listado de las propiedades que vamos a listar.
	 * @param editableProperties
	 *            El listado de las propiedades que vamos a poder editar dentro de la tabla.
	 * @param visiblePropertiesName
	 *            El mapa de los nombres de las propiedades que vamos a listar.
	 * @param visiblePropertiesWidth
	 *            El mapa de los anchos que va a tener cada una de las columnas.
	 * @param firstSelected
	 *            El valor booleano que nos va a indicar si va a seleccionarse o no la primer fila cuando se carguen datos.
	 */
	public BaseTable(List<B> entities, String[] visibleProperties, String[] editableProperties, Map<String, String> visiblePropertiesName,
			Map<String, Integer> visiblePropertiesWidth, boolean firstSelected) {
		this.firstSelected = firstSelected;
		this.init(visibleProperties, editableProperties, visiblePropertiesName, visiblePropertiesWidth);
		this.reloadData(entities);
	}

	/**
	 * Inicializa la tabla y sus componentes.
	 * 
	 * @param visibleProperties
	 *            El listado de las propiedades que vamos a listar.
	 * @param editableProperties
	 *            El listado de las propiedades que vamos a poder editar dentro de la tabla.
	 * @param visiblePropertiesName
	 *            El mapa de los nombres de las propiedades que vamos a listar.
	 * @param visiblePropertiesWidth
	 *            El mapa de los anchos que va a tener cada una de las columnas.
	 */
	protected void init(String[] visibleProperties, String[] editableProperties, Map<String, String> visiblePropertiesName,
			Map<String, Integer> visiblePropertiesWidth) {
		if (visibleProperties == null || visibleProperties.length == 0) {
			LOGGER.warn("The list of visibles properties must least have one");
			throw new SwingException("The list of visibles properties must least have one", "swing.component.basetable.error.properties.null");
		}
		this.visibleProperties = visibleProperties;

		// Creamos el modelo de la tabla.
		this.tableModel = this.createModel(visibleProperties, editableProperties, visiblePropertiesName);
		super.setModel(this.tableModel);

		// Creamos las columnas para la tabla.
		this.setColumns(visiblePropertiesName, visiblePropertiesWidth);

		// Creamos el ordenador de las filas por omisión.
		this.rowSorter = new BaseRowSorter<BaseTableModel<B>>(this.tableModel);
		this.setRowSorter(this.rowSorter);
	}

	@Override
	public void setModel(TableModel tableModel) {
		// No se le carga ningun modelo a la tabla, ya que se lo creamos nosotros al mismo.
	}

	/**
	 * Se encarga de crear el modelo de la tabla.
	 * 
	 * @param visibleProperties
	 *            Las propiedades que vamos a mostrar en al tabla.
	 * @param editableProperties
	 *            El listado de las propiedades que vamos a poder editar dentro de la tabla.
	 * @param visiblePropertiesName
	 *            Los nombres que van a recibir cada una de esas columnas.
	 * @return El modelo que vamos a colocar dentro de la tabla.
	 */
	protected abstract BaseTableModel<B> createModel(String[] visibleProperties, String[] editableProperties,
			Map<String, String> visiblePropertiesName);

	/**
	 * Crea las columnas que vamos a desplegar dentro de la tabla.
	 * 
	 * @param visiblePropertiesName
	 *            Los nombres que van a recibir cada una de esas columnas.
	 * @param visiblePropertiesWidth
	 *            Los anchos que van a recibir cada una de esas columnas.
	 */
	protected void setColumns(Map<String, String> visiblePropertiesName, Map<String, Integer> visiblePropertiesWidth) {
		// Recuperamos las columnas.
		this.propertiesColumn = new HashMap<String, TableColumn>();
		for (int i = 0; i < this.visibleProperties.length; i++) {
			String property = this.visibleProperties[i];
			this.propertiesColumn.put(property, this.getColumnModel().getColumn(i));
		}

		// Cargamos los anchos de las columnas.
		if (visiblePropertiesWidth != null) {
			for (String property : this.visibleProperties) {
				// Obtenemos la columna y el ancho correspondiente.
				TableColumn column = this.propertiesColumn.get(property);
				Integer width = visiblePropertiesWidth.get(property);

				// Si tenemos la columna, le seteamos el ancho.
				if (column != null) {
					if (width != null) {
						column.setPreferredWidth(width);
					} else {
						column.setPreferredWidth(0);
					}
				}
			}

			// Cargamos el formateador por default para Strings.
			for (String property : this.visibleProperties) {
				this.addCellFormatter(property, new StringCellFormatter());
			}
		}
	}

	/**
	 * Permite cargarle a la tabla un render para cada una de las cabeceras de los atributos.
	 * 
	 * @param property
	 *            La propiedad a la que vamos a cargarle el render de la cabecera.
	 * @param renderer
	 *            El render de la cabecera propiamente dicha.
	 */
	public void addHeaderRenderer(String property, HeaderRenderer renderer) {
		VerifierUtil.checkNotNull(renderer, "The header renderer for the property '" + property + "' cannot be null");
		if (this.propertiesColumn.containsKey(property)) {
			this.propertiesColumn.get(property).setHeaderRenderer(renderer);
		} else {
			LOGGER.warn("Don't exist the header for the property '" + property + "'");
		}
	}

	/**
	 * Permite cargarle a la tabla un formateador para cada una de las columnas de los atributos.
	 * 
	 * @param property
	 *            La propiedad a la que vamos a cargarle el formateador de la columna.
	 * @param cellFormatter
	 *            El formateador de la columna propiamente dicha.
	 */
	public void addCellFormatter(String property, CellFormatter cellFormatter) {
		VerifierUtil.checkNotNull(cellFormatter, "The column formatter for the property '" + property + "' cannot be null");
		if (this.propertiesColumn.containsKey(property)) {
			this.propertiesColumn.get(property).setCellRenderer(cellFormatter);
			this.propertiesColumn.get(property).setCellEditor(cellFormatter);
		} else {
			LOGGER.warn("Don't exist the column for the property '" + property + "'");
		}
	}

	/**
	 * Permite saber si la tabla se encuentra vacía.
	 * 
	 * @return <code>true</code> en caso de que la tabla no contenga ninguna entidad, en caso contrario, retorna <code>false</code>.
	 */
	public boolean isEmpty() {
		return this.tableModel.isEmpty();
	}

	/**
	 * Permite vaciar el contenido de la tabla.
	 */
	public void clearTable() {
		synchronized (tableMutex) {
			synchronized (rowMutex) {
				this.tableModel.clear();
			}
		}
	}

	/**
	 * Permite cargar dentro de esta tabla las entidades que recibimos dentro de una lista. Esta carga se realiza sobre una tabla completamente vacia.
	 * 
	 * @param entities
	 *            La lista de entidades que vamos a almacenar en la tabla.
	 */
	protected void reloadData(final Collection<B> entities) {
		new Thread() {
			@Override
			public void run() {
				setEnabled(false);
				clearTable();
				synchronized (tableMutex) {
					for (B entity : entities) {
						synchronized (rowMutex) {
							tableModel.addRow(entity);
						}
					}
					if (firstSelected && !tableModel.isEmpty()) {
						setRowSelectionInterval(0, 0);
					}
				}
				setEnabled(true);
			}
		}.start();
	}

	/**
	 * Permite cargar un listado de entidades dentro de la tabla. Al cargar este nuevo listado, se borra el contenido de la tabla que se encuentra
	 * actualmente y se carga la misma con este listado. En caso de que la lista este vacía o sea <code>null</code> se va a limpiar la tabla.
	 * 
	 * @param entities
	 *            El listado de las entidades. Puede estar vacía o ser <code>null</code>.
	 */
	public void setValues(Collection<B> entities) {
		if (CollectionUtil.isNotEmpty(entities)) {
			this.reloadData(entities);
		} else {
			this.clearTable();
		}
	}

	/**
	 * Permite agregar una entidad a las que ya tenemos cargadas dentro de la tabla.
	 * 
	 * @param entity
	 *            La entidad que vamos a agregar a las que ya tenemos-
	 */
	public void addValue(final B entity) {
		new Thread() {
			@Override
			public void run() {
				synchronized (rowMutex) {
					tableModel.addRow(entity);
				}
			}
		}.start();
	}

	/**
	 * Permite quitar una entidad de las que tenemos cargadas dentro de la tabla.
	 * 
	 * @param entity
	 *            La entidad que queremos quitar de la tabla.
	 */
	public void removeValue(final B entity) {
		new Thread() {
			@Override
			public void run() {
				synchronized (rowMutex) {
					tableModel.removeRow(tableModel.getIndex(entity));
				}
			}
		}.start();
	}

	/**
	 * Se encarga de consultar si la tabla contiene dentro de si una entidad dada.
	 * 
	 * @param entity
	 *            La entidad sobre la que vamos a preguntar si esta cargada dentro de la tabla.
	 * @return <i>true</i> en caso de que la entidad se encuentra cargada dentro de la tabla, en caso contrario retorna <i>false</i>.
	 */
	public boolean containValue(B entity) {
		return this.tableModel.contains(entity);
	}

	/**
	 * Permite recuperar la entidad que tenemos seleccionada dentro de la tabla, en caso de que no haya ninguna, retornamos un valor nulo.
	 * 
	 * @return La entidad que se encuentra seleccionada en la tabla, en caso de que no se encuentre ninguna seleccionada, retornamos un valor nulo.
	 */
	public B getValue() {
		synchronized (this.rowMutex) {
			// Otenemos el indice seleccinado.
			Integer index = this.getSelectedRow();
			if (index != -1) {
				int realIndex = this.convertRowIndexToModel(index);
				return tableModel.getRow(realIndex);
			}
			return null;
		}
	}

	/**
	 * Permite recuperar un listado de las entidades que tenemos seleccionadas dentro de la tabla, en caso de que no haya ninguna, retornamos una
	 * lista vacia.
	 * 
	 * @return El listado de las entidades que se encuentran seleccionadas en la tabla, en caso de que no se encuentre ninguna seleccionada,
	 *         retornamos una lista vacia.
	 */
	public Collection<B> getValues() {
		synchronized (this.tableMutex) {
			// Obtenemos los indices seleccionados.
			int[] indexs = this.getSelectedRows();

			// Convertimos esos indices.
			int[] realIndexs = new int[indexs.length];
			if (!this.isEmpty()) {
				for (int i = 0; i < indexs.length; i++) {
					realIndexs[i] = this.convertRowIndexToModel(indexs[i]);
				}
			}
			// Otenemos las entidades de esos indices.
			Collection<B> selectedValues = new ArrayList<B>();
			for (Integer realIndex : realIndexs) {
				selectedValues.add(this.tableModel.getRow(realIndex));
			}
			// Recuperamos esos objetos y los devolvemos.
			return selectedValues;
		}
	}
}