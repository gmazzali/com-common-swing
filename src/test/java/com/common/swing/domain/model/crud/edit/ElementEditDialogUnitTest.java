package com.common.swing.domain.model.crud.edit;

import org.apache.log4j.BasicConfigurator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.swing.domain.model.crud.Constants;
import com.common.swing.domain.model.crud.edit.impl.ElementEditFormDialog;
import com.common.swing.domain.model.crud.edit.impl.ElementEditFormPanel;
import com.common.swing.domain.model.crud.model.Element;
import com.common.swing.domain.model.crud.model.ElementServiceImpl;

/**
 * La clase de prueba de la ventana de edición de entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementEditDialogUnitTest {

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * Pruebas sobre la ventana de edición de entidades.
	 */
	@Test
	public void pruebaDeEntityEditDialog() {
		System.out.println("<<<<<<<<<<<<<<<<< PRUEBAS DEL DIALOGO DE EDICIÓN DE ENTIDADES >>>>>>>>>>>>>>>>>");

		ElementEditFormPanel panel = new ElementEditFormPanel();
		panel.setService(new ElementServiceImpl());

		ElementEditFormDialog dialog = new ElementEditFormDialog();
		dialog.setPanel(panel);
		dialog.setResizable(false);
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);

		Element element = new Element();

		dialog = (ElementEditFormDialog) dialog.createNewForm();
		dialog.setVisible(Constants.visible);

		System.out.println();
		System.out.println("ELEMENTO -> " + dialog.getEntity());
		System.out.println();

		element = new Element();
		element.setName("Prueba");

		dialog = (ElementEditFormDialog) dialog.createEditForm(element);
		dialog.setVisible(Constants.visible);

		System.out.println();
		System.out.println("ELEMENTO -> " + dialog.getEntity());
		System.out.println("IGUALES? -> " + (dialog.getEntity() == element));
	}
}