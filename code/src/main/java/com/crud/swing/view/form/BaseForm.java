package com.crud.swing.view.form;

/**
 * La interfaz que define el comportamiento común para los paneles de los formularios.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface BaseForm {

	/**
	 * Se encarga de habilitar este formulario.
	 */
	public void enabled();

	/**
	 * Se encarga de deshabilitar este formulario.
	 */
	public void disabled();

	/**
	 * Permite vaciar el contenido del formulario.
	 */
	public void emptyFields();
}