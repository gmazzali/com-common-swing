package com.common.swing.listener;

/**
 * La interfaz que va a implementar una ventana para corroborar el estado en el que fue cerrada la misma de acuerdo a la acción que provoco su cierre.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface FormCloseListener {

	/**
	 * La enumeración que tiene todas las posibles maneras de que una ventana sea cerrada dentro de un sistema, ya sea porque se canceló la acción que
	 * estaba realizándose sobre esta o si se acepto la acción misma y la ventana se cerró.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public enum WindowCloseState {

		/**
		 * El elemento que define que la ventana fue cerrada sin llevar a cabo ninguna acción, por lo que es equivalente a hacer un click en la cruz
		 * superior derecha de la ventana para cerrarla.
		 */
		UNDEFINED,
		/**
		 * El elemento que define que la ventana fue cerrada cuando se canceló la acción que estaba llevándose a cabo dentro de la misma.
		 */
		REJECT,
		/**
		 * El elemento que define que la ventana fue cerrada cuando se confirmó la acción que estaba llevándose a cabo dentro de la misma.
		 */
		COMMIT;
	}

	/**
	 * Función encargada de retornar el estado en el que fue cerrada esta ventana para saber que tipo de acción vamso a llevar a cabo.
	 * 
	 * @return El estado en el que fue cerrada esta ventana.
	 */
	public WindowCloseState getCloseState();
}
