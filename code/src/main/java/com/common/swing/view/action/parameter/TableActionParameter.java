package com.common.swing.view.action.parameter;

import java.util.Collection;
import java.util.Collections;

import com.common.swing.view.bean.RowBean;

/**
 * Permite definir un parámetro para las acciones de una tabla.
 * 
 * @since 28/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            El bean de fila de tabla.
 */
public class TableActionParameter<E extends RowBean> extends BaseActionParameter<E> {
	private static final long serialVersionUID = 1L;

	/**
	 * El listado de beans seleccionados de la tabla.
	 */
	private Collection<E> entities;

	/**
	 * El constructor de un parámetro para la acción de la tabla.
	 * 
	 * @param entities
	 *            El listado de entidades seleccionadas.
	 */
	public TableActionParameter(Collection<E> entities) {
		this.entities = Collections.unmodifiableCollection(entities);
	}

	/**
	 * Permite recuperar las entidades seleccionadas de la tabla.
	 * 
	 * @return El listado de filas seleccionadas.
	 */
	public Collection<E> getEntities() {
		return entities;
	}
}