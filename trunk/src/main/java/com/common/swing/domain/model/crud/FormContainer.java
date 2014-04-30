package com.common.swing.domain.model.crud;

import java.io.Serializable;

/**
 * La interfaz que define el comportamiento común a todos los contenedores de formularios.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de elementos que vamos a mantener dentro de este contenedor.
 */
public interface FormContainer<E extends Serializable> {

	/**
	 * Se encarga de desplegar el contenedor para mostrar el formulario que tiene en el interior.
	 */
	public void open();

	/**
	 * Se encarga de cerrar el contenedor donde tenemos desplegado un formulario.
	 */
	public void close();
}