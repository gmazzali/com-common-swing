package com.common.swing.view.component.table.formatter.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;

/**
 * La clase para los formateadores de las celdas de fecha dentro de un campo de texto.
 * 
 * @see BaseCellFormatter
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class DateCellFormatter extends BaseCellFormatter<JTextField> {
	private static final long serialVersionUID = 1L;

	/**
	 * El patrón de la fecha que vamos a usar.
	 */
	private String datePattern;

	/**
	 * Constructor de un formateador de celdas de fechas.
	 * 
	 * @param datePattern
	 *            El patrón de la fecha. No puede ser <code>null</code>.
	 */
	public DateCellFormatter(String datePattern) {
		this(datePattern, null, null);
	}

	/**
	 * Constructor de un formateador de celdas de fechas.
	 * 
	 * @param datePattern
	 *            El patrón de la fecha. No puede ser <code>null</code>.
	 * @param horizontalAlignment
	 *            La alineación horizontal del componente de visualización.
	 * @param verticalAlignment
	 *            La alineación vertical del componente de visualización.
	 */
	public DateCellFormatter(String datePattern, Integer horizontalAlignment, Integer verticalAlignment) {
		super(new JTextField(), horizontalAlignment, verticalAlignment);
		this.datePattern = datePattern;
	}

	@Override
	public Object format(Object beanValue) {
		return beanValue != null ? new SimpleDateFormat(this.datePattern).format(beanValue) : null;
	}

	@Override
	public Object parse(JTextField editComponent) {
		Date date = null;
		try {
			date = new SimpleDateFormat(this.datePattern).parse(editComponent.getText());
		} catch (Exception e) {
		}
		return date;
	}

	@Override
	public void setValueToEditComponent(Object beanValue, JTextField editComponent) {
		editComponent.setText(beanValue != null ? new SimpleDateFormat(this.datePattern).format(beanValue) : null);
	}
}