package com.commons.swing.abm.edit;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.swing.abm.edit.EntityEditDialog;
import com.common.util.service.GenericService;
import com.commons.swing.abm.Constants;
import com.commons.swing.abm.model.Element;
import com.commons.swing.abm.model.ElementServiceImpl;

/**
 * La clase que nos permite probar el panel de edición de entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class EntityEditPanelTestUnit {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * El dialogo que va a contener el panel.
	 */
	private EntityEditDialog<Element, Integer> dialog;

	/**
	 * La función encargada de probar el panel de edición.
	 */
	@Test
	public void pruebaSobrePanelDeEdicion() {
		System.out.println("<<<<<<<<<<<<<<<<<< PRUEBAS DEL PANEL DE EDICIÓN DE ENTIDADES >>>>>>>>>>>>>>>>>>");

		GenericService<Element, Integer> service = new ElementServiceImpl();
		Element element = new Element();

		EntityEditDialog<Element, Integer> dialogo = new EntityEditDialog<Element, Integer>();
		ElementEditPanel panel = new ElementEditPanel();
		panel.setEntityService(service);

		dialogo.setEntityEditPanel(panel);

		System.out.println("SALIDA: " + element);

		this.dialog = dialogo.getNewEntityDialog(element);
		this.dialog.setVisible(Constants.visible);

		System.out.println("SALIDA: " + element);

		element.setName("Nombre de prueba");

		System.out.println();
		System.out.println("SALIDA: " + element);

		this.dialog = dialogo.getEditEntityDialog(element);
		this.dialog.setVisible(Constants.visible);

		System.out.println("SALIDA: " + element);
	}
}
