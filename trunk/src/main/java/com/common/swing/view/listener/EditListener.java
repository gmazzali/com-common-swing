package com.common.swing.view.listener;

import java.io.Serializable;

import com.common.swing.view.bean.EditBean;
import com.common.swing.view.event.EditEvent;

/**
 * Permite definir un escuchador de un evento de edición.
 * 
 * @since 27/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de los elementos que vamos a tener dentro del panel de edición.
 */
public interface EditListener<E extends EditBean<?>> extends Serializable {

	/**
	 * Permite disparar un evento en caso de una acción en la edición.
	 * 
	 * @param editEvent
	 *            El evento que vamos a disparar.
	 */
	public void fireEvent(EditEvent<E> editEvent);
}