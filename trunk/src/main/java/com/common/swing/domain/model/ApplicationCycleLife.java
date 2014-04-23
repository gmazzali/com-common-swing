package com.common.swing.domain.model;

import java.io.Serializable;

import com.common.util.domain.exception.UncheckedException;

/**
 * La interfaz que define el ciclo de vida de una aplicaci�n para controlar la misma.
 * 
 * @since 23/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ApplicationCycleLife extends Serializable {

	/**
	 * La funci�n que vamos a ejecutar antes de arrancar la aplicaci�n principal del sistema.
	 * 
	 * @throws UncheckedException
	 *             En caso de que ocurra un error a la hora de inicializar los componentes que vamos a utilizar dentro de la aplicaci�n, como ser el
	 *             contexto de o un fallo de inicio de sesi�n por parte de un usuario.
	 */
	public void beforeStartApplication() throws UncheckedException;

	/**
	 * La funci�n que ejecuta la aplicaci�n principal del sistema, esta utiliza el contexto creado anteriormente y carga las pantallas de la
	 * aplicaci�n.
	 * 
	 * @throws UncheckedException
	 *             En caso de que ocurra un error a la hora de levantar la aplicaci�n completa del sistema.
	 */
	public void startApplication() throws UncheckedException;

	/**
	 * La funci�n que se ejecuta cuando se cierra la aplicaci�n permitiendo dentro de esta cerrar archivos abiertos, conexiones establecidas y liberar
	 * diversos recursos tomados.
	 * 
	 * @throws UncheckedException
	 *             En caso de ocurrir un error al momento de liberar los recursos tomados por el sistema.
	 */
	public void afterStartApplication() throws UncheckedException;
}
