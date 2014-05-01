package com.common.swing.domain.model.crud.list;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.swing.domain.model.crud.edit.impl.ElementEditFormDialog;
import com.common.swing.domain.model.crud.edit.impl.ElementEditFormPanel;
import com.common.swing.domain.model.crud.list.impl.ElementListFormContainerDialog;
import com.common.swing.domain.model.crud.list.impl.ElementListFormPanel;
import com.common.swing.domain.model.crud.model.ElementServiceImpl;

/**
 * La clase de prueba de la ventana de listado de entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementListDialogUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * Pruebas sobre la ventana de listado de entidades.
	 */
	@Test
	public void pruebaDeEntityEditDialog() {
		System.out.println("<<<<<<<<<<<<<<<<< PRUEBAS DEL DIALOGO DE LISTADO DE ENTIDADES >>>>>>>>>>>>>>>>>");

		ElementServiceImpl service = new ElementServiceImpl();

		ElementEditFormPanel editPanel = new ElementEditFormPanel();
		editPanel.setEntityService(service);

		ElementEditFormDialog editDialog = new ElementEditFormDialog();
		editDialog.setPanel(editPanel);

		ElementListFormPanel listPanel = new ElementListFormPanel();
		listPanel.setElementService(service);
		listPanel.setEntityEditFormContainer(editDialog);

		ElementListFormContainerDialog listDialog = new ElementListFormContainerDialog();
		listDialog.setElementService(service);
		listDialog.setElementListFormPanel(listPanel);

		listPanel.setFormContainer(listDialog);

		listDialog.open();
	}
}