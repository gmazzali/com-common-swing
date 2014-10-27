package com.common.swing.view.component.list;

import com.common.swing.domain.model.Element;
import com.common.swing.view.component.panel.BaseListPanel;
import com.common.swing.view.component.table.BaseTable;
import com.common.swing.view.component.table.renderer.impl.DateColumnTableRenderer;
import com.common.swing.view.component.table.renderer.impl.MoneyColumnTableRenderer;

/**
 * El panel de la tabla de elementos.
 * 
 * @since 27/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementListPanel extends BaseListPanel<Element> {
	private static final long serialVersionUID = 1L;

	public ElementListPanel() {
		this.init();
	}

	@Override
	protected void afterInit() {
	}

	@Override
	public Integer getHeightSize() {
		return 200;
	}

	@Override
	public Integer getWidthSize() {
		return 500;
	}

	@Override
	protected BaseTable<Element> createTable() {
		return new ElementTable();
	}

	@Override
	protected void loadColumnsRenderer(BaseTable<Element> table) {
		table.addColumnRenderer(Element.Attribute.FECHA, new DateColumnTableRenderer("dd/MM/yyyy"));
		table.addColumnRenderer(Element.Attribute.SALARIO, new MoneyColumnTableRenderer());
	}

	@Override
	protected void loadHeadersRenderer(BaseTable<Element> table) {
	}
}