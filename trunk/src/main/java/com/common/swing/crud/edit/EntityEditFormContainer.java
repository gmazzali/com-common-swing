package com.common.swing.crud.edit;

import java.io.Serializable;

import com.common.swing.crud.FormContainer;
import com.common.util.model.Persistence;

/**
 * La interfaz que define los métodos para el contenedor de un formulario de edición de entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que establece las entidades que vamos a editar dentro de esta ventana.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a editar.
 */
public interface EntityEditFormContainer<E extends Persistence<PK>, PK extends Serializable> extends FormContainer {

	/**
	 * La función encargada de establecer la ventana de formulario para una nueva entidad con el panel de edición adecuado.
	 * 
	 * @return La ventana configurada para dar de alta una nueva entidad.
	 */
	public EntityEditFormContainer<E, PK> createNewForm();

	/**
	 * La función encargada de establecer la ventana de formulario para la edición de una entidad con el panel de edición adecuado.
	 * 
	 * @param entity
	 *            La entidad que vamos a editar dentro de esta ventana.
	 * @return La ventana configurada para modificar una entidad.
	 */
	public EntityEditFormContainer<E, PK> createEditForm(E entity);

	/**
	 * La función que retorna el titulo de la ventana que va a contener el panel de alta de una entidad.
	 * 
	 * @return El titulo de la ventana que contiene el panel de alta de entidades.
	 */
	public String getNewFormTitle();

	/**
	 * La función que retorna el titulo de la ventana que va a contener el panel de edición de una entidad.
	 * 
	 * @return El titulo de la ventana que contiene el panel de edición de entidades.
	 */
	public String getEditFormTitle();

	/**
	 * La función encargada de retornar el panel que vamos a utilizar dentro de esta ventana para la edición de una entidad dada.
	 * 
	 * @return El panel de edición de una entidad.
	 */
	public EntityEditForm<E, PK> getEntityForm();

	/**
	 * La función encargada de retornar la entidad que estamos editando dentro del formulario que tenemos dentro de este contenedor.
	 * 
	 * @return La entidad que se acaba de almacenar dentro de la base de datos, en caso de que se cancele el guardado, esta función retornará un valor
	 *         null.
	 */
	public E getEntity();
}