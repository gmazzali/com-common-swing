package com.common.swing.view.component.table.renderer.impl;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

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
	 * El patrón de la moneda que vamos a usar.
	 */
	private String moneyPattern;

	/**
	 * El contructor por omisión del formateador de la moneda.
	 */
	public MoneyColumnTableRenderer() {
		this("###,##0.00");
	}

	/**
	 * El contructor del render que recibe el patrón de la moneda.
	 * 
	 * @param moneyPattern
	 *            El patrón de la moneda. No puede ser <code>null</code>.
	 */
	public MoneyColumnTableRenderer(String moneyPattern) {
		this(moneyPattern, SwingConstants.RIGHT);
	}

	/**
	 * El contructor del render que recibe el patrón de la moneda y la alineación del campo.
	 * 
	 * @param moneyPattern
	 *            El patrón de la moneda.
	 * @param aligment
	 *            La alineación de la columna. Puede ser <code>null</code> y en ese caso, toma el valor de {@link SwingConstants.RIGHT}.
	 */
	public MoneyColumnTableRenderer(String moneyPattern, Integer aligment) {
		super(aligment);
		this.moneyPattern = moneyPattern;
	}

	@Override
	protected String format(Object value) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		return new DecimalFormat(this.moneyPattern, symbols).format(value);
	}
}