package com.common.swing.view.component.table.model;

import java.util.Map;

import com.common.swing.view.bean.RowBean;

public class ReadOnlyBaseTableModel<B extends RowBean> extends BaseTableModel<B> {
	private static final long serialVersionUID = 1L;

	public ReadOnlyBaseTableModel(String[] visibleProperties, Map<String, String> visiblePropertiesName) {
		super(visibleProperties, visiblePropertiesName);
	}

	@Override
	protected String[] getEditableProperties() {
		return null;
	}
}