package com.common.swing.view.notification.util;

import javax.swing.JOptionPane;

/**
 * La enumeración que contiene todos los tipos de retornos que podemos obtener de un mensjes de confirmación.
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
	 * El retorno por presionar el botón de <b>SI</b>.
	 */
	YES(JOptionPane.YES_OPTION),
	/**
	 * El retorno por presionar el botón de <b>NO</b>.
	 */
	NO(JOptionPane.NO_OPTION),
	/**
	 * El retorno por presionar el botón de <b>OK</b>.
	 */
	OK(JOptionPane.OK_OPTION),
	/**
	 * El retorno por presionar el botón de <b>CANCELAR</b>.
	 */
	CANCEL(JOptionPane.CANCEL_OPTION);

	/**
	 * Permite mapear el retorno de un mensaje de confirmación con el tipo correspondiente.
	 * 
	 * @param type
	 *            El retorno que devuelve un mensaje de confirmación.
	 * @return El tipo de confirmación correspondiente al recibido.
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
	 * El codigo del tipo de retorno de confirmación.
	 */
	private Integer type;

	/**
	 * El constructor de un tipo de retorno de confirmación.
	 * 
	 * @param type
	 *            El código del tipo de retorno de confirmación.
	 */
	private ConfirmReturnType(Integer type) {
		this.type = type;
	}

	/**
	 * Retorna el código del tipo de retorno de confirmación.
	 * 
	 * @return El código del tipo de retorno de confirmación.
	 */
	public Integer getType() {
		return type;
	}
}