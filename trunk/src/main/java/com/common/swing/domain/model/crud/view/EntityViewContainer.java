package com.common.swing.domain.model.crud.view;

import java.io.Serializable;

import com.common.swing.domain.model.crud.FormContainer;
import com.common.util.domain.model.Persistence;

/**
 * El panel que implementa la interfaz que nos permite definir un contenedor de un formulario para la visualización de un elemento.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que establece las entidades que vamos a visualizar dentro de este contenedor.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a visualizar.
 */
public interface EntityViewContainer<E extends Persistence<PK>, PK extends Serializable> extends FormContainer<E> {

	/**
	 * Se encarga de retornar el titulo de la ventana de visualización.
	 * 
	 * @return El titulo de la ventana de visualización.
	 */
	public String getViewTitle();

	/**
	 * Se encarga de establecer la ventana de formulario para la visualización de una entidad con el panel adecuado.
	 * 
	 * @param entity
	 *            La entidad que vamos a visualizar dentro de esta ventana.
	 * @return La ventana configurada para visualizar una entidad.
	 */
	public EntityViewContainer<E, PK> createViewForm(E entity);

	/**
	 * Se encarga de retornar el panel que vamos a utilizar dentro de esta ventana para su visualización.
	 * 
	 * @return El panel de visualización de una entidad.
	 */
	public EntityViewForm<E, PK> getViewForm();
}