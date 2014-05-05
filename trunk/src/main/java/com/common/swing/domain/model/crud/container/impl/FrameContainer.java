package com.common.swing.domain.model.crud.container.impl;

import javax.swing.JFrame;

import com.common.swing.domain.model.crud.BaseForm;
import com.common.swing.domain.model.crud.container.BaseContainer;

/**
 * La clase que extiende de {@link JFrame} para poder contener un formulario {@link BaseForm}.
 * 
 * @see JFrame
 * @see BaseForm
 * @see DialogContainer
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class FrameContainer extends JFrame implements BaseContainer {
	private static final long serialVersionUID = 1L;
}