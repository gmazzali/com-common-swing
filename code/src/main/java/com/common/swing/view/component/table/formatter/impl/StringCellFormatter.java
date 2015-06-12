package com.common.swing.view.component.table.formatter.impl;

import javax.swing.JTextField;

/**
 * La clase para los formateadores de las celdas de strings.
 * 
 * @see BaseCellFormatter
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class StringCellFormatter extends BaseCellFormatter<JTextField> {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de un formateador de celdas de strings.
	 */
	public StringCellFormatter() {
		this(null, null);
	}

	/**
	 * Constructor de un formateador de celdas de strings.
	 * 
	 * @param horizontalAlignment
	 *            La alineación horizontal del componente de visualización.
	 * @param verticalAlignment
	 *            La alineación vertical del componente de visualización.
	 */
	public StringCellFormatter(Integer horizontalAlignment, Integer verticalAlignment) {
		super(new JTextField(), horizontalAlignment, verticalAlignment);
	}

	@Override
	public Object format(Object value) {
		return value != null ? value.toString() : null;
	}

	@Override
	public Object parse(JTextField editComponent) {
		return editComponent.getText();
	}

	@Override
	public void setValueToEditComponent(Object value, JTextField editComponent) {
		editComponent.setText(value != null ? value.toString() : "");
	}
}