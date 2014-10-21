package com.common.swing.view.component.table;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.view.component.table.model.ReadOnlyTableModel;
import com.common.swing.view.component.table.renderer.ColumnTableRenderer;
import com.common.swing.view.component.table.renderer.HeaderTableRenderer;
import com.common.util.business.tool.collection.CollectionUtil;

/**
 * Representa una tabla para desplegarse en formularios hechos con SWING y que contiene dentro de si misma el listado de las entidades que vamos a
 * desplegar dentro de la misma.
 * 
 * @since 23/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de las entidades que vamos a cargar dentro de la tabla.
 */
@SuppressWarnings("unchecked")
public abstract class BaseTable<E extends Serializable> extends JTable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(BaseTable.class);

	/**
	 * La clase de entidades que vamos a tener dentro de la tabla y el conjunto de getters de las propiedades que vamos a mostrar.
	 */
	protected Class<E> entityClass;
	/**
	 * El mapa de las entidades que tenemos desplegadas en la tabla.
	 */
	protected Map<Integer, E> entityMap;
	/**
	 * El indice del proximo elemento que vamos a agregar a la tabla.
	 */
	protected Integer nextIndex = 0;
	/**
	 * Las propiedades que vamos a mostrar en al tabla.
	 */
	protected String[] visibleProperties;
	/*
	 * Los métodos getters que vamos a ocupar de las entidades.
	 */
	protected Map<String, Method> propertiesGetter;
	/**
	 * Las columnas de la tabla.
	 */
	protected Map<String, TableColumn> propertiesColumn;
	/**
	 * El modelo de la tabla.
	 */
	protected DefaultTableModel tableModel;
	/**
	 * Los objectos de exclusion de fila y de tabla.
	 */
	private final Object rowMutex = new Object();
	private final Object tableMutex = new Object();

	/**
	 * Constructor de una tabla que recibe las propiedades de las entidades que vamos a listar.
	 * 
	 * @param visibleProperty
	 *            El listado de las propiedades que vamos a listar.
	 */
	public BaseTable(String[] visibleProperty) {
		this(new ArrayList<E>(), visibleProperty, null, null);
	}

	/**
	 * Constructor de una tabla que recibe las propiedades de las entidades que vamos a listar y los nombres que van a recibir cada una de ellas.
	 * 
	 * @param visibleProperty
	 *            El listado de las propiedades que vamos a listar.
	 * @param visiblePropertyName
	 *            El mapa de los nombres de las propiedades que vamos a listar.
	 */
	public BaseTable(String[] visibleProperty, Map<String, String> visiblePropertyName) {
		this(new ArrayList<E>(), visibleProperty, visiblePropertyName, null);
	}

	/**
	 * Constructor de una tabla que recibe las propiedades de las entidades que vamos a listar, los nombres que van a recibir cada una de ellas y el
	 * ancho de cada una.
	 * 
	 * @param visibleProperty
	 *            El listado de las propiedades que vamos a listar.
	 * @param visiblePropertyName
	 *            El mapa de los nombres de las propiedades que vamos a listar.
	 * @param visiblePropertiesWidth
	 *            Los anchos que va a tener cada una de las columnas.
	 */
	public BaseTable(String[] visibleProperty, Map<String, String> visiblePropertyName, Map<String, Integer> visiblePropertiesWidth) {
		this(new ArrayList<E>(), visibleProperty, visiblePropertyName, visiblePropertiesWidth);
	}

	/**
	 * Constructor de una tabla que recibe las propiedades de las entidades que vamos a listar, los nombres que van a recibir cada una de ellas y el
	 * ancho de cada una.
	 * 
	 * @param entities
	 *            Las entidades que vamos a cargar dentro de la tabla.
	 * @param visibleProperty
	 *            El listado de las propiedades que vamos a listar.
	 * @param visiblePropertyName
	 *            El mapa de los nombres de las propiedades que vamos a listar.
	 * @param visiblePropertiesWidth
	 *            El mapa de los anchos que va a tener cada una de las columnas.
	 */
	public BaseTable(List<E> entities, String[] visibleProperties, Map<String, String> visiblePropertiesName,
			Map<String, Integer> visiblePropertiesWidth) {
		super();
		try {
			this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception ex) {
			log.error("The generic parameter of this class cannot be empty", ex);
			throw new SwingException("The generic parameter of this class cannot be empty", "swing.component.basetable.error.parameter.empty");
		}

		this.visibleProperties = visibleProperties;
		this.init(visiblePropertiesName, visiblePropertiesWidth);
		this.reloadData(entities);
	}

	@Override
	public void setModel(TableModel tableModel) {
		// No se le carga ningun modelo a la tabla, ya que se lo creamos nosotros al mismo.
	}

	/**
	 * Inicializa la tabla y sus componentes.
	 * 
	 * @param visiblePropertyName
	 *            El mapa de los nombres de las propiedades que vamos a listar.
	 * @param visiblePropertiesWidth
	 *            El mapa de los anchos que va a tener cada una de las columnas.
	 */
	protected void init(Map<String, String> visiblePropertiesName, Map<String, Integer> visiblePropertiesWidth) {
		// Creamos el modelo de la tabla.
		this.tableModel = this.createModel();

		// Verificamos que las propiedades no sean nulas o vacias.
		if (this.visibleProperties == null || this.visibleProperties.length == 0) {
			log.warn("The list of visibles properties must least have one");
			throw new SwingException("The list of visibles properties must least have one", "swing.component.basetable.error.properties.null");
		}

		// Creamos los getters de las propiedades de la entidad que vamos a mostrar.
		this.createPropertiesGetters();

		// Creamos las columnas para la tabla.
		this.createColumns(visiblePropertiesName, visiblePropertiesWidth);

		// Creamos el ordenador de las filas por omisión.
		this.setAutoCreateRowSorter(true);
	}

	/**
	 * Se encarga de crear el modelo de la tabla.
	 * 
	 * @return El modelo que vamos a colocar dentro de la tabla.
	 */
	protected DefaultTableModel createModel() {
		return new ReadOnlyTableModel();
	}

	/**
	 * Se encargada de crear los metodos getters de las propiedades de la entidad que estamos manejando dentro de la tabla.
	 */
	protected void createPropertiesGetters() {
		// Obtenemos los getters de las propiedades.
		this.propertiesGetter = new HashMap<String, Method>();
		for (String property : this.visibleProperties) {
			try {
				String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
				Method getter = this.entityClass.getDeclaredMethod(methodName);
				this.propertiesGetter.put(property, getter);
			} catch (Exception e) {
				log.warn("Not found the getter for the property '" + property + "' in the class '" + this.entityClass.getSimpleName() + "'");
				throw new SwingException("Not found the getter for the property '" + property + "' in the class '" + this.entityClass.getSimpleName()
						+ "'", "swing.component.basetable.getter.missing", property, this.entityClass.getSimpleName());
			}
		}
	}

	/**
	 * Crea las columnas que vamos a desplegar dentro de la tabla.
	 * 
	 * @param visiblePropertiesName
	 *            Los nombres que van a recibir cada una de esas columnas.
	 * @param visiblePropertiesWidth
	 *            Los anchos que van a recibir cada una de esas columnas.
	 */
	protected void createColumns(Map<String, String> visiblePropertiesName, Map<String, Integer> visiblePropertiesWidth) {
		// Cargamos los nombres de las columnas dentro de la tabla.
		for (String property : this.visibleProperties) {
			if (visiblePropertiesName != null && visiblePropertiesName.containsKey(property)) {
				this.tableModel.addColumn(visiblePropertiesName.get(property));
			} else {
				this.tableModel.addColumn(property);
			}
		}

		// Seteamos el modelo a la tabla.
		super.setModel(this.tableModel);

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
		}
	}

	/**
	 * Permite cargarle a la tabla un render para cada una de las columnas de los atributos.
	 * 
	 * @param property
	 *            La propiedad a la que vamos a cargarle el render de la columna.
	 * @param renderer
	 *            El render de la columna propiamente dicha.
	 */
	public void addColumnRenderer(String property, ColumnTableRenderer renderer) {
		// Si el renderer es nulo, lanzamos una excepción.
		if (renderer == null) {
			log.warn("The column renderer for the property '" + property + "' cannot be null");
			throw new SwingException("The column renderer for the property '" + property + "' cannot be null",
					"swing.component.basetable.error.columnrenderer.null", property);
		}

		// Cargamos el renderer en el mapa de las columnas.
		if (this.propertiesColumn.containsKey(property)) {
			this.propertiesColumn.get(property).setCellRenderer(renderer);
		} else {
			log.warn("Don't exist the column for the property '" + property + "'");
			throw new SwingException("Don't exist the column for the property '" + property + "'",
					"swing.component.basetable.error.columnrenderer.nonexist", property);
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
	public void addHeaderRenderer(String property, HeaderTableRenderer renderer) {
		// Si el renderer es nulo, lanzamos una excepción.
		if (renderer == null) {
			log.warn("The header renderer for the property '" + property + "' cannot be null");
			throw new SwingException("The header renderer for the property '" + property + "' cannot be null",
					"swing.component.basetable.error.headerrenderer.null", property);
		}

		// Cargamos el renderer en el mapa de las columnas.
		if (this.propertiesColumn.containsKey(property)) {
			this.propertiesColumn.get(property).setHeaderRenderer(renderer);
		} else {
			log.warn("Don't exist the header for the property '" + property + "'");
			throw new SwingException("Don't exist the header for the property '" + property + "'",
					"swing.component.basetable.error.headerrenderer.nonexist", property);
		}
	}

	/**
	 * Permite saber si la tabla se encuentra vacía.
	 * 
	 * @return <code>true</code> en caso de que la tabla no contenga ninguna entidad, en caso contrario, retorna <code>false</code>.
	 */
	public boolean isEmpty() {
		return this.entityMap.isEmpty();
	}

	/**
	 * Permite vaciar el contenido de la tabla.
	 */
	public void clearTable() {
		synchronized (tableMutex) {
			synchronized (rowMutex) {
				this.entityMap = new HashMap<Integer, E>();
				Integer size = this.tableModel.getDataVector().size();
				if (size > 0) {
					this.tableModel.getDataVector().clear();
					this.tableModel.fireTableRowsDeleted(0, size - 1);
				}
				this.nextIndex = 0;
			}
		}
	}

	/**
	 * Permite cargar dentro de esta tabla las entidades que recibimos dentro de una lista. Esta carga se realiza sobre una tabla completamente vacia.
	 * 
	 * @param entities
	 *            La lista de entidades que vamos a almacenar en la tabla.
	 */
	protected void reloadData(final Collection<E> entities) {
		new Thread() {
			public void run() {
				setEnabled(false);

				// Limpiamos la tabla.
				clearTable();

				synchronized (tableMutex) {
					// Tomamos las entidades y las cargamos fila por fila.
					for (E entity : entities) {
						// Creamos la fila y la cargamos.
						List<Object> row = new ArrayList<Object>();
						for (String property : visibleProperties) {
							try {
								row.add(propertiesGetter.get(property).invoke(entity));
							} catch (Exception e) {
							}
						}

						synchronized (rowMutex) {
							// Cargamos la fila al modelo de la tabla.
							tableModel.addRow(row.toArray());
							// Agregamos la entidad al listado interno.
							entityMap.put(nextIndex, entity);
							nextIndex++;
						}
					}
				}
				setEnabled(true);
			}
		}.start();
	}

	/**
	 * Permite cargar dentro de la tabla y del mapa la entidad que recibimos.
	 * 
	 * @param entity
	 *            La entidad que vamos a almacenar en el mapa y la tabla.
	 */
	protected void addEntity(final E entity) {
		new Thread() {
			public void run() {
				try {
					// Creamos la fila y la cargamos.
					List<Object> row = new ArrayList<Object>();
					for (String property : visibleProperties) {
						row.add(propertiesGetter.get(property).invoke(entity));
					}

					synchronized (rowMutex) {
						// Cargamos la fila al modelo de la tabla.
						tableModel.addRow(row.toArray());

						// Agregamos la entidad al listado interno.
						entityMap.put(nextIndex, entity);
						nextIndex++;
					}
				} catch (Exception e) {
				}
			}
		}.start();
	}

	/**
	 * Permite quitar de la tabla y del mapa la entidad que recibimos.
	 * 
	 * @param entity
	 *            La entidad que vamos a quitar del mapa y la tabla.
	 */
	protected void removeEntity(final E entity) {
		new Thread() {
			public void run() {
				synchronized (rowMutex) {
					// Buscamos el indice de la entidad que queremos borrar.
					Integer removeRowKey = null;
					for (Integer entityRowKey : entityMap.keySet()) {
						if (entityMap.get(entityRowKey).equals(entity)) {
							removeRowKey = entityRowKey;
							break;
						}
					}

					// Si encontramos el indice, eliminamos.
					if (removeRowKey != null) {
						// Quitamos la entidad de la tabla y del mapa de entidades.
						entityMap.remove(removeRowKey);
						tableModel.removeRow(removeRowKey);

						// Bajamos en 1 la cantidad de entidades en la tabla.
						nextIndex--;

						// Ahora tenemos que actualizar el mapa de las entidades a partir del índice eliminado, solo corriendo las entidades.
						for (Integer i = removeRowKey; i < nextIndex; i++) {
							entityMap.put(i, entityMap.get(i + 1));
						}
						entityMap.remove(nextIndex);
					}
				}
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
	public void setValues(Collection<E> entities) {
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
	public void addValue(E entity) {
		synchronized (this.nextIndex) {
			this.addEntity(entity);
		}
	}

	/**
	 * Permite quitar una entidad de las que tenemos cargadas dentro de la tabla.
	 * 
	 * @param entity
	 *            La entidad que queremos quitar de la tabla.
	 */
	public void removeValue(E entity) {
		synchronized (this.nextIndex) {
			this.removeEntity(entity);
		}
	}

	/**
	 * Se encarga de consultar si la tabla contiene dentro de si una entidad dada.
	 * 
	 * @param entity
	 *            La entidad sobre la que vamos a preguntar si esta cargada dentro de la tabla.
	 * @return <i>true</i> en caso de que la entidad se encuentra cargada dentro de la tabla, en caso contrario retorna <i>false</i>.
	 */
	public boolean containValue(E entity) {
		return this.entityMap.containsValue(entity);
	}

	/**
	 * Permite recuperar la entidad que tenemos seleccionada dentro de la tabla, en caso de que no haya ninguna, retornamos un valor nulo.
	 * 
	 * @return La entidad que se encuentra seleccionada en la tabla, en caso de que no se encuentre ninguna seleccionada, retornamos un valor nulo.
	 */
	public E getSelectedValue() {
		synchronized (this.rowMutex) {
			// Otenemos el indice seleccinado.
			Integer index = this.getSelectedRow();
			if (index != -1) {
				Integer realIndex = this.convertRowIndexToModel(index);
				return this.entityMap.get(realIndex);
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
	public Collection<E> getSelectedValues() {
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
			Collection<E> selectedValues = new ArrayList<E>();
			for (Integer realIndex : realIndexs) {
				if (this.entityMap.containsKey(realIndex)) {
					selectedValues.add(this.entityMap.get(realIndex));
				}
			}

			// Recuperamos esos objetos y los devolvemos.
			return selectedValues;
		}
	}
}