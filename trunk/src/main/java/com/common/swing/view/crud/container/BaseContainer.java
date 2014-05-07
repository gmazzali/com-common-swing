package com.common.swing.view.crud.container;

import java.io.Serializable;

/**
 * La interfaz que define el comportamiento común para los contenedores de formularios.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface BaseContainer extends Serializable {

	/**
	 * Permite cargarle el titulo al contenedor.
	 * 
	 * @param title
	 *            El titulo del contenedor.
	 */
	public void setTitle(String title);
}