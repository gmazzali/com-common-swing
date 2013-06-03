package com.commons.swing.abm.list;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.swing.abm.list.EntityListDialog;
import com.commons.swing.abm.Constants;
import com.commons.swing.abm.model.Element;

/**
 * La clase para probar
 * 
 * @author Guillermo Mazzali
 * 
 */
public class EntityListDialogUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * La ventana de listado de entidades que vamos a probar.
	 */
	private EntityListDialog<Element, Integer> dialog;

	/**
	 * Pruebas sobre la ventana de listado de entidades.
	 */
	@Test
	public void pruebaDeEntityEditDialog() {
		System.out.println("<<<<<<<<<<<<<<<<< PRUEBAS DEL DIALOGO DE LISTADO DE ENTIDADES >>>>>>>>>>>>>>>>>");

		this.dialog = new EntityListDialog<>();
		this.dialog.setVisible(Constants.visible);
	}
}
