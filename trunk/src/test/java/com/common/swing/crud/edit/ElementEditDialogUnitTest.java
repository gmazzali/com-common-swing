package com.common.swing.crud.edit;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.swing.crud.Constants;
import com.common.swing.crud.edit.impl.ElementEditFormContainerDialog;
import com.common.swing.crud.edit.impl.ElementEditFormPanel;
import com.common.swing.crud.model.Element;
import com.common.swing.crud.model.ElementServiceImpl;
import com.common.util.service.GenericService;

/**
 * La clase de prueba de la ventana de edición de entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementEditDialogUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
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

		GenericService<Element, Integer> service = new ElementServiceImpl();

		ElementEditFormPanel panel = new ElementEditFormPanel();
		panel.setEntityService(service);

		ElementEditFormContainerDialog dialog = new ElementEditFormContainerDialog();
		dialog.setPanel(panel);
		dialog.setResizable(false);
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);

		Element element = new Element();

		dialog = (ElementEditFormContainerDialog) dialog.createNewForm();
		dialog.setVisible(Constants.visible);

		System.out.println();
		System.out.println("ELEMENTO -> " + dialog.getEntity());
		System.out.println();

		element = new Element();
		element.setName("Prueba");

		dialog = (ElementEditFormContainerDialog) dialog.createEditForm(element);
		dialog.setVisible(Constants.visible);

		System.out.println();
		System.out.println("ELEMENTO -> " + dialog.getEntity());
		System.out.println("IGUALES? -> " + (dialog.getEntity() == element));
	}
}
