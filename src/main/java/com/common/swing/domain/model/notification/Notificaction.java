package com.common.swing.domain.model.notification;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import com.common.swing.domain.model.notification.util.NotificactionType;
import com.common.util.business.holder.HolderMessage;

/**
 * Nos permite crear los mensajes de notificación de manera personalizadas para los sistemas.
 * 
 * @see JOptionPane
 * 
 * @since 06/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
// TODO VER LO DE LOS ICONOS DE LOS MENSAJES.
public class Notificaction {

	/**
	 * Crea una ventana informativa con titulo definido por la clave <code>notification.message.info.title.default</code> y el mensaje definido por su
	 * clave.
	 * 
	 * @param component
	 *            El <code>Frame</code> sobre el que el mensaje se va a desplegar. Si este es <code>null</code> o si no tiene un <code>Frame</code>,
	 *            se usa un <code>Frame</code> por default.
	 * @param messageKey
	 *            La clave del mensaje que vamos a usar para la notificación.
	 * @param messageParameter
	 *            Los parámetros para mensaje.
	 */
	public static void showMessage(Component component, String messageKey, Object... messageParameter) {
		Notificaction.showMessage(component, "notification.message.info.title.default", messageKey, messageParameter);
	}

	/**
	 * Crea una ventana informativa donde vamos a desplegar el mensaje definido por su clave.
	 * 
	 * @param component
	 *            El <code>Frame</code> sobre el que el mensaje se va a desplegar. Si este es <code>null</code> o si no tiene un <code>Frame</code>,
	 *            se usa un <code>Frame</code> por default.
	 * @param titleKey
	 *            La clave del título de la ventana.
	 * @param messageKey
	 *            La clave del mensaje que vamos a usar para la notificación.
	 * @param messageParameter
	 *            Los parámetros para mensaje.
	 */
	public static void showMessage(Component component, String titleKey, String messageKey, Object... messageParameter) {
		Notificaction.showMessage(component, null, titleKey, messageKey, messageParameter);
	}

	/**
	 * Crea una ventana informativa donde vamos a desplegar el mensaje definido por su clave y todos los demás parámetros.
	 * 
	 * @param component
	 *            El <code>Frame</code> sobre el que el mensaje se va a desplegar. Si este es <code>null</code> o si no tiene un <code>Frame</code>,
	 *            se usa un <code>Frame</code> por default.
	 * @param icon
	 *            El icono que vamos a utilizar dentro de esta ventana.
	 * @param titleKey
	 *            La clave del título de la ventana.
	 * @param messageKey
	 *            La clave del mensaje que vamos a usar para la notificación.
	 * @param messageParameter
	 *            Los parámetros para mensaje.
	 */
	public static void showMessage(Component component, Icon icon, String titleKey, String messageKey, Object... messageParameter) {
		JOptionPane.showMessageDialog(component, HolderMessage.getMessage(messageKey, messageParameter), HolderMessage.getMessage(titleKey),
				NotificactionType.INFO.getType(), icon);
	}

	/**
	 * Crea una ventana informativa de un error con titulo definido por la clave <code>notification.message.error.title.default</code> y el mensaje
	 * definido por su clave.
	 * 
	 * @param component
	 *            El <code>Frame</code> sobre el que el mensaje se va a desplegar. Si este es <code>null</code> o si no tiene un <code>Frame</code>,
	 *            se usa un <code>Frame</code> por default.
	 * @param messageKey
	 *            La clave del mensaje de error que vamos a usar para la notificación.
	 * @param messageParameter
	 *            Los parámetros para mensaje de error.
	 */
	public static void showErrorMessage(Component component, String messageKey, Object... messageParameter) {
		Notificaction.showErrorMessage(component, "notification.message.error.title.default", messageKey, messageParameter);
	}

	/**
	 * Crea una ventana informativa de un error donde vamos a desplegar el mensaje definido por su clave.
	 * 
	 * @param component
	 *            El <code>Frame</code> sobre el que el mensaje se va a desplegar. Si este es <code>null</code> o si no tiene un <code>Frame</code>,
	 *            se usa un <code>Frame</code> por default.
	 * @param titleKey
	 *            La clave del título de la ventana.
	 * @param messageKey
	 *            La clave del mensaje de error que vamos a usar para la notificación.
	 * @param messageParameter
	 *            Los parámetros para mensaje de error.
	 */
	public static void showErrorMessage(Component component, String titleKey, String messageKey, Object... messageParameter) {
		Notificaction.showErrorMessage(component, null, titleKey, messageKey, messageParameter);
	}

	/**
	 * Crea una ventana informativa de un error donde vamos a desplegar el mensaje definido por su clave y todos los demás parámetros.
	 * 
	 * @param component
	 *            El <code>Frame</code> sobre el que el mensaje se va a desplegar. Si este es <code>null</code> o si no tiene un <code>Frame</code>,
	 *            se usa un <code>Frame</code> por default.
	 * @param icon
	 *            El icono que vamos a utilizar dentro de esta ventana.
	 * @param titleKey
	 *            La clave del título de la ventana.
	 * @param messageKey
	 *            La clave del mensaje de error que vamos a usar para la notificación.
	 * @param messageParameter
	 *            Los parámetros para mensaje de error.
	 */
	public static void showErrorMessage(Component component, Icon icon, String titleKey, String messageKey, Object... messageParameter) {
		JOptionPane.showMessageDialog(component, HolderMessage.getMessage(messageKey, messageParameter), HolderMessage.getMessage(titleKey),
				NotificactionType.ERROR.getType(), icon);
	}
}