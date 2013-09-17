package com.common.swing.crud.edit.impl;

import com.common.swing.crud.model.Element;

/**
 * La clase que extiende en contenedor del formulario de edición.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementEditFormContainerDialog extends EntityFormContainerDialog<Element, Integer> {

	public ElementEditFormContainerDialog() {
	}

	private static final long serialVersionUID = -1227018967633327331L;

	private ElementEditFormPanel panel;

	@Override
	public String getNewFormTitle() {
		return "Nuevo elemento";
	}

	@Override
	public String getEditFormTitle() {
		return "Editar elemento";
	}

	@Override
	public EntityFormPanel<Element, Integer> getEntityForm() {
		return this.panel;
	}

	public void setPanel(ElementEditFormPanel panel) {
		this.panel = panel;
		this.panel.setElementFormDialog(this);
	}
}
