package com.common.swing.domain.model.crud.form;

import java.io.Serializable;

import com.common.swing.domain.model.crud.BaseForm;
import com.common.swing.domain.model.crud.container.BaseContainer;
import com.common.swing.domain.model.crud.util.CallbackForm;
import com.common.swing.domain.model.crud.util.FormTypeEnum;
import com.common.util.domain.exception.CheckedException;

/**
 * La interfaz que define los métodos para la ventana que va a representar un formulario de edición de entidades.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que establece las entidades que vamos a editar dentro de este formulario.
 */
public interface EditForm<E extends Serializable> extends BaseForm {

	/**
	 * Se encarga de crear un formulario de acuerdo al tipo recibido y con la entidad que tenemos cargada dentro de esta.
	 * 
	 * @param formType
	 *            El tipo de formulario que vamos a crear, puede ser {@link FormTypeEnum#NEW}, {@link FormTypeEnum#EDIT} o {@link FormTypeEnum#VIEW}
	 * @param callback
	 *            El comportamiento que vamos a controlar despues de apretar el boton de aceptar o cancelar. Puede ser <code>null</code>.
	 * @param container
	 *            El contenedor donde vamos a colocar el formulario.
	 */
	public void createForm(BaseContainer container, FormTypeEnum formType, CallbackForm callback);

	/**
	 * Permite recuperar la entidad que estamos manipulando dentro de este formulario, en caso de que el formulario se cancele, esto retornará
	 * <code>null</code>.
	 * 
	 * @return La entidad que estamos manipulando dentro de este formulario, o <code>null</code> en caso de cancelar la edición de la entidad.
	 */
	public E getEntity();

	/**
	 * Permite cargar dentro del formulario la entidad que vamos a editar.
	 * 
	 * @param entity
	 *            La entidad que vamos a editar.
	 */
	public void setEntity(E entity);

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