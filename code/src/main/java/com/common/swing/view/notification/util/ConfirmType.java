package com.common.swing.view.notification.util;

import javax.swing.JOptionPane;

/**
 * La enumeraci�n que contiene todos los tipos de mensajes que podemos crear como notificaciones.
 * 
 * <pre>
 * {@link ConfirmType#DEFAULT}
 * {@link ConfirmType#YES_NO}
 * {@link ConfirmType#YES_NO_CANCEL}
 * {@link ConfirmType#OK_CANCEL}
 * </pre>
 * 
 * @see JOptionPane
 * 
 * @since 06/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum ConfirmType {

	/**
	 * El tipo de confirmaci�n por default.
	 */
	DEFAULT(JOptionPane.DEFAULT_OPTION),
	/**
	 * El tipo de mensajes de confirmaci�n con los botones de <b>SI</b>, <b>NO</b>.
	 */
	YES_NO(JOptionPane.YES_NO_OPTION),
	/**
	 * El tipo de mensajes de confirmaci�n con los botones de <b>SI</b>, <b>NO</b> y <b>CANCELAR</b>.
	 */
	YES_NO_CANCEL(JOptionPane.YES_NO_CANCEL_OPTION),
	/**
	 * El tipo de mensajes de confirmaci�n con los botones de <b>OK</b>, <b>CANCELAR</b>.
	 */
	OK_CANCEL(JOptionPane.OK_CANCEL_OPTION);

	/**
	 * El codigo del tipo de mensaje de confirmaci�n.
	 */
	private Integer type;

	/**
	 * El constructor de un tipo de mensaje de confirmaci�n.
	 * 
	 * @param type
	 *            El c�digo del tipo de mensaje de confirmaci�n.
	 */
	private ConfirmType(Integer type) {
		this.type = type;
	}

	/**
	 * Retorna el c�digo del tipo de mensaje de confirmaci�n.
	 * 
	 * @return El c�digo del tipo de mensaje de confirmaci�n.
	 */
	public Integer getType() {
		return type;
	}
}