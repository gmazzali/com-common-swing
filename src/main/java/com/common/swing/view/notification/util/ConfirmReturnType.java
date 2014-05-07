package com.common.swing.view.notification.util;

import javax.swing.JOptionPane;

/**
 * La enumeraci�n que contiene todos los tipos de retornos que podemos obtener de un mensjes de confirmaci�n.
 * 
 * <pre>
 * {@link ConfirmReturnType#CLOSE}
 * {@link ConfirmReturnType#YES}
 * {@link ConfirmReturnType#NO}
 * {@link ConfirmReturnType#OK}
 * {@link ConfirmReturnType#CANCEL}
 * </pre>
 * 
 * @see JOptionPane
 * 
 * @since 06/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum ConfirmReturnType {

	/**
	 * El retorno por cierre de la ventana.
	 */
	CLOSE(JOptionPane.CLOSED_OPTION),
	/**
	 * El retorno por presionar el bot�n de <b>SI</b>.
	 */
	YES(JOptionPane.YES_OPTION),
	/**
	 * El retorno por presionar el bot�n de <b>NO</b>.
	 */
	NO(JOptionPane.NO_OPTION),
	/**
	 * El retorno por presionar el bot�n de <b>OK</b>.
	 */
	OK(JOptionPane.OK_OPTION),
	/**
	 * El retorno por presionar el bot�n de <b>CANCELAR</b>.
	 */
	CANCEL(JOptionPane.CANCEL_OPTION);

	/**
	 * Permite mapear el retorno de un mensaje de confirmaci�n con el tipo correspondiente.
	 * 
	 * @param type
	 *            El retorno que devuelve un mensaje de confirmaci�n.
	 * @return El tipo de confirmaci�n correspondiente al recibido.
	 */
	public static ConfirmReturnType get(Integer type) {
		if (type != null) {
			switch (type) {
			case JOptionPane.CLOSED_OPTION:
				return CLOSE;
			case JOptionPane.YES_OPTION:
				return YES;
			case JOptionPane.NO_OPTION:
				return NO;
				// case JOptionPane.OK_OPTION:
				// return OK;
			case JOptionPane.CANCEL_OPTION:
				return CANCEL;
			}
		}
		return null;
	}

	/**
	 * El codigo del tipo de retorno de confirmaci�n.
	 */
	private Integer type;

	/**
	 * El constructor de un tipo de retorno de confirmaci�n.
	 * 
	 * @param type
	 *            El c�digo del tipo de retorno de confirmaci�n.
	 */
	private ConfirmReturnType(Integer type) {
		this.type = type;
	}

	/**
	 * Retorna el c�digo del tipo de retorno de confirmaci�n.
	 * 
	 * @return El c�digo del tipo de retorno de confirmaci�n.
	 */
	public Integer getType() {
		return type;
	}
}