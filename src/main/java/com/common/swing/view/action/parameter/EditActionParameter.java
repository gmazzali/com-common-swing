package com.common.swing.view.action.parameter;

import com.common.swing.view.bean.EditBean;

/**
 * Permite definir un parámetro para las acciones de edición.
 * 
 * @since 27/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            El bean de edición.
 */
public class EditActionParameter<E extends EditBean<?>> extends BaseActionParameter<E> {
	private static final long serialVersionUID = 1L;

	/**
	 * La entidad de edición.
	 */
	private final E entity;

	/**
	 * El constructor de un parámetro.
	 * 
	 * @param entity
	 *            La entidad del parámetro.
	 */
	public EditActionParameter(E entity) {
		this.entity = entity;
	}

	/**
	 * Permite recuperar la entidad.
	 * 
	 * @return La entidad de edición.
	 */
	public E getEntity() {
		return entity;
	}
}