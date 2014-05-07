package com.common.swing.view.notification.util;

import javax.swing.JOptionPane;

/**
 * La enumeraci�n que contiene todos los tipos de mensajes que podemos crear como notificaciones.
 * 
 * <pre>
 * {@link NotificactionType#PLAIN}
 * {@link NotificactionType#INFO}
 * {@link NotificactionType#WARNING}
 * {@link NotificactionType#ERROR}
 * {@link NotificactionType#CONFIRM}
 * </pre>
 * 
 * @see JOptionPane
 * 
 * @since 06/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum NotificactionType {

	/**
	 * El tipo de mensaje de texto plano.
	 */
	PLAIN(JOptionPane.PLAIN_MESSAGE),
	/**
	 * El tipo de mensaje de informaci�n.
	 */
	INFO(JOptionPane.INFORMATION_MESSAGE),
	/**
	 * El tipo de mensaje de advertencia.
	 */
	WARNING(JOptionPane.WARNING_MESSAGE),
	/**
	 * El tipo de mensaje de error.
	 */
	ERROR(JOptionPane.ERROR_MESSAGE),
	/**
	 * El tipo de mensaje de confirmaci�n.
	 */
	CONFIRM(JOptionPane.QUESTION_MESSAGE);

	/**
	 * El codigo del tipo.
	 */
	private Integer type;

	/**
	 * El constructor de un tipo de notificaci�n.
	 * 
	 * @param type
	 *            El c�digo del tipo de notificaci�n.
	 */
	private NotificactionType(Integer type) {
		this.type = type;
	}

	/**
	 * Retorna el c�digo del tipo de notificaci�n.
	 * 
	 * @return El c�digo del tipo de notificaci�n.
	 */
	public Integer getType() {
		return type;
	}
}