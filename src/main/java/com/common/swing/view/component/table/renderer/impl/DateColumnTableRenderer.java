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
	 * El patr�n de la fecha que vamos a usar.
	 */
	private String datePattern;

	/**
	 * El contructor del render que recibe el patr�n de la fecha.
	 * 
	 * @param datePattern
	 *            El patr�n de la fecha. No puede ser <code>null</code>.
	 */
	public DateColumnTableRenderer(String datePattern) {
		this(datePattern, null, null);
	}

	/**
	 * El contructor del render que recibe el patr�n de la fecha y la alineaci�n del campo.
	 * 
	 * @param datePattern
	 *            El patr�n de la fecha.
	 * @param horizontalAlignment
	 *            La alineaci�n horizontal de la columna. Puede ser <code>null</code> y en ese caso, toma el valor de {@link SwingConstants.LEFT}.
	 * @param verticalAlignment
	 *            La alineaci�n vertical de la columna. Puede ser <code>null</code> y en ese caso, toma el valor de {@link SwingConstants.CENTER}.
	 */
	public DateColumnTableRenderer(String datePattern, Integer horizontalAlignment, Integer verticalAlignment) {
		super(horizontalAlignment, verticalAlignment);
		this.datePattern = datePattern;
	}

	@Override
	protected Object format(Object value) {
		Date fecha = (Date) value;
		return fecha != null ? new SimpleDateFormat(this.datePattern).format(fecha) : "";
	}
}