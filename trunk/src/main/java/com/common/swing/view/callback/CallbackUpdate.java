package com.common.swing.view.callback;

/**
 * Interface para el manejo de eventos para actualizar un componente de acuerdo a los eventos dispoarado por otro.
 * 
 * @since 21/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface CallbackUpdate {

	/**
	 * Permite actualizar un componente de acuerdo a los cambios de otro componente.
	 */
	public void update();
}