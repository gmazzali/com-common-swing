package com.common.swing.domain.model;

import java.io.Serializable;

import com.common.util.domain.exception.UncheckedException;

/**
 * La interfaz que define el ciclo de vida de una aplicación para controlar la misma.
 * 
 * @since 23/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ApplicationCycleLife extends Serializable {

	/**
	 * La función que vamos a ejecutar antes de arrancar la aplicación principal del sistema.
	 * 
	 * @throws UncheckedException
	 *             En caso de que ocurra un error a la hora de inicializar los componentes que vamos a utilizar dentro de la aplicación, como ser el
	 *             contexto de o un fallo de inicio de sesión por parte de un usuario.
	 */
	public void beforeStartApplication() throws UncheckedException;

	/**
	 * La función que ejecuta la aplicación principal del sistema, esta utiliza el contexto creado anteriormente y carga las pantallas de la
	 * aplicación.
	 * 
	 * @throws UncheckedException
	 *             En caso de que ocurra un error a la hora de levantar la aplicación completa del sistema.
	 */
	public void startApplication() throws UncheckedException;

	/**
	 * La función que se ejecuta cuando se cierra la aplicación permitiendo dentro de esta cerrar archivos abiertos, conexiones establecidas y liberar
	 * diversos recursos tomados.
	 * 
	 * @throws UncheckedException
	 *             En caso de ocurrir un error al momento de liberar los recursos tomados por el sistema.
	 */
	public void afterStartApplication() throws UncheckedException;
}
