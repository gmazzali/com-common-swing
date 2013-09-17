package com.common.swing.model;

/**
 * La interfaz que define el ciclo de vida de una aplicaci�n para controlar la misma.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ApplicationCycleLife {

	/**
	 * La funci�n que vamos a ejecutar antes de arrancar la aplicaci�n principal del sistema.
	 * 
	 * @throws Exception
	 *             En caso de que ocurra un error a la hora de inicializar los componentes que vamos a utilizar dentro de la aplicaci�n, como ser el
	 *             contexto de o un fallo de inicio de sesi�n por parte de un usuario.
	 */
	public void beforeStartApplication() throws Exception;

	/**
	 * La funci�n que ejecuta la aplicaci�n principal del sistema, esta utiliza el contexto creado anteriormente y carga las pantallas de la
	 * aplicaci�n.
	 * 
	 * @throws Exception
	 *             En caso de que ocurra un error a la hora de levantar la aplicaci�n completa del sistema.
	 */
	public void startApplication() throws Exception;

	/**
	 * La funci�n que se ejecuta cuando se cierra la aplicaci�n permitiendo dentro de esta cerrar archivos abiertos, conexiones establecidas y liberar
	 * diversos recursos tomados.
	 * 
	 * @throws Exception
	 *             En caso de ocurrir un error al momento de liberar los recursos tomados por el sistema.
	 */
	public void afterStartApplication() throws Exception;
}
