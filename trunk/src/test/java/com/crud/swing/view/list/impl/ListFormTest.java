package com.crud.swing.view.list.impl;

import java.awt.BorderLayout;
import java.util.Collection;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.common.swing.view.action.TableAction;
import com.common.util.persistence.filter.BaseFilter;
import com.crud.swing.model.Element;
import com.crud.swing.model.ElementServiceImpl;
import com.crud.swing.view.container.impl.DialogContainer;

/**
 * La clase de prueba de la ventana de listado de entidades.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ListFormTest {

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * Pruebas sobreun listado sin acciones.
	 */
	@Test
	public void testListFormWithoutTableAction() {
		ElementServiceImpl service = new ElementServiceImpl();
		service.save(new Element("ELEMENTO 1"));
		service.save(new Element("ELEMENTO 2"));
		service.save(new Element("ELEMENTO 3"));
		service.save(new Element("ELEMENTO 4"));
		service.save(new Element("ELEMENTO 5"));
		service.save(new Element("ELEMENTO 6"));
		service.save(new Element("ELEMENTO 7"));
		service.save(new Element("ELEMENTO 8"));
		service.save(new Element("ELEMENTO 9"));
		service.save(new Element("ELEMENTO 0"));

		ElementListForm panel = new ElementListForm() {
			private static final long serialVersionUID = 1L;

			@Override
			protected Collection<TableAction<Element>> getTableActions() {
				return null;
			}
		};

		panel.setEntities(service.findByFilter(new BaseFilter<Element, Integer>()));

		final DialogContainer dialog = new DialogContainer();
		dialog.getContentPane().removeAll();
		dialog.getContentPane().add(panel, BorderLayout.CENTER);
		dialog.pack();
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	/**
	 * Pruebas sobre un listado sin acciones.
	 */
	@Test
	public void testListFormWithTableAction() {
		ElementServiceImpl service = new ElementServiceImpl();
		service.save(new Element("ELEMENTO 1"));
		service.save(new Element("ELEMENTO 2"));
		service.save(new Element("ELEMENTO 3"));
		service.save(new Element("ELEMENTO 4"));
		service.save(new Element("ELEMENTO 5"));
		service.save(new Element("ELEMENTO 6"));
		service.save(new Element("ELEMENTO 7"));
		service.save(new Element("ELEMENTO 8"));
		service.save(new Element("ELEMENTO 9"));
		service.save(new Element("ELEMENTO 0"));

		ElementListForm panel = new ElementListForm();
		panel.setEntities(service.findByFilter(new BaseFilter<Element, Integer>()));
		panel.setService(service);

		final DialogContainer dialog = new DialogContainer();
		dialog.getContentPane().removeAll();
		dialog.getContentPane().add(panel, BorderLayout.CENTER);
		dialog.pack();
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
}