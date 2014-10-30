package com.common.swing.view.component.table.formatter.impl;

import javax.swing.JTextField;

import com.common.util.business.tool.NumberUtil;

/**
 * La clase para los formateadores de las celdas de números enteros.
 * 
 * @see BaseCellFormatter
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class LongCellFormatter extends BaseCellFormatter<JTextField> {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de un formateador de celdas de números enteros.
	 */
	public LongCellFormatter() {
		this(null, null);
	}

	/**
	 * Constructor de un formateador de celdas de números enteros.
	 * 
	 * @param horizontalAlignment
	 *            La alineación horizontal del componente de visualización.
	 * @param verticalAlignment
	 *            La alineación vertical del componente de visualización.
	 */
	public LongCellFormatter(Integer horizontalAlignment, Integer verticalAlignment) {
		super(new JTextField(), horizontalAlignment, verticalAlignment);
	}

	@Override
	public Object format(Object beanValue) {
		return beanValue != null ? beanValue.toString() : null;
	}

	@Override
	public Object parse(JTextField editComponent) {
		String number = editComponent.getText();
		if (NumberUtil.isLongNumber(number)) {
			return new Long(number);
		}
		return null;
	}

	@Override
	public void setValueToEditComponent(Object beanValue, JTextField editComponent) {
		editComponent.setText(beanValue != null ? beanValue.toString() : "");
	}
}