package com.common.swing.view.component.table.formatter.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

/**
 * La clase para los formateadores de las celdas de fecha dentro de un campo de selección de fechas.
 * 
 * @see BaseCellFormatter
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class DateChooserCellFormatter extends BaseCellFormatter<JDateChooser> {
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
	public DateChooserCellFormatter(String datePattern) {
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
	public DateChooserCellFormatter(String datePattern, Integer horizontalAlignment, Integer verticalAlignment) {
		super(new JDateChooser(), horizontalAlignment, verticalAlignment);
		this.datePattern = datePattern;
	}

	@Override
	public Object format(Object beanValue) {
		return beanValue != null ? new SimpleDateFormat(this.datePattern).format(beanValue) : null;
	}

	@Override
	public Object parse(JDateChooser editComponent) {
		return editComponent.getDate();
	}

	@Override
	public void setValueToEditComponent(Object beanValue, JDateChooser editComponent) {
		Date date = null;
		if (beanValue != null) {
			try {
				date = (Date) beanValue;
			} catch (Exception e) {
			}
		}
		editComponent.setDate(date);
	}
}