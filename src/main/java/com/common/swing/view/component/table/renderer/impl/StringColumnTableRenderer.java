package com.common.swing.view.component.table.renderer.impl;

import javax.swing.SwingConstants;

/**
 * La clase que implementa la interfaz que define un render para las columnas de string de una tabla.
 * 
 * @since 29/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class StringColumnTableRenderer extends BaseColumnTableRenderer {
	private static final long serialVersionUID = 1L;

	/**
	 * El contructor del render que recibe la alineación del campo.
	 * 
	 * @param horizontalAlignment
	 *            La alineación horizontal de la columna. Puede ser <code>null</code> y en ese caso, toma el valor de {@link SwingConstants.LEFT}.
	 * @param verticalAlignment
	 *            La alineación vertical de la columna. Puede ser <code>null</code> y en ese caso, toma el valor de {@link SwingConstants.CENTER}.
	 */
	public StringColumnTableRenderer(Integer horizontalAlignment, Integer verticalAlignment) {
		super(horizontalAlignment, verticalAlignment);
	}

	@Override
	protected Object format(Object value) {
		return value != null ? value.toString() : "";
	}
}