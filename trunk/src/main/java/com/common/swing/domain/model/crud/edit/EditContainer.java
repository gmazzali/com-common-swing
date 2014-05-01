package com.common.swing.domain.model.crud.edit;

import java.io.Serializable;

import com.common.swing.domain.model.crud.FormContainer;
import com.common.util.domain.model.Persistence;

/**
 * La interfaz que define los métodos para el contenedor de un formulario de edición de entidades.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que establece las entidades que vamos a editar dentro de esta ventana.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a editar.
 */
public interface EditContainer<E extends Persistence<PK>, PK extends Serializable> extends FormContainer<E> {

	/**
	 * Se encarga de establecer la ventana de formulario para una nueva entidad con el panel de edición adecuado.
	 * 
	 * @return La ventana configurada para dar de alta una nueva entidad.
	 */
	public EditContainer<E, PK> createNewForm();

	/**
	 * Se encarga de establecer la ventana de formulario para la edición de una entidad con el panel de edición adecuado.
	 * 
	 * @param entity
	 *            La entidad que vamos a editar dentro de esta ventana.
	 * @return La ventana configurada para modificar una entidad.
	 */
	public EditContainer<E, PK> createEditForm(E entity);

	/**
	 * Se encarga de retornar el titulo de la ventana que va a contener el panel de alta de una entidad.
	 * 
	 * @return El titulo de la ventana que contiene el panel de alta de entidades.
	 */
	public String getNewTitle();

	/**
	 * Se encarga de retornar el titulo de la ventana que va a contener el panel de edición de una entidad.
	 * 
	 * @return El titulo de la ventana que contiene el panel de edición de entidades.
	 */
	public String getEditTitle();

	/**
	 * Se encarga de retornar el panel que vamos a utilizar dentro de esta ventana para su edición.
	 * 
	 * @return El panel de edición de una entidad.
	 */
	public EditForm<E, PK> getEditForm();

	/**
	 * Se encarga de retornar la entidad que estamos editando dentro del formulario que tenemos dentro de este contenedor.
	 * 
	 * @return La entidad que estamos editando dentro del formulario que tenemos dentro de este contenedor.
	 */
	public E getEntity();
}