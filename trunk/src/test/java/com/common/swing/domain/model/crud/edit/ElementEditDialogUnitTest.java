package com.common.swing.domain.model.crud.edit;

import java.awt.BorderLayout;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.swing.domain.model.crud.container.impl.DialogContainer;
import com.common.swing.domain.model.crud.edit.impl.ElementEditPanel;
import com.common.swing.domain.model.crud.model.Element;
import com.common.swing.domain.model.crud.model.ElementServiceImpl;
import com.common.swing.domain.model.crud.util.CallbackForm;
import com.common.swing.domain.model.crud.util.FormTypeEnum;

/**
 * La clase de prueba de la ventana de edición de entidades.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementEditDialogUnitTest {

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * Pruebas sobre el alta de una entidad.
	 */
	@Test
	public void pruebaDeAlta() {
		ElementEditPanel panel = new ElementEditPanel();
		panel.setService(new ElementServiceImpl());

		final DialogContainer dialog = new DialogContainer();
		dialog.getContentPane().removeAll();
		dialog.getContentPane().add(panel, BorderLayout.CENTER);
		dialog.pack();
		dialog.setResizable(false);
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);

		panel.createForm(dialog, FormTypeEnum.NEW, new CallbackForm() {

			private static final long serialVersionUID = 1L;

			@Override
			public void reject() {
				dialog.dispose();
			}

			@Override
			public void confirm() {
				dialog.dispose();
			}
		});

		dialog.setVisible(true);

		System.out.println();
		System.out.println("ELEMENTO -> " + panel.getEntity());
		System.out.println();
	}

	/**
	 * Pruebas sobre la edición de una entidad.
	 */
	@Test
	public void pruebaDeEdicion() {
		ElementEditPanel panel = new ElementEditPanel();
		panel.setService(new ElementServiceImpl());

		Element element = new Element();
		element.setName("NAME");

		panel.setEntity(element);

		final DialogContainer dialog = new DialogContainer();
		dialog.getContentPane().removeAll();
		dialog.getContentPane().add(panel, BorderLayout.CENTER);
		dialog.pack();
		dialog.setResizable(false);
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);

		panel.createForm(dialog, FormTypeEnum.EDIT, new CallbackForm() {

			private static final long serialVersionUID = 1L;

			@Override
			public void reject() {
				dialog.dispose();
			}

			@Override
			public void confirm() {
				dialog.dispose();
			}
		});

		dialog.setVisible(true);

		System.out.println();
		System.out.println("ELEMENTO -> " + panel.getEntity());
		System.out.println();
	}
}