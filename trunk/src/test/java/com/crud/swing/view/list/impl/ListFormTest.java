package com.crud.swing.view.list.impl;

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.JButton;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.common.swing.view.action.TableAction;
import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.TableEvent;
import com.common.swing.view.listener.TableListener;
import com.common.util.business.tool.collection.CollectionUtil;
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
	@SuppressWarnings("unchecked")
	public void testListFormWithTableAction() {
		final ElementServiceImpl service = new ElementServiceImpl();
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
		
		final ElementListForm panel = new ElementListForm() {
			private static final long serialVersionUID = 1L;

			@Override
			protected Collection<TableAction<Element>> getTableActions() {
				TableListener<Element> tableListener1 = new TableListener<Element>() {

					@Override
					public void fireEvent(final TableEvent<Element> tableEvent) {
						new Thread() {
							public void run() {
								setEnabled(false);
								service.save(new Element("ELEMENTO " + (int) (Math.random() * 1000)));
								setEntities(service.findByFilter(new BaseFilter<Element, Integer>()));
								setEnabled(true);
							};
						}.start();
					};
				};

				ButtonDecorator buttonDecorator1 = new ButtonDecorator() {

					@Override
					public void decorateButton(JButton button) {
						button.setText("AGREGAR");
					}
				};
				TableListener<Element> tableListener2 = new TableListener<Element>() {

					@Override
					public void fireEvent(final TableEvent<Element> tableEvent) {
						new Thread() {
							public void run() {
								setEnabled(false);
								for (Element element : tableEvent.getEntities()) {
									service.delete(element);
								}
								setEntities(service.findByFilter(new BaseFilter<Element, Integer>()));
								setEnabled(true);
							};
						}.start();
					}
				};
				ButtonDecorator buttonDecorator2 = new ButtonDecorator() {

					@Override
					public void decorateButton(JButton button) {
						button.setText("BORRAR");
					}
				};
				TableListener<Element> tableListener3 = new TableListener<Element>() {

					@Override
					public void fireEvent(final TableEvent<Element> tableEvent) {
						emptyFields();
					}
				};
				ButtonDecorator buttonDecorator3 = new ButtonDecorator() {

					@Override
					public void decorateButton(JButton button) {
						button.setText("LIMPIAR");
					}
				};
				TableListener<Element> tableListener4 = new TableListener<Element>() {

					@Override
					public void fireEvent(final TableEvent<Element> tableEvent) {
						setEntities(service.findByFilter(new BaseFilter<Element, Integer>()));
					}
				};
				ButtonDecorator buttonDecorator4 = new ButtonDecorator() {

					@Override
					public void decorateButton(JButton button) {
						button.setText("CARGAR");
					}
				};

				return CollectionUtil.newArrayList(new TableAction<Element>(table, tableListener1, buttonDecorator1), new TableAction<Element>(table,
						tableListener2, buttonDecorator2), new TableAction<Element>(table, tableListener3, buttonDecorator3),
						new TableAction<Element>(table, tableListener4, buttonDecorator4));
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
}