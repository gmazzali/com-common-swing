package com.common.swing.view.component.table.formatter.impl;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;

public class BooleanComboCellFormatter extends BaseCellFormatter<JComboBox<String>> {
	private static final long serialVersionUID = 1L;

	/*
	 * El mapa de visualización de valores booleanos.
	 */
	private Map<String, Boolean> mappers;
	private Map<Boolean, String> captions;

	/**
	 * Constructor de un formateador de celdas de valores booleanos.
	 */
	public BooleanComboCellFormatter() {
		this("SI", "NO");
	}

	/**
	 * Constructor de un formateador de celdas de valores booleanos.
	 * 
	 * @param trueValue
	 *            El string para el valor {@link Boolean#TRUE}
	 * @param falseValue
	 *            El string para el valor {@link Boolean#FALSE}
	 */
	public BooleanComboCellFormatter(String trueValue, String falseValue) {
		this(trueValue, falseValue, null, null);
	}

	/**
	 * Constructor de un formateador de celdas de valores booleanos.
	 * 
	 * @param trueValue
	 *            El string para el valor {@link Boolean#TRUE}
	 * @param falseValue
	 *            El string para el valor {@link Boolean#FALSE}
	 * @param horizontalAlignment
	 *            La alineación horizontal del componente de visualización.
	 * @param verticalAlignment
	 *            La alineación vertical del componente de visualización.
	 */
	public BooleanComboCellFormatter(String trueValue, String falseValue, Integer horizontalAlignment, Integer verticalAlignment) {
		super(new JComboBox<String>(), horizontalAlignment, verticalAlignment);
		this.mappers = new HashMap<String, Boolean>();
		this.mappers.put(trueValue, Boolean.TRUE);
		this.mappers.put(falseValue, Boolean.FALSE);

		this.captions = new HashMap<Boolean, String>();
		this.captions.put(Boolean.TRUE, trueValue);
		this.captions.put(Boolean.FALSE, falseValue);

		this.componentEditor.addItem(trueValue);
		this.componentEditor.addItem(falseValue);
	}

	@Override
	public Object format(Object beanValue) {
		return this.captions.get(beanValue);
	}

	@Override
	public Object parse(JComboBox<String> editComponent) {
		return this.mappers.get(editComponent.getSelectedItem());
	}

	@Override
	public void setValueToEditComponent(Object beanValue, JComboBox<String> editComponent) {
		editComponent.setSelectedItem(beanValue);
	}
}