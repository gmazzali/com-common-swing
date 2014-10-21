package com.common.swing.view.decorator;

import javax.swing.JButton;

/**
 * La interfaz que define el decorador de un bot�n.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ButtonDecorator {

	/**
	 * Permite establecer las propiedades de un bot�n dado.
	 * 
	 * @param button
	 *            El bot�n al que vamos a cargarle las propiedades.
	 */
	public void decorateButton(JButton button);
}