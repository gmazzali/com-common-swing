package com.common.swing.domain.model.crud.container.impl;

import javax.swing.JDialog;

import com.common.swing.domain.model.crud.BaseForm;
import com.common.swing.domain.model.crud.container.BaseContainer;

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