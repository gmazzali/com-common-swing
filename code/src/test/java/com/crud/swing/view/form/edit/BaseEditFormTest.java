package com.crud.swing.view.form.edit;

import java.awt.BorderLayout;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.common.swing.domain.model.Element;
import com.common.swing.view.Constants;
import com.common.swing.view.callback.CallbackEdit;
import com.common.swing.view.component.edit.ElementEditForm;
import com.crud.swing.view.container.impl.DialogContainer;
import com.crud.swing.view.form.util.EditType;

/**
 * La clase de prueba de la ventana de edición de entidades.
 * 
 * @since 27/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseEditFormTest {

	@InjectMocks
	private ElementEditForm panel;

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * Pruebas sobre un formulario de alta.
	 */
	@Test
	public void testNewElement() {
		final DialogContainer dialog = new DialogContainer();
		
		final Element bean = new Element();

		CallbackEdit<Element> callback = new CallbackEdit<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void confirm(Element entity) {
				System.out.println(entity);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				dialog.dispose();
			}

			@Override
			public void cancel() {
				System.out.println(bean);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				dialog.dispose();
			}
		};

		this.panel.initForm(dialog, callback, bean, EditType.NEW);
		dialog.getContentPane().removeAll();
		dialog.getContentPane().add(this.panel, BorderLayout.CENTER);
		dialog.pack();
		dialog.setModal(true);
		dialog.setAlwaysOnTop(Constants.alwaysTop);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(Constants.visible);
	}

	/**
	 * Pruebas sobre un formulario de visualización.
	 */
	@Test
	public void testViewElement() {
		final DialogContainer dialog = new DialogContainer();

		final Element bean = new Element();
		bean.setName("elemento");

		CallbackEdit<Element> callback = new CallbackEdit<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void confirm(Element entity) {
				System.out.println(entity);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				dialog.dispose();
			}

			@Override
			public void cancel() {
				System.out.println(bean);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				dialog.dispose();
			}
		};

		this.panel.initForm(dialog, callback, bean, EditType.VIEW);
		dialog.getContentPane().removeAll();
		dialog.getContentPane().add(this.panel, BorderLayout.CENTER);
		dialog.pack();
		dialog.setModal(true);
		dialog.setAlwaysOnTop(Constants.alwaysTop);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(Constants.visible);
	}

	/**
	 * Pruebas sobre un formulario de edición.
	 */
	@Test
	public void testEditElement() {
		final DialogContainer dialog = new DialogContainer();

		final Element bean = new Element();
		bean.setName("nombre");
		
		CallbackEdit<Element> callback = new CallbackEdit<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void confirm(Element entity) {
				System.out.println(entity);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				dialog.dispose();
			}

			@Override
			public void cancel() {
				System.out.println(bean);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				dialog.dispose();
			}
		};

		this.panel.initForm(dialog, callback, bean, EditType.EDIT);
		dialog.getContentPane().removeAll();
		dialog.getContentPane().add(this.panel, BorderLayout.CENTER);
		dialog.pack();
		dialog.setModal(true);
		dialog.setAlwaysOnTop(Constants.alwaysTop);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(Constants.visible);
	}
}