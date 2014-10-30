package com.common.swing.view.component.table.formatter.impl;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

/**
 * La clase para los formateadores de las celdas de valores booleanos.
 * 
 * @see BaseCellFormatter
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class BooleanCellFormatter extends BaseCellFormatter<JCheckBox> {
	private static final long serialVersionUID = 1L;

	/*
	 * El mapa de visualización de valores booleanos.
	 */
	private Map<Boolean, String> captions;

	/**
	 * Constructor de un formateador de celdas de valores booleanos.
	 */
	public BooleanCellFormatter() {
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
	public BooleanCellFormatter(String trueValue, String falseValue) {
		this(trueValue, falseValue, SwingConstants.CENTER, null);
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
	public BooleanCellFormatter(String trueValue, String falseValue, Integer horizontalAlignment, Integer verticalAlignment) {
		super(new JCheckBox(), horizontalAlignment, verticalAlignment);
		this.captions = new HashMap<Boolean, String>();
		this.captions.put(Boolean.TRUE, trueValue);
		this.captions.put(Boolean.FALSE, falseValue);
	}

	@Override
	public Object format(Object beanValue) {
		return beanValue != null ? this.captions.get(beanValue) : null;
	}

	@Override
	public Object parse(JCheckBox editComponent) {
		return (Boolean) editComponent.isSelected();
	}

	@Override
	public void setValueToEditComponent(Object beanValue, JCheckBox editComponent) {
		editComponent.setHorizontalAlignment(SwingConstants.CENTER);
		Boolean bool = (Boolean) beanValue;
		if (bool != null) {
			editComponent.setSelected(bool);
		}
	}
}