package com.crud.swing.view.container.impl;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDialog;

import com.common.swing.view.component.panel.BasePanel;
import com.crud.swing.view.container.BaseContainer;
import com.crud.swing.view.form.BaseForm;

/**
 * La clase que extiende de {@link JDialog} para poder contener un formulario {@link BaseForm}.
 * 
 * @see JDialog
 * @see BasePanel
 * @see FrameContainer
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class DialogContainer extends JDialog implements BaseContainer {
	private static final long serialVersionUID = 1L;

	/**
	 * Permite cargar dentro del contenedor el cuerpo del mismo.
	 * 
	 * @param component
	 *            El panel que contiene el cuerpo de la ventana.
	 */
	public void setBody(Component component) {
		this.getContentPane().removeAll();
		this.getContentPane().add(component, BorderLayout.CENTER);
		this.pack();
	}
}