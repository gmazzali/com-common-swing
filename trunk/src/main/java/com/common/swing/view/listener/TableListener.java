package com.common.swing.view.listener;

import java.io.Serializable;

import com.common.swing.view.bean.RowBean;
import com.common.swing.view.event.TableEvent;

/**
 * Permite definir un escuchador de un evento de la tabla.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de los elementos que vamos a tener dentro de la tabla de este escuchador.
 */
public interface TableListener<E extends RowBean> extends Serializable {

	/**
	 * Permite disparar un evento en caso de una acción en una tabla.
	 * 
	 * @param tableEvent
	 *            El evento que vamos a disparar.
	 */
	public void fireEvent(TableEvent<E> tableEvent);
}