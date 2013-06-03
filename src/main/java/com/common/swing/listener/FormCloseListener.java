package com.common.swing.listener;

/**
 * La interfaz que va a implementar una ventana para corroborar el estado en el que fue cerrada la misma de acuerdo a la acci�n que provoco su cierre.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface FormCloseListener {

	/**
	 * La enumeraci�n que tiene todas las posibles maneras de que una ventana sea cerrada dentro de un sistema, ya sea porque se cancel� la acci�n que
	 * estaba realiz�ndose sobre esta o si se acepto la acci�n misma y la ventana se cerr�.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public enum WindowCloseState {

		/**
		 * El elemento que define que la ventana fue cerrada sin llevar a cabo ninguna acci�n, por lo que es equivalente a hacer un click en la cruz
		 * superior derecha de la ventana para cerrarla.
		 */
		UNDEFINED,
		/**
		 * El elemento que define que la ventana fue cerrada cuando se cancel� la acci�n que estaba llev�ndose a cabo dentro de la misma.
		 */
		REJECT,
		/**
		 * El elemento que define que la ventana fue cerrada cuando se confirm� la acci�n que estaba llev�ndose a cabo dentro de la misma.
		 */
		COMMIT;
	}

	/**
	 * Funci�n encargada de retornar el estado en el que fue cerrada esta ventana para saber que tipo de acci�n vamso a llevar a cabo.
	 * 
	 * @return El estado en el que fue cerrada esta ventana.
	 */
	public WindowCloseState getCloseState();
}
