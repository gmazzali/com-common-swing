package com.common.swing.view.event;

import com.common.swing.view.bean.SearchBean;

/**
 * Permite definir un evento de un filtro de b�squeda.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de elementos que contiene el filtro que disparo el evento.
 */
public class SearchEvent<E extends SearchBean> extends BaseEvent<E> {
	private static final long serialVersionUID = 1L;
}