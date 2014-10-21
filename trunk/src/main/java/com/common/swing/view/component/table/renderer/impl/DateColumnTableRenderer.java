package com.common.swing.view.component.table.renderer.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;

/**
 * La clase que implementa la interfaz que define un render para las columnas de fecha de una tabla.
 * 
 * @since 21/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class DateColumnTableRenderer extends BaseLabelColumnTableRenderer {
	private static final long serialVersionUID = 1L;

	/**
	 * El patrón de la fecha que vamos a usar.
	 */
	private String datePattern;

	/**
	 * El contructor del render que recibe el patrón de la fecha.
	 * 
	 * @param datePattern
	 *            El patrón de la fecha. No puede ser <code>null</code>.
	 */
	public DateColumnTableRenderer(String datePattern) {
		this(datePattern, null);
	}

	/**
	 * El contructor del render que recibe el patrón de la fecha y la alineación del campo.
	 * 
	 * @param datePattern
	 *            El patrón de la fecha.
	 * @param aligment
	 *            La alineación de la columna. Puede ser <code>null</code> y en ese caso, toma el valor de {@link SwingConstants.LEFT}.
	 */
	public DateColumnTableRenderer(String datePattern, Integer aligment) {
		super(aligment);
		this.datePattern = datePattern;
	}

	@Override
	protected String format(Object value) {
		Date fecha = (Date) value;
		return new SimpleDateFormat(this.datePattern).format(fecha);
	}
}