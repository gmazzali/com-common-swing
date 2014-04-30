package com.common.swing.domain.model.crud.filter;

import java.io.Serializable;

import com.common.swing.domain.model.crud.FormContainer;
import com.common.util.domain.model.Persistence;

/**
 * La interfaz que nos permite definir el comportamiento para un contenedor de formularios de filtrado de entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            Las clases de las entidades que vamos a filtrar dentro del contenedor.
 * @param <PK>
 *            Las clases que representan los identificadores de las entidades.
 */
public interface EntityFilterFormContainer<E extends Persistence<PK>, PK extends Serializable> extends FormContainer {

	/**
	 * La función que retornar el título que vamos a colocarle a este contenedor de formularios.
	 * 
	 * @return El título de este contenedor de formulario.
	 */
	public String getFilterContainerTitle();

	/**
	 * La función encargada de retornar el panel de búsqueda para el filtrado de las consultas dentro del panel de listado.
	 * 
	 * @return El panel de búsqueda filtrada.
	 */
	public EntityFilterForm<E, PK> getEntityFilterForm();
}