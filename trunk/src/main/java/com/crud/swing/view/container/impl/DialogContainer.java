package com.crud.swing.view.container.impl;

import javax.swing.JDialog;

import com.crud.swing.view.container.BaseContainer;
import com.crud.swing.view.form.BaseForm;

/**
 * La clase que extiende de {@link JDialog} para poder contener un formulario {@link BaseForm}.
 * 
 * @see JDialog
 * @see BaseForm
 * @see FrameContainer
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class DialogContainer extends JDialog implements BaseContainer {
	private static final long serialVersionUID = 1L;
}