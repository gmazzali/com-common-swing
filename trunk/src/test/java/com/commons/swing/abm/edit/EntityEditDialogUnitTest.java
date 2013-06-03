package com.commons.swing.abm.edit;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.swing.abm.edit.EntityEditDialog;
import com.commons.swing.abm.Constants;
import com.commons.swing.abm.model.Element;

/**
 * La clase de prueba de la ventana de edici�n de entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class EntityEditDialogUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * La ventana de edici�n de entidades que vamos a probar.
	 */
	private EntityEditDialog<Element, Integer> dialog;

	/**
	 * Pruebas sobre la ventana de edici�n de entidades.
	 */
	@Test
	public void pruebaDeEntityEditDialog() {
		System.out.println("<<<<<<<<<<<<<<<<< PRUEBAS DEL DIALOGO DE EDICI�N DE ENTIDADES >>>>>>>>>>>>>>>>>");

		EntityEditDialog<Element, Integer> d = new EntityEditDialog<>();

		this.dialog = d.getNewEntityDialog(new Element());
		this.dialog.setVisible(Constants.visible);

		this.dialog = d.getEditEntityDialog(new Element());
		this.dialog.setVisible(Constants.visible);
	}
}
