package com.common.swing.view.component.panel;

/**
 * La interfaz que define el comportamiento común para los paneles de los formularios.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface BasePanel {

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
	 * Permite vaciar el contenido del formulario.
	 */
	public void emptyFields();
}