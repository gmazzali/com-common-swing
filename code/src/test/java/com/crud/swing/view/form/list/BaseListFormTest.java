package com.crud.swing.view.form.list;

import java.awt.BorderLayout;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.common.swing.domain.model.Element;
import com.common.swing.domain.model.ElementFilter;
import com.common.swing.domain.model.ElementServiceImpl;
import com.common.swing.view.Constants;
import com.common.swing.view.component.list.ElementListForm;
import com.common.util.domain.exception.UncheckedException;
import com.crud.swing.view.container.impl.DialogContainer;


/**
 * La clase de prueba de la ventana de listado de entidades.
 * 
 * @since 10/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseListFormTest {

	@InjectMocks
	private ElementListForm panel;

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * Pruebas sobre un listado sin acciones.
	 */
	@Test
	public void testListFormWithTableAction() {
		ElementServiceImpl service = new ElementServiceImpl() {
			private static final long serialVersionUID = 1L;

			@Override
			public void save(Element entity) throws UncheckedException {
				super.save(entity);

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
			}

			@Override
			public void delete(Element entity) throws UncheckedException {
				super.delete(entity);

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
		};
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

		this.panel.setEntities(service.findByFilter(new ElementFilter()));
		this.panel.setService(service);

		final DialogContainer dialog = new DialogContainer();
		dialog.getContentPane().removeAll();
		dialog.getContentPane().add(this.panel, BorderLayout.CENTER);
		dialog.pack();
		dialog.setModal(true);
		dialog.setAlwaysOnTop(Constants.alwaysTop);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(Constants.visible);
	}
}