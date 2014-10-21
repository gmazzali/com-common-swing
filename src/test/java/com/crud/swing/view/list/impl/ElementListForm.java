package com.crud.swing.view.list.impl;

import javax.swing.ListSelectionModel;

import com.common.swing.view.component.table.BaseTable;
import com.common.util.business.service.BaseService;
import com.crud.swing.model.Element;
import com.crud.swing.model.ElementServiceImpl;
import com.crud.swing.model.ElementTable;

/**
 * La clase que extiende el formulario de listado de entidades.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class ElementListForm extends ListForm<Element, Integer> {
	private static final long serialVersionUID = 1L;

	private ElementServiceImpl service;

	@Override
	public Integer getHeightSize() {
		return 400;
	}

	@Override
	public Integer getWidthSize() {
		return 400;
	}

	@Override
	protected BaseTable<Element> createTable() {
		return new ElementTable();
	}

	@Override
	public void enabled() {
		this.table.setEnabled(true);
	}

	@Override
	public void disabled() {
		this.table.setEnabled(false);
	}

	@Override
	protected BaseService<Element, Integer> getService() {
		return service;
	}

	public void setService(ElementServiceImpl service) {
		this.service = service;
	}

	@Override
	protected void addColumnGenerators(BaseTable<Element> table) {
	}

	@Override
	protected void afterInit() {
		this.table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}
}