package com.common.swing.view.event;

import java.io.Serializable;

/**
 * Permite definir un evento base.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            El bean que vamos a manejar dentro de este evento.
 */
public class BaseEvent<E extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;
}