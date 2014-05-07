package com.common.swing.view.crud.util;

import java.io.Serializable;

/**
 * Interface para el manejo de eventos de un formulario de alta / modificaci�n / visualizaci�n.
 * 
 * @since 05/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface CallbackForm extends Serializable {

	/**
	 * Metodo ejecutado al presionar la opci�n aceptar de un formulario de alta / modificaci�n / visualizaci�n.
	 */
	public void confirm();

	/**
	 * Metodo ejecutado al presionar la opci�n cancelar de un formulario de alta / modificaci�n / visualizaci�n.
	 */
	public void reject();
}