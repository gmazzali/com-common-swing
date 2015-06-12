package com.common.swing.view.component.table.formatter.impl;

import java.math.BigDecimal;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.common.util.business.tool.NumberUtil;
import com.common.util.business.tool.StringUtil;

/**
 * La clase para los formateadores de las celdas de números flotantes.
 * 
 * @see BaseCellFormatter
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class BigDecimalCellFormatter extends BaseCellFormatter<JTextField> {
	private static final long serialVersionUID = 1L;

	/**
	 * La cantidad de dígitos decimales.
	 */
	private Integer decimalDigit;
	/**
	 * El separador decimal.
	 */
	private Character decimalSeparator;
	/**
	 * La cantidad de dígitos de los grupos.
	 */
	private Integer groupSize;
	/**
	 * El separador de los grupos.
	 */
	private Character groupSeparator;

	/**
	 * Constructor de un formateador de celdas de numeros flotantes.
	 */
	public BigDecimalCellFormatter() {
		this(2, ',', 3, '.');
	}

	/**
	 * Constructor de un formateador de celdas de numeros flotantes.
	 * 
	 * @param decimalDigit
	 *            La cantidad de digitos decimales.
	 * @param decimalSeparator
	 *            El caracter de separación de decimales.
	 * @param groupSize
	 *            El tamaño de los grupos de numeros enteros.
	 * @param groupSeparator
	 *            El separador de los grupos.
	 */
	public BigDecimalCellFormatter(Integer decimalDigit, Character decimalSeparator, Integer groupSize, Character groupSeparator) {
		this(decimalDigit, decimalSeparator, groupSize, groupSeparator, SwingConstants.RIGHT, null);
	}

	/**
	 * Constructor de un formateador de celdas de numeros flotantes.
	 * 
	 * @param decimalDigit
	 *            La cantidad de digitos decimales.
	 * @param decimalSeparator
	 *            El caracter de separación de decimales.
	 * @param groupSize
	 *            El tamaño de los grupos de numeros enteros.
	 * @param groupSeparator
	 *            El separador de los grupos.
	 * @param horizontalAlignment
	 *            La alineación horizontal del componente de visualización.
	 * @param verticalAlignment
	 *            La alineación vertical del componente de visualización.
	 */
	public BigDecimalCellFormatter(Integer decimalDigit, Character decimalSeparator, Integer groupSize, Character groupSeparator,
			Integer horizontalAlignment, Integer verticalAlignment) {
		super(new JTextField(), horizontalAlignment, verticalAlignment);
		this.decimalDigit = decimalDigit;
		this.decimalSeparator = decimalSeparator;
		this.groupSize = groupSize;
		this.groupSeparator = groupSeparator;
	}

	@Override
	public Object format(Object beanValue) {
		BigDecimal bigDecimal = (BigDecimal) beanValue;
		return bigDecimal != null ? NumberUtil.formatNumber(bigDecimal, null, null, this.decimalDigit, this.decimalDigit, this.decimalSeparator,
				this.groupSize, this.groupSeparator) : null;
	}

	@Override
	public void setValueToEditComponent(Object beanValue, JTextField editComponent) {
		BigDecimal bigDecimal = (BigDecimal) beanValue;
		editComponent.setText(bigDecimal != null ? NumberUtil.formatNumber(bigDecimal, null, null, this.decimalDigit, this.decimalDigit,
				this.decimalSeparator, null, null) : "");
	}

	@Override
	public Object parse(JTextField editComponent) {
		String stringNumber = editComponent.getText();
		BigDecimal bigDecimal = null;
		if (StringUtil.isNotEmpty(stringNumber)) {
			stringNumber = stringNumber.replace(this.decimalSeparator, '.');
			if (NumberUtil.isDecimalNumber(stringNumber)) {
				bigDecimal = new BigDecimal(stringNumber);
			}
		}
		return bigDecimal;
	}
}