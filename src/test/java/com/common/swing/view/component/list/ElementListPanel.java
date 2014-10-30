package com.common.swing.view.component.list;

import com.common.swing.domain.model.Element;
import com.common.swing.view.component.panel.BaseListPanel;
import com.common.swing.view.component.table.BaseTable;
import com.common.swing.view.component.table.formatter.impl.BigDecimalCellFormatter;
import com.common.swing.view.component.table.formatter.impl.BooleanCellFormatter;
import com.common.swing.view.component.table.formatter.impl.DateCellFormatter;
import com.common.swing.view.component.table.formatter.impl.LongCellFormatter;
import com.common.swing.view.component.table.formatter.impl.StringCellFormatter;

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
	protected void loadHeaderRenderers(BaseTable<Element> table) {
	}

	@Override
	protected void loadCellFormatter(BaseTable<Element> table) {
		table.addCellFormatter(Element.Attribute.CODE, new LongCellFormatter());
		table.addCellFormatter(Element.Attribute.NAME, new StringCellFormatter());
		table.addCellFormatter(Element.Attribute.FECHA, new DateCellFormatter("dd/MM/yyyy"));
		table.addCellFormatter(Element.Attribute.SALARIO, new BigDecimalCellFormatter());
		table.addCellFormatter(Element.Attribute.ACTIVO, new BooleanCellFormatter());
	}
}