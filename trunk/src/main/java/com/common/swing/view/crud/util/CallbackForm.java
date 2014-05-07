package com.common.swing.view.crud.util;

import java.io.Serializable;

/**
 * Interface para el manejo de eventos de un formulario de alta / modificación / visualización.
 * 
 * @since 05/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface CallbackForm extends Serializable {

	/**
	 * Metodo ejecutado al presionar la opción aceptar de un formulario de alta / modificación / visualización.
	 */
	public void confirm();

	/**
	 * Metodo ejecutado al presionar la opción cancelar de un formulario de alta / modificación / visualización.
	 */
	public void reject();
}