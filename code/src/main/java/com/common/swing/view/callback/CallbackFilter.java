package com.common.swing.view.callback;

import java.io.Serializable;
import java.util.Collection;

/**
 * Interfaz para actualizar un listado de elementos cuando se busca mediante un filtro.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            El tipo de objeto que vamos a filtrar.
 */
public interface CallbackFilter<E extends Serializable> extends Serializable {

	/**
	 * Permite actualizar un listado de entidades a partir de un filtro.
	 * 
	 * @param entities
	 *            El listado de las entidades.
	 */
	public void updateEntities(Collection<E> entities);
}