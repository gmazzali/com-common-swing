package com.common.swing.crud;

/**
 * La interfaz que define el comportamiento com�n a todos los contenedores de formularios.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface FormContainer {

	/**
	 * La funci�n encargada de desplegar el contenedor para mostrar el formulario que tiene en el interior.
	 */
	public void open();

	/**
	 * La funci�n encargada de cerrar el contenedor donde tenemos desplegado un formulario.
	 */
	public void close();
}
