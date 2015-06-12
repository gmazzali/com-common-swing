package com.common.swing.view.component.list;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.swing.JList;
import javax.swing.ListModel;

import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.list.model.BaseListModel;
import com.common.util.business.tool.collection.CollectionUtil;

/**
 * La clase que permite definir una lista con datos.
 * 
 * @since 10/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <B>
 *            El bean de tabla que vamos a manipular dentro de esta lista.
 */
public abstract class BaseList<B extends RowBean> extends JList<B> {
	private static final long serialVersionUID = 1L;

	/**
	 * El valor booleano que nos indica si va a seleccionarse o no la primer fila cuando se carguen datos.
	 */
	private boolean firstSelected;
	/**
	 * El modelo de la lista.
	 */
	protected BaseListModel<B> listModel;
	/**
	 * Los objectos de exclusion de fila y de lista.
	 */
	private final Object rowMutex = new Object();
	private final Object listMutex = new Object();

	/**
	 * Inicializa la lista y sus componentes.
	 */
	@PostConstruct
	protected void init() {
		this.listModel = this.createListModel();
		super.setModel(this.listModel);
	}

	@Override
	public void setModel(ListModel<B> model) {
		// No se le carga ningun modelo a la lista, ya que se lo creamos nosotros al mismo.
	}

	/**
	 * Permite crear el modelo de la lista.
	 * 
	 * @return El modelo de la lista.
	 */
	protected BaseListModel<B> createListModel() {
		return new BaseListModel<B>();
	}

	/**
	 * Permite saber si la lista se encuentra vacía.
	 * 
	 * @return <code>true</code> en caso de que la lista no contenga ninguna entidad, en caso contrario, retorna <code>false</code>.
	 */
	public boolean isEmpty() {
		return this.listModel.isEmpty();
	}

	/**
	 * Permite vaciar el contenido de la lista.
	 */
	public void clearList() {
		synchronized (listMutex) {
			synchronized (rowMutex) {
				this.listModel.clear();
			}
		}
	}

	/**
	 * Permite cargar dentro de esta tabla las entidades que recibimos dentro de una lista. Esta carga se realiza sobre una lista completamente vacia.
	 * 
	 * @param entities
	 *            La lista de entidades que vamos a almacenar en la lista.
	 */
	protected void reloadData(final Collection<B> entities) {
		new Thread() {
			@Override
			public void run() {
				setEnabled(false);
				clearList();
				synchronized (listMutex) {
					for (B entity : entities) {
						synchronized (rowMutex) {
							listModel.addRow(entity);
						}
					}
					if (firstSelected && !listModel.isEmpty()) {
						setSelectedIndex(0);
					}
				}
				setEnabled(true);
			}
		}.start();
	}

	/**
	 * Permite cargar un listado de entidades dentro de la lista. Al cargar este nuevo listado, se borra el contenido de la lista que se encuentra
	 * actualmente y se carga la misma con este listado. En caso de que la lista este vacía o sea <code>null</code> se va a limpiar la lista.
	 * 
	 * @param entities
	 *            El listado de las entidades. Puede estar vacía o ser <code>null</code>.
	 */
	public void setValues(Collection<B> entities) {
		if (CollectionUtil.isNotEmpty(entities)) {
			this.reloadData(entities);
		} else {
			this.clearList();
		}
	}

	/**
	 * Permite agregar una entidad a las que ya tenemos cargadas dentro de la lista.
	 * 
	 * @param entity
	 *            La entidad que vamos a agregar a las que ya tenemos.
	 */
	public void addValue(final B entity) {
		new Thread() {
			@Override
			public void run() {
				synchronized (rowMutex) {
					listModel.addRow(entity);
				}
			}
		}.start();
	}

	/**
	 * Permite quitar una entidad de las que tenemos cargadas dentro de la lista.
	 * 
	 * @param entity
	 *            La entidad que queremos quitar de la lista.
	 */
	public void removeValue(final B entity) {
		new Thread() {
			@Override
			public void run() {
				synchronized (rowMutex) {
					listModel.removeRow(listModel.getIndex(entity));
				}
			}
		}.start();
	}

	/**
	 * Se encarga de consultar si la lista contiene dentro de si una entidad dada.
	 * 
	 * @param entity
	 *            La entidad sobre la que vamos a preguntar si esta cargada dentro de la lista.
	 * @return <code>true</code> en caso de que la entidad se encuentra cargada dentro de la lista, en caso contrario retorna <code>false</code>.
	 */
	public boolean containValue(B entity) {
		return this.listModel.contains(entity);
	}

	/**
	 * Permite recuperar la entidad que tenemos seleccionada dentro de la lista, en caso de que no haya ninguna, retornamos un valor <code>null</code>
	 * .
	 * 
	 * @return La entidad que se encuentra seleccionada en la lista, en caso de que no se encuentre ninguna seleccionada, retornamos un valor
	 *         <code>null</code>.
	 */
	public B getValue() {
		synchronized (this.rowMutex) {
			// Otenemos el indice seleccinado.
			Integer index = this.getSelectedIndex();
			if (index != -1) {
				return listModel.getRow(index);
			}
			return null;
		}
	}

	/**
	 * Permite recuperar un listado de las entidades que tenemos seleccionadas dentro de la lista, en caso de que no haya ninguna, retornamos una
	 * lista vacia.
	 * 
	 * @return El listado de las entidades que se encuentran seleccionadas en la lista, en caso de que no se encuentre ninguna seleccionada,
	 *         retornamos una lista vacia.
	 */
	public Collection<B> getValues() {
		synchronized (this.listMutex) {
			// Obtenemos los indices seleccionados.
			int[] indexs = this.getSelectedIndices();

			// Otenemos las entidades de esos indices.
			Collection<B> selectedValues = new ArrayList<B>();
			for (Integer realIndex : indexs) {
				selectedValues.add(this.listModel.getRow(realIndex));
			}
			// Recuperamos esos objetos y los devolvemos.
			return selectedValues;
		}
	}
}