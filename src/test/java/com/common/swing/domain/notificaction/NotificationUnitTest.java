package com.common.swing.domain.notificaction;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.swing.view.notification.Notificaction;
import com.common.swing.view.notification.util.ConfirmReturnType;
import com.common.swing.view.notification.util.ConfirmType;

/**
 * Las pruebas sobre los mensajes de notificaci�n.
 * 
 * @since 07/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class NotificationUnitTest {

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * Las pruebas sobre el mensaje de informaci�n.
	 */
	@Test
	public void testShowMessage() {
		Notificaction.showMessage(null, "mensaje de informaci�n");
	}

	/**
	 * Las pruebas sobre el mensaje de error.
	 */
	@Test
	public void testShowErrorMessage() {
		Notificaction.showErrorMessage(null, "mensaje de error");
	}

	/**
	 * Las pruebas sobre el mensaje de confirmaci�n.
	 */
	@Test
	public void testShowConfirmMessage() {
		Assert.assertEquals(ConfirmReturnType.CLOSE, Notificaction.showConfirmMessage(null, ConfirmType.YES_NO, "mensaje de confirmaci�n por cierre"));

		Assert.assertEquals(ConfirmReturnType.YES, Notificaction.showConfirmMessage(null, ConfirmType.YES_NO, "mensaje de confirmaci�n positiva"));

		Assert.assertEquals(ConfirmReturnType.NO, Notificaction.showConfirmMessage(null, ConfirmType.YES_NO, "mensaje de confirmaci�n negativa"));

		Assert.assertEquals(ConfirmReturnType.CANCEL,
				Notificaction.showConfirmMessage(null, ConfirmType.YES_NO_CANCEL, "mensaje de confirmaci�n de cancelaci�n"));
	}
}