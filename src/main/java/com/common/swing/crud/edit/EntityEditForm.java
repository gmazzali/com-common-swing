package com.common.swing.crud.edit;

import java.io.Serializable;

import com.common.swing.crud.Form;
import com.common.util.exception.CheckedException;
import com.common.util.model.Persistence;
import com.common.util.service.GenericService;

/**
 * La interfaz que define los m�todos para la ventana que va a representar un formulario de edici�n de entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que establece las entidades que vamos a editar dentro de este formulario.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a editar.
 */
public interface EntityEditForm<E extends Persistence<PK>, PK extends Serializable> extends Form {

	/**
	 * La funci�n encargada de crear un formulario para el alta de una entidad.
	 * 
	 * @return El formulario para dar de alta una nueva entidad.
	 */
	public EntityEditForm<E, PK> createNewEntityForm();

	/**
	 * La funci�n encargada de crear un formulario para la edici�n de una entidad.
	 * 
	 * @param editEntity
	 *            La entidad que vamos a editar dentro de esta ventana.
	 * @return El formulario configurada para modificar una entidad.
	 */
	public EntityEditForm<E, PK> createEditEntityForm(E editEntity);

	/**
	 * La funci�n encargada de cerrar el formulario de edici�n de entidades y no guardar ning�n cambio de esta en la base de datos.
	 */
	public void rejectForm();

	/**
	 * La funci�n encargada de crear una nueva entidad para dar de alta dentro de este formulario.
	 * 
	 * @return La nueva entidad que vamos a dar de alta.
	 */
	public E createNewEntity();

	/**
	 * La funci�n encargada de retornar la entidad que acabamos de almacenar dentro de la base de datos.
	 * 
	 * @return La entidad que se acaba de almacenar dentro de la base de datos, en caso de que se cancele el guardado, esta funci�n retornar� un valor
	 *         null.
	 */
	public E getEntity();

	/**
	 * La funci�n encargada de retornar el servicio que vamos a utilizar para la entidad que tenemos en el panel.
	 * 
	 * @return El servicio para la entidad que tenemos en el panel.
	 */
	public GenericService<E, PK> getEntityService();

	/**
	 * La funci�n encargada de retornar el contenedor donde colocamos este formulario.
	 * 
	 * @return El contenedor contiene este formulario.
	 */
	public EntityEditFormContainer<E, PK> getEntityFormContainer();

	/**
	 * La funci�n encargada de cargar los campos de la ventana de acuerdo a los valores de los atributos de la entidad que queremos editar.
	 */
	public void fromEntityToFields();

	/**
	 * La funci�n encargada de tomar los valores de los campos de la ventana y cargarlos dentro de la entidad antes de guardarlo dentro de la base de
	 * datos.
	 * 
	 * @throws CheckedException
	 *             En caso de que la carga de datos desde la ventana no sea v�lida.
	 */
	public void fromFieldsToEntity() throws CheckedException;

	/**
	 * La funci�n encargada del guardado de la entidad que estamos editando dentro de la base de datos.
	 */
	public void saveEntity();
}