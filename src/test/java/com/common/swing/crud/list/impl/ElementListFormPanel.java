package com.common.swing.crud.list.impl;

import com.common.swing.crud.edit.EntityEditFormContainer;
import com.common.swing.crud.edit.impl.ElementEditFormContainerDialog;
import com.common.swing.crud.model.Element;
import com.common.swing.crud.model.ElementServiceImpl;
import com.common.swing.domain.model.crud.FormContainer;
import com.common.util.service.GenericService;

/**
 * La clase que representa el formulario del listado de los elementos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementListFormPanel extends EntityListFormPanel<Element, Integer> {

	private static final long serialVersionUID = -6514055708578488963L;

	private ElementServiceImpl service;

	private ElementEditFormContainerDialog elementEditFormContainerDialog;

	private ElementListFormContainerDialog elementListFormContainerDialog;

	public void setEntityEditFormContainer(ElementEditFormContainerDialog elementEditFormContainerDialog) {
		this.elementEditFormContainerDialog = elementEditFormContainerDialog;
	}

	public void setFormContainer(ElementListFormContainerDialog elementListFormContainerDialog) {
		this.elementListFormContainerDialog = elementListFormContainerDialog;
	}

	public void setElementService(ElementServiceImpl service) {
		this.service = service;
	}

	@Override
	public EntityEditContainer<Element, Integer> getEntityEditFormContainer() {
		return this.elementEditFormContainerDialog;
	}

	@Override
	public GenericService<Element, Integer> getEntityService() {
		return this.service;
	}

	@Override
	public FormContainer getFormContainer() {
		return this.elementListFormContainerDialog;
	}

	@Override
	public String[] getColumnsTitles() {
		return new String[]
			{ "Nombre" };
	}

	@Override
	public Integer[] getColumnsWidths() {
		return new Integer[]
			{ 1 };
	}

	@Override
	public String[] converterEntityToRow(Element entity) {
		String[] row = new String[1];

		row[0] = entity.getName();

		return row;
	}

	@Override
	public Element getNewEntity() {
		return new Element();
	}

	@Override
	public Element getCloneEntity(Element entity) {
		try {
			return (Element) entity.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public String getFormTitle() {
		return "Listado de elementos";
	}

	@Override
	public String getNewButtonCaption() {
		return "Nuevo elemento";
	}

	@Override
	public String getModifyButtonCaption() {
		return "Modificar elemento";
	}

	@Override
	public String getDeleteButtonCaption() {
		return "Borrar elemento";
	}

	@Override
	public String getCloseButtonCaption() {
		return "Cerrar";
	}

	@Override
	public void emptyFields() {
	}

	@Override
	public Integer getHeightSize() {
		return null;
	}

	@Override
	public Integer getWidthSize() {
		return null;
	}

	@Override
	public Boolean isContainerCloseable() {
		return true;
	}
}