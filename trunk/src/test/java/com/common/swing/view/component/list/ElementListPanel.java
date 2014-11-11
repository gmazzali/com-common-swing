package com.common.swing.view.component.list;

import com.common.swing.domain.model.Element;
import com.common.swing.view.component.list.formatter.PropertyCellFormatter;
import com.common.swing.view.component.panel.BaseListPanel;

/**
 * El panel de la lista de elementos.
 * 
 * @since 10/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementListPanel extends BaseListPanel<Element> {
	private static final long serialVersionUID = 1L;

	public ElementListPanel() {
		this.init();
	}

	@Override
	public Integer getHeightSize() {
		return 300;
	}

	@Override
	public Integer getWidthSize() {
		return 200;
	}

	@Override
	protected void afterInit() {
	}

	@Override
	protected BaseList<Element> createList() {
		return new ElementList();
	}

	@Override
	protected PropertyCellFormatter<Element> getFormatterProperty() {
		return new PropertyCellFormatter<Element>(Element.Attribute.NAME) {
			private static final long serialVersionUID = 1L;
		};
	}
}