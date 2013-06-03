package com.commons.swing.abm.list;

import com.common.swing.abm.list.EntityListPanel;
import com.common.util.service.GenericService;
import com.commons.swing.abm.model.Element;

@SuppressWarnings("serial")
public class ElementListPanel extends EntityListPanel<Element, Integer> {

	/**
	 * @see com.common.swing.abm.list.EntityListPanel#getListTitle()
	 */
	@Override
	protected String getListTitle() {
		return "Listado de elementos";
	}

	/**
	 * @see com.common.swing.abm.list.EntityListPanel#isCloseBottonVisible()
	 */
	@Override
	protected Boolean isCloseBottonVisible() {
		return true;
	}

	/**
	 * @see com.common.swing.abm.list.EntityListPanel#getColumnsTitles()
	 */
	@Override
	protected String[] getColumnsTitles() {
		return new String[]
			{ "Nombre" };
	}

	/**
	 * @see com.common.swing.abm.list.EntityListPanel#getColumnsWidths()
	 */
	@Override
	protected Integer[] getColumnsWidths() {
		return new Integer[]
			{ 100 };
	}

	/**
	 * @see com.common.swing.abm.list.EntityListPanel#getColumnsFilter()
	 */
	@Override
	protected Integer[] getColumnsFilter() {
		return new Integer[]
			{ 0 };
	}

	/**
	 * @see com.common.swing.abm.list.EntityListPanel#loadListToTable()
	 */
	@Override
	protected void loadListToTable() {
		for (Element e : this.entityList) {
			String[] row = new String[1];

			row[0] = e.getName();

			this.tableModel.addRow(row);
		}
	}

	/**
	 * @see com.common.swing.abm.list.EntityListPanel#getNewEntity()
	 */
	@Override
	protected Element getNewEntity() {
		return new Element();
	}

	/**
	 * @see com.common.swing.abm.list.EntityListPanel#getCloneEntity(com.common.util.model.Persistence)
	 */
	@Override
	protected Element getCloneEntity(Element entity) {
		Element e = new Element();
		e.setId(entity.getId());
		e.setName(entity.getName());
		return e;
	}

	/**
	 * @see com.common.swing.abm.list.EntityListPanel#setEntityService(com.common.util.service.GenericService)
	 */
	@Override
	public void setEntityService(GenericService<Element, Integer> entityService) {
		this.entityService = entityService;
	}
}
