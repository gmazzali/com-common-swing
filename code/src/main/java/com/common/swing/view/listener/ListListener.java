package com.common.swing.view.listener;

import java.io.Serializable;

import com.common.swing.view.bean.RowBean;
import com.common.swing.view.event.ListEvent;

/**
 * Permite definir un escuchador de un evento de la lista.
 * 
 * @since 10/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de los elementos que vamos a tener dentro de la lista de este escuchador.
 */
public interface ListListener<E extends RowBean> extends Serializable {

	/**
	 * Permite disparar un evento en caso de una acción en una lista.
	 * 
	 * @param listEvent
	 *            El evento que vamos a disparar.
	 */
	public void fireEvent(ListEvent<E> listEvent);
}