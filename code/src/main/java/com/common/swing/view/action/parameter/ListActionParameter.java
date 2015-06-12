package com.common.swing.view.action.parameter;

import java.util.Collection;
import java.util.Collections;

import com.common.swing.view.bean.RowBean;

/**
 * Permite definir un parámetro para las acciones de una lista.
 * 
 * @since 10/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            El bean de fila de lista.
 */
public class ListActionParameter<E extends RowBean> extends BaseActionParameter<E> {
	private static final long serialVersionUID = 1L;

	/**
	 * El listado de beans seleccionados de la lista.
	 */
	private Collection<E> entities;

	/**
	 * El constructor de un parámetro para la acción de la lista.
	 * 
	 * @param entities
	 *            El listado de entidades seleccionadas.
	 */
	public ListActionParameter(Collection<E> entities) {
		this.entities = Collections.unmodifiableCollection(entities);
	}

	/**
	 * Permite recuperar las entidades seleccionadas de la lista.
	 * 
	 * @return El listado de filas seleccionadas.
	 */
	public Collection<E> getEntities() {
		return entities;
	}
}