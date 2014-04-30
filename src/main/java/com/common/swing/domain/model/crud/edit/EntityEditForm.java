package com.common.swing.domain.model.crud.edit;

import java.io.Serializable;

import com.common.swing.domain.model.crud.Form;
import com.common.util.business.service.BaseService;
import com.common.util.domain.exception.CheckedException;
import com.common.util.domain.model.Persistence;

/**
 * La interfaz que define los métodos para la ventana que va a representar un formulario de edición de entidades.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que establece las entidades que vamos a editar dentro de este formulario.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a editar.
 */
public interface EntityEditForm<E extends Persistence<PK>, PK extends Serializable> extends Form<E> {

	/**
	 * Se encarga de crear un formulario para el alta de una entidad.
	 * 
	 * @return El formulario para dar de alta una nueva entidad.
	 */
	public EntityEditForm<E, PK> createNewForm();

	/**
	 * Se encarga de crear un formulario para la edición de una entidad.
	 * 
	 * @param editEntity
	 *            La entidad que vamos a editar dentro de esta ventana.
	 * @return El formulario configurada para modificar una entidad.
	 */
	public EntityEditForm<E, PK> createEditForm(E editEntity);

	/**
	 * Se encarga de crear una nueva entidad para dar de alta dentro de este formulario.
	 * 
	 * @return La nueva entidad que vamos a dar de alta.
	 */
	public E createNewEntity();

	/**
	 * Se encarga de retornar la entidad que tenemos dentro del formulario.
	 * 
	 * @return La entidad que tenemos dentro del formulario.
	 */
	public E getEntity();

	/**
	 * Se encarga de retornar el servicio que vamos a utilizar para la entidad que tenemos en el panel.
	 * 
	 * @return El servicio para la entidad que tenemos en el panel.
	 */
	public BaseService<E, PK> getService();

	/**
	 * Se encarga de cargar los campos de la ventana de acuerdo a los valores de los atributos de la entidad que queremos editar.
	 */
	public void fromEntityToFields();

	/**
	 * Se encarga de tomar los valores de los campos de la ventana y cargarlos dentro de la entidad antes de guardarlo dentro de la base de datos.
	 * 
	 * @throws CheckedException
	 *             En caso de que la carga de datos desde la ventana no sea válida.
	 */
	public void fromFieldsToEntity() throws CheckedException;

	/**
	 * Se encarga del guardado de la entidad que estamos editando dentro de la base de datos.
	 */
	public void confirm();

	/**
	 * Se encarga de cerrar el formulario de edición de entidades y no guardar ningún cambio de esta en la base de datos.
	 */
	public void reject();
}