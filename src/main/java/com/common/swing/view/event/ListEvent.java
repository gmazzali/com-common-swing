package com.common.swing.view.event;

import java.util.Collection;
import java.util.Collections;

import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.list.BaseList;

/**
 * Permite definir un evento de la lista.
 * 
 * @since 10/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de elementos que contiene la lista que disparo el evento.
 */
public class ListEvent<E extends RowBean> extends BaseEvent<E> {
	private static final long serialVersionUID = 1L;

	/**
	 * La lista que disparo el evento.
	 */
	protected BaseList<E> list;

	/**
	 * Crea un evento de la lista.
	 * 
	 * @param list
	 *            La lista que disparo el evento.
	 */
	public ListEvent(BaseList<E> list) {
		this.list = list;
	}

	/**
	 * Retorna la entidad seleccionada de la lista.
	 * 
	 * @return La entidad.
	 */
	public E getSelectedEntity() {
		return this.list.getValue();
	}

	/**
	 * Retorna las entidades seleccionadas de la lista.
	 * 
	 * @return Las entidades.
	 */
	public Collection<E> getSelectedEntities() {
		return Collections.unmodifiableCollection(this.list.getValues());
	}

	/**
	 * Retorna la lista que disparo el evento.
	 * 
	 * @return La lista que disparo el evento.
	 */
	public BaseList<E> getList() {
		return list;
	}
}