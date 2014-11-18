package com.common.swing.domain.notificaction;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.swing.view.Constants;
import com.common.swing.view.notification.Notificaction;
import com.common.swing.view.notification.util.ConfirmReturnType;
import com.common.swing.view.notification.util.ConfirmType;

/**
 * Las pruebas sobre los mensajes de notificación.
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
	 * Las pruebas sobre el mensaje de información.
	 */
	@Test
	public void testShowMessage() {
		if (Constants.visible) {
			Notificaction.showMessage(null, "mensaje de información");
		}
	}

	/**
	 * Las pruebas sobre el mensaje de error.
	 */
	@Test
	public void testShowErrorMessage() {
		if (Constants.visible) {
			Notificaction.showErrorMessage(null, "mensaje de error");
		}
	}

	/**
	 * Las pruebas sobre el mensaje de confirmación.
	 */
	@Test
	public void testShowConfirmMessage() {
		if (Constants.visible) {
			Assert.assertEquals(ConfirmReturnType.CLOSE,
					Notificaction.showConfirmMessage(null, ConfirmType.YES_NO, "mensaje de confirmación por cierre"));

			Assert.assertEquals(ConfirmReturnType.YES, Notificaction.showConfirmMessage(null, ConfirmType.YES_NO, "mensaje de confirmación positiva"));

			Assert.assertEquals(ConfirmReturnType.NO, Notificaction.showConfirmMessage(null, ConfirmType.YES_NO, "mensaje de confirmación negativa"));

			Assert.assertEquals(ConfirmReturnType.CANCEL,
					Notificaction.showConfirmMessage(null, ConfirmType.YES_NO_CANCEL, "mensaje de confirmación de cancelación"));
		}
	}
}