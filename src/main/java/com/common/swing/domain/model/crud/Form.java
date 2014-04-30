package com.common.swing.domain.model.crud;

import java.io.Serializable;

/**
 * La interfaz que define el comportamiento común para los formularios de entidades.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de elementos que vamos a mantener dentro de este formulario.
 */
public interface Form<E extends Serializable> {

	/**
	 * Se encarga de retornar el container que mantiene este formulario.
	 * 
	 * @return El container que contiene este formulario.
	 */
	public FormContainer<E> getFormContainer();

	/**
	 * Se encarga de habilitar o deshabilitar un formulario.
	 * 
	 * @param enabled
	 *            El valor booleano que nos permite saber si debemos habilitar o deshabilitar el formulario.
	 */
	public void setEnabled(boolean enabled);

	/**
	 * Se encarga de reiniciar los campos de búsqueda para comenzar con una búsqueda desde el comienzo.
	 */
	public void emptyFields();

	/**
	 * Se encarga de retornar el alto del formulario.
	 * 
	 * @return El alto del formulario que tenemos (en pixeles).
	 */
	public Integer getHeightSize();

	/**
	 * Se encarga de retornar el ancho del formulario.
	 * 
	 * @return El ancho del formulario que tenemos (en pixeles).
	 */
	public Integer getWidthSize();
}