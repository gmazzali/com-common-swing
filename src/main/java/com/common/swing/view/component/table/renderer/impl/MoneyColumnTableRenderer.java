package com.common.swing.view.component.table.renderer.impl;

import java.text.DecimalFormat;

import javax.swing.SwingConstants;

/**
 * La clase que implementa la interfaz que define un render para las columnas de moneda de una tabla.
 * 
 * @since 21/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class MoneyColumnTableRenderer extends BaseLabelColumnTableRenderer {
	private static final long serialVersionUID = 1L;

	/**
	 * El patr�n de la moneda que vamos a usar.
	 */
	private String moneyPattern;

	/**
	 * El contructor por omisi�n del formateador de la moneda.
	 */
	public MoneyColumnTableRenderer() {
		this("###,##0.00");
	}

	/**
	 * El contructor del render que recibe el patr�n de la moneda.
	 * 
	 * @param moneyPattern
	 *            El patr�n de la moneda. No puede ser <code>null</code>.
	 */
	public MoneyColumnTableRenderer(String moneyPattern) {
		this(moneyPattern, SwingConstants.RIGHT, null);
	}

	/**
	 * El contructor del render que recibe el patr�n de la moneda y la alineaci�n del campo.
	 * 
	 * @param moneyPattern
	 *            El patr�n de la moneda.
	 * @param horizontalAlignment
	 *            La alineaci�n horizontal de la columna. Puede ser <code>null</code> y en ese caso, toma el valor de {@link SwingConstants.LEFT}.
	 * @param verticalAlignment
	 *            La alineaci�n vertical de la columna. Puede ser <code>null</code> y en ese caso, toma el valor de {@link SwingConstants.CENTER}.
	 */
	public MoneyColumnTableRenderer(String moneyPattern, Integer horizontalAlignment, Integer verticalAlignment) {
		super(horizontalAlignment, verticalAlignment);
		this.moneyPattern = moneyPattern;
	}

	@Override
	protected Object format(Object value) {
		return new DecimalFormat(this.moneyPattern).format(value);
	}
}