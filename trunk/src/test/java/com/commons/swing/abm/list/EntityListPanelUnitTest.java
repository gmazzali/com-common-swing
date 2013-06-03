package com.commons.swing.abm.list;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.swing.abm.list.EntityListDialog;
import com.commons.swing.abm.Constants;
import com.commons.swing.abm.edit.ElementEditPanel;
import com.commons.swing.abm.model.Element;
import com.commons.swing.abm.model.ElementServiceImpl;

/**
 * La clase que nos permite probar el panel que despliega el listado de las entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class EntityListPanelUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * El panel del listado de las entidades.
	 */
	private ElementListPanel listPanel;
	/**
	 * El panel de edición de las entidades.
	 */
	private ElementEditPanel editPanel;

	@Test
	public void pruebaDeVentanaDeListado() {
		System.out.println("<<<<<<<<<<<<<<<<<< PRUEBAS DEL PANEL DE LISTADO DE ENTIDADES >>>>>>>>>>>>>>>>>>");

		ElementServiceImpl service = new ElementServiceImpl();

		this.listPanel = new ElementListPanel();
		this.listPanel.setEntityService(service);

		this.editPanel = new ElementEditPanel();
		this.editPanel.setEntityService(service);

		this.listPanel.setEntityEditPanel(this.editPanel);

		EntityListDialog<Element, Integer> dialogo = new EntityListDialog<>();
		dialogo.setEntityListPanel(this.listPanel);
		dialogo.setVisible(Constants.visible);

	}
}
