package com.common.swing.view.event;

import com.common.swing.view.bean.EditBean;

/**
 * Permite definir un evento de un panel de edición.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de elementos que contiene el filtro que disparo el evento.
 */
public class EditEvent<E extends EditBean<?>> extends BaseEvent<E> {
	private static final long serialVersionUID = 1L;
}