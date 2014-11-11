package com.common.swing.view.component.list.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractListModel;

import com.common.swing.view.bean.RowBean;
import com.common.util.business.tool.collection.CollectionUtil;

/**
 * Define un modelo de una lista de base.
 * 
 * @since 07/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <B>
 *            La clase de beans que vamos a tener dentro de este modelo.
 */
public class BaseListModel<B extends RowBean> extends AbstractListModel<B> {
	private static final long serialVersionUID = 1L;

	/**
	 * El indice no encontrado.
	 */
	private static final int INDEX_NOT_FOUND = -1;

	/**
	 * El listado de los beans que vamos a tener dentro de este modelo.
	 */
	protected List<B> rowBeans;
	
	/**
	 * El constructor del modelo de la lista.
	 */
	public BaseListModel() {
		this.rowBeans = new ArrayList<B>();
	}

	/**
	 * Retorna el tamaño de la lista de elementos.
	 * 
	 * @return El tamaño de los elementos que tenemos dentro de la lista.
	 */
	@Override
	public int getSize() {
		return rowBeans.size();
	}

	/**
	 * Permite recuperar el elemento de un indice dado.
	 * 
	 * @param index
	 *            El indice que vamos a recuperar.
	 * @return El bean que se encuentra dentro de esta posición.
	 */
	@Override
	public B getElementAt(int index) {
		if (index >= 0 && index < this.getSize()) {
			return rowBeans.get(index);
		}
		return null;
	}

	/**
	 * Permite vaciar el contenido de la lista.
	 */
	public void clear() {
		int size = this.getSize();
		if (size > 0) {
			this.rowBeans.clear();
			this.fireIntervalRemoved(this, 0, size - 1);
		}
	}

	/**
	 * Permite agregar una nueva linea dentro de la lista.
	 * 
	 * @param rowBean
	 *            El bean que vamos a agregar al modelo. En caso que este valor sea <code>null</code>, no se va a insertar a la misma.
	 */
	public void addRow(B rowBean) {
		if (rowBean != null) {
			int size = this.getSize();
			this.rowBeans.add(rowBean);
			this.fireIntervalAdded(this, size, size);
		}
	}

	/**
	 * Permite agregar una lista de beans al modelo de la lista.
	 * 
	 * @param rowBeans
	 *            El listado de los beans que vamos a agregar al modelo. En caso que este valor sea <code>null</code> o este vacia la lista, no se va
	 *            a insertar nada.
	 */
	public void addRows(Collection<B> rowBeans) {
		if (CollectionUtil.isNotEmpty(rowBeans)) {
			int from = this.getSize();
			for (B rowBean : rowBeans) {
				this.rowBeans.add(rowBean);
			}
			int to = this.getSize();
			this.fireIntervalAdded(this, from, to);
		}
	}

	/**
	 * Permite recuperar la fila dado su indice.
	 * 
	 * @param realIndex
	 *            El indice real del bean dentro del modelo.
	 * @return El bean que corresponde al indice, en caso de se un indice fuera rango de valores, retornamos <code>null</code>.
	 */
	public B getRow(int realIndex) {
		return this.getElementAt(realIndex);
	}

	/**
	 * Permite quitar una linea dentro de la lista de acuerdo a su indice.
	 * 
	 * @param rowIndex
	 *            El indice del bean que queremos eliminar.
	 */
	public void removeRow(int rowIndex) {
		if (rowIndex >= 0 && rowIndex < this.getSize()) {
			this.rowBeans.remove(rowIndex);
			this.fireIntervalRemoved(this, rowIndex, rowIndex);
		}
	}

	/**
	 * Permite quitar una linea dentro de la lista.
	 * 
	 * @param rowBean
	 *            La fila dentro de la lista quer vamos a quitar.
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
	 * Permite saber si la lista se encuentra vacia.
	 * 
	 * @return <code>true</code> en caso de que la lista no posea ningun bean, en caso contrario, retorna <code>false</code>.
	 */
	public boolean isEmpty() {
		return rowBeans.isEmpty();
	}

	/**
	 * Permite recuperar el indice del modelo en el que se encuentra el bean que tenemos.
	 * 
	 * @param rowBean
	 *            El bean del que queremos saber el indice. En caso que sea <code>null</code> retornamos {@link BaseListModel#INDEX_NOT_FOUND}.
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