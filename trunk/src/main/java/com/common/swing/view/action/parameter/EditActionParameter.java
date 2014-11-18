package com.common.swing.view.action.parameter;

import com.common.swing.view.bean.EditBean;

/**
 * Permite definir un par�metro para las acciones de edici�n.
 * 
 * @since 27/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            El bean de edici�n.
 */
public class EditActionParameter<E extends EditBean<?>> extends BaseActionParameter<E> {
	private static final long serialVersionUID = 1L;

	/**
	 * La entidad de edici�n.
	 */
	private final E entity;

	/**
	 * El constructor de un par�metro.
	 * 
	 * @param entity
	 *            La entidad del par�metro.
	 */
	public EditActionParameter(E entity) {
		this.entity = entity;
	}

	/**
	 * Permite recuperar la entidad.
	 * 
	 * @return La entidad de edici�n.
	 */
	public E getEntity() {
		return entity;
	}
}