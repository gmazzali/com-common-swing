package com.common.swing.view.component.table.formatter.impl;

import javax.swing.JTextField;

import com.common.util.business.tool.NumberUtil;

/**
 * La clase para los formateadores de las celdas de n�meros enteros.
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
	 * Constructor de un formateador de celdas de n�meros enteros.
	 */
	public LongCellFormatter() {
		this(null, null);
	}

	/**
	 * Constructor de un formateador de celdas de n�meros enteros.
	 * 
	 * @param horizontalAlignment
	 *            La alineaci�n horizontal del componente de visualizaci�n.
	 * @param verticalAlignment
	 *            La alineaci�n vertical del componente de visualizaci�n.
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