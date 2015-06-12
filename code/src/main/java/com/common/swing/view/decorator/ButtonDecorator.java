package com.common.swing.view.decorator;

import java.io.Serializable;

import javax.swing.JButton;

/**
 * La interfaz que define el decorador de un botón.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ButtonDecorator extends Serializable {

	/**
	 * Permite establecer las propiedades de un botón dado.
	 * 
	 * @param button
	 *            El botón al que vamos a cargarle las propiedades.
	 */
	public void decorateButton(JButton button);
}