package com.common.swing.domain.model.crud.edit.impl;

import com.common.swing.domain.model.crud.model.Element;

/**
 * La clase que extiende en contenedor del formulario de edición.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementEditFormDialog extends EditFormDialog<Element, Integer> {
	private static final long serialVersionUID = 1L;

	private ElementEditFormPanel panel;

	@Override
	public String getNewTitle() {
		return "Nuevo elemento";
	}

	@Override
	public String getEditTitle() {
		return "Editar elemento";
	}

	@Override
	public EditFormPanel<Element, Integer> getEditForm() {
		return this.panel;
	}

	public void setPanel(ElementEditFormPanel panel) {
		this.panel = panel;
		this.panel.setContainer(this);
	}
}