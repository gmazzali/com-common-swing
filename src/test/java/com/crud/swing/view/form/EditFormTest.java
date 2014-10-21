package com.crud.swing.view.form;

import java.awt.BorderLayout;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.common.swing.view.callback.CallbackForm;
import com.crud.swing.model.Element;
import com.crud.swing.model.ElementServiceImpl;
import com.crud.swing.view.container.impl.DialogContainer;
import com.crud.swing.view.util.FormTypeEnum;

/**
 * La clase de prueba de la ventana de edición de entidades.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class EditFormTest {

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * Pruebas sobre el alta de una entidad.
	 */
	@Test
	public void pruebaDeAlta() {
		ElementEditForm panel = new ElementEditForm();
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
		ElementEditForm panel = new ElementEditForm();
		panel.setService(new ElementServiceImpl());

		Element element = new Element(1, "NAME");

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