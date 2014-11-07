package com.common.swing.view.component.list.model;

import java.util.List;

import javax.swing.AbstractListModel;

import com.common.swing.view.bean.RowBean;

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
	 * El listado de los beans que vamos a tener dentro de este modelo.
	 */
	protected List<B> rowBeans;

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
		return rowBeans.get(index);
	}

	/**
	 * Permite saber si la lista se encuentra vacia.
	 * 
	 * @return <code>true</code> en caso de que la lista no posea ningun bean, en caso contrario, retorna <code>false</code>.
	 */
	public boolean isEmpty() {
		return rowBeans.isEmpty();
	}
}