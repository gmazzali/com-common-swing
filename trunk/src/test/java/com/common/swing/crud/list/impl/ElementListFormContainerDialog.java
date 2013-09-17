package com.common.swing.crud.list.impl;

import com.common.swing.crud.list.EntityListForm;
import com.common.swing.crud.model.Element;
import com.common.swing.crud.model.ElementServiceImpl;
import com.common.util.exception.CheckedException;

/**
 * La clase contenedora que va a contener el formulario del listado de los elementos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementListFormContainerDialog extends EntityListFormContainerDialog<Element, Integer> {

	private static final long serialVersionUID = -4106117796386508091L;

	private ElementServiceImpl elementService;

	private ElementListFormPanel elementListFormPanel;

	public void setElementService(ElementServiceImpl elementService) {
		this.elementService = elementService;
	}

	@Override
	public String getListContainerTitle() {
		return "Listado de elementos";
	}

	@Override
	public void updateEntityListForm() {
		new Thread() {
			@Override
			public void run() {
				try {
					ElementListFormContainerDialog.this.setEnabled(false);
					ElementListFormContainerDialog.this.getEntityListForm().updateEntityList(
							ElementListFormContainerDialog.this.elementService.findAll());
					ElementListFormContainerDialog.this.setEnabled(true);
				} catch (CheckedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public EntityListForm<Element, Integer> getEntityListForm() {
		return this.elementListFormPanel;
	}

	public void setElementListFormPanel(ElementListFormPanel elementListFormPanel) {
		this.elementListFormPanel = elementListFormPanel;
	}
}