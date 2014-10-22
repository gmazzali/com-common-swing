package com.crud.swing.view.form;

/**
 * La interfaz que define el comportamiento común para los formularios de entidades.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface BaseForm {

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

	/**
	 * Se encarga de habilitar este formulario.
	 */
	public void enabled();

	/**
	 * Se encarga de deshabilitar este formulario.
	 */
	public void disabled();

	/**
	 * Se encarga de vaciar los campos de la ventana para poder volver a cargarlos desde cero.
	 */
	public void emptyFields();
}