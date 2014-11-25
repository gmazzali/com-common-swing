package com.common.swing.view.notification;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import com.common.swing.view.notification.util.ConfirmReturnType;
import com.common.swing.view.notification.util.ConfirmType;
import com.common.swing.view.notification.util.NotificactionType;
import com.common.util.business.service.MessageService;
import com.common.util.domain.exception.error.ErrorDetail;
import com.common.util.domain.exception.error.Errors;

/**
 * Nos permite crear los mensajes de notificación de manera personalizadas para los sistemas.
 * 
 * @see JOptionPane
 * 
 * @since 06/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Notificaction {

	/**
	 * El servicio para los mensajes.
	 */
	private static MessageService messageService;

	/**
	 * Permite cargar el servicio de los mensajes.
	 * 
	 * @param messageService
	 *            El servicio para los mensajes.
	 */
	public void setMessageService(MessageService messageService) {
		Notificaction.messageService = messageService;
	}

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
		JOptionPane.showMessageDialog(component, messageService.getMessage(messageKey, messageParameter), messageService.getMessage(titleKey),
				NotificactionType.INFO.getType(), icon);
	}

	/**
	 * Crea una ventana de error con titulo definido por la clave <code>notification.message.error.title.default</code> y el mensaje definido por su
	 * clave.
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
	 * Crea una ventana de error donde vamos a desplegar el mensaje definido por su clave.
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
	 * Crea una ventana de error donde vamos a desplegar el mensaje definido por su clave y todos los demás parámetros.
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
		JOptionPane.showMessageDialog(component, messageService.getMessage(messageKey, messageParameter), messageService.getMessage(titleKey),
				NotificactionType.ERROR.getType(), icon);
	}

	/**
	 * Crea una ventana de error con titulo definido por la clave <code>notification.message.error.title.default</code> y el mensaje definido por los
	 * errores.
	 * 
	 * @param component
	 *            El <code>Frame</code> sobre el que el mensaje se va a desplegar. Si este es <code>null</code> o si no tiene un <code>Frame</code>,
	 *            se usa un <code>Frame</code> por default.
	 * @param errors
	 *            El conjunto de errores.
	 */
	public static void showErrorMessage(Component component, Errors errors) {
		Notificaction.showErrorMessage(component, "notification.message.error.title.default", errors);
	}

	/**
	 * Crea una ventana de error donde vamos a desplegar el mensaje definido por el conjunto de errores.
	 * 
	 * @param component
	 *            El <code>Frame</code> sobre el que el mensaje se va a desplegar. Si este es <code>null</code> o si no tiene un <code>Frame</code>,
	 *            se usa un <code>Frame</code> por default.
	 * @param titleKey
	 *            La clave del título de la ventana.
	 * @param errors
	 *            El conjunto de errores.
	 */
	public static void showErrorMessage(Component component, String titleKey, Errors errors) {
		Notificaction.showErrorMessage(component, null, titleKey, errors);
	}

	/**
	 * Crea una ventana de error donde vamos a desplegar el mensaje definido por el conjunto de errores.
	 * 
	 * @param component
	 *            El <code>Frame</code> sobre el que el mensaje se va a desplegar. Si este es <code>null</code> o si no tiene un <code>Frame</code>,
	 *            se usa un <code>Frame</code> por default.
	 * @param icon
	 *            El icono que vamos a utilizar dentro de esta ventana.
	 * @param titleKey
	 *            La clave del título de la ventana.
	 * @param errors
	 *            El conjunto de errores.
	 */
	public static void showErrorMessage(Component component, Icon icon, String titleKey, Errors errors) {
		StringBuffer buffer = new StringBuffer();
		for (ErrorDetail errorDetail : errors.getErrorDetails()) {
			buffer.append(messageService.getMessage(errorDetail.getDefaultMessage(), errorDetail.getKeyMessage(), errorDetail.getParameters()));
			buffer.append("\n");
		}
		JOptionPane.showMessageDialog(component, buffer.toString(), messageService.getMessage(titleKey), NotificactionType.ERROR.getType(), icon);
	}

	/**
	 * Crea una ventana de confirmación con titulo definido por la clave <code>notification.message.confirm.title.default</code> y el mensaje definido
	 * por su clave.
	 * 
	 * @param component
	 *            El <code>Frame</code> sobre el que el mensaje se va a desplegar. Si este es <code>null</code> o si no tiene un <code>Frame</code>,
	 *            se usa un <code>Frame</code> por default.
	 * @param confirmType
	 *            El tipo de mensaje de confirmación que vamos a crear. Si este es <code>null</code>, se crea un mensaje de tipo
	 *            {@link ConfirmType#DEFAULT}.
	 * @param messageKey
	 *            La clave del mensaje de confirmación que vamos a usar para la notificación.
	 * @param messageParameter
	 *            Los parámetros para mensaje de confirmación.
	 * @return El tipo de opción que ingreso el usuario.
	 */
	public static ConfirmReturnType showConfirmMessage(Component component, ConfirmType confirmType, String messageKey, Object... messageParameter) {
		return Notificaction.showConfirmMessage(component, confirmType, "notification.message.confirm.title.default", messageKey, messageParameter);
	}

	/**
	 * Crea una ventana de confirmación donde vamos a desplegar el mensaje definido por su clave.
	 * 
	 * @param component
	 *            El <code>Frame</code> sobre el que el mensaje se va a desplegar. Si este es <code>null</code> o si no tiene un <code>Frame</code>,
	 *            se usa un <code>Frame</code> por default.
	 * @param confirmType
	 *            El tipo de mensaje de confirmación que vamos a crear. Si este es <code>null</code>, se crea un mensaje de tipo
	 *            {@link ConfirmType#DEFAULT}.
	 * @param titleKey
	 *            La clave del título de la ventana.
	 * @param messageKey
	 *            La clave del mensaje de confirmación que vamos a usar para la notificación.
	 * @param messageParameter
	 *            Los parámetros para mensaje de confirmación.
	 * @return El tipo de opción que ingreso el usuario.
	 */
	public static ConfirmReturnType showConfirmMessage(Component component, ConfirmType confirmType, String titleKey, String messageKey,
			Object... messageParameter) {
		return Notificaction.showConfirmMessage(component, confirmType, null, titleKey, messageKey, messageParameter);
	}

	/**
	 * Crea una ventana de confirmación donde vamos a desplegar el mensaje definido por su clave y todos los demás parámetros.
	 * 
	 * @param component
	 *            El <code>Frame</code> sobre el que el mensaje se va a desplegar. Si este es <code>null</code> o si no tiene un <code>Frame</code>,
	 *            se usa un <code>Frame</code> por default.
	 * @param confirmType
	 *            El tipo de mensaje de confirmación que vamos a crear. Si este es <code>null</code>, se crea un mensaje de tipo
	 *            {@link ConfirmType#DEFAULT}.
	 * @param icon
	 *            El icono que vamos a utilizar dentro de esta ventana.
	 * @param titleKey
	 *            La clave del título de la ventana.
	 * @param messageKey
	 *            La clave del mensaje de confirmación que vamos a usar para la notificación.
	 * @param messageParameter
	 *            Los parámetros para mensaje de confirmación.
	 * @return El tipo de opción que ingreso el usuario.
	 */
	public static ConfirmReturnType showConfirmMessage(Component component, ConfirmType confirmType, Icon icon, String titleKey, String messageKey,
			Object... messageParameter) {
		if (confirmType == null) {
			confirmType = ConfirmType.DEFAULT;
		}
		return ConfirmReturnType.get(JOptionPane.showConfirmDialog(component, messageService.getMessage(messageKey, messageParameter),
				messageService.getMessage(titleKey), confirmType.getType(), NotificactionType.CONFIRM.getType(), icon));
	}
}