package com.common.swing.view.listener;

import java.io.Serializable;

import com.common.swing.view.bean.SearchBean;
import com.common.swing.view.event.SearchEvent;

/**
 * Permite definir un escuchador de un evento de un filtro.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de los elementos que vamos a recuperar con el filtro.
 */
public interface SearchListener<E extends SearchBean> extends Serializable {

	/**
	 * Permite disparar un evento en caso de una acción en un filtro.
	 * 
	 * @param filterEvent
	 *            El evento que vamos a disparar.
	 */
	public void fireEvent(SearchEvent<E> filterEvent);
}