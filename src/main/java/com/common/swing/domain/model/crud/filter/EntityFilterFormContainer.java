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
	 * La funci�n que retornar el t�tulo que vamos a colocarle a este contenedor de formularios.
	 * 
	 * @return El t�tulo de este contenedor de formulario.
	 */
	public String getFilterContainerTitle();

	/**
	 * La funci�n encargada de retornar el panel de b�squeda para el filtrado de las consultas dentro del panel de listado.
	 * 
	 * @return El panel de b�squeda filtrada.
	 */
	public EntityFilterForm<E, PK> getEntityFilterForm();
}