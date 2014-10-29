package com.common.swing.view.component.table.renderer.impl;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.common.swing.view.component.table.renderer.ColumnTableRenderer;

/**
 * La clase que implementa la interfaz que define un render para las columnas de solo lecturas de una tabla.
 * 
 * @since 21/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class BaseColumnTableRenderer extends DefaultTableCellRenderer implements ColumnTableRenderer {
	private static final long serialVersionUID = 1L;

	/**
	 * Permite crear un render por default.
	 */
	public BaseColumnTableRenderer() {
		this(null, null);
	}

	/**
	 * El contructor del render que recibe la alineación del campo.
	 * 
	 * @param horizontalAlignment
	 *            La alineación horizontal de la columna. Puede ser <code>null</code> y en ese caso, toma el valor de {@link SwingConstants.LEFT}.
	 * @param verticalAlignment
	 *            La alineación vertical de la columna. Puede ser <code>null</code> y en ese caso, toma el valor de {@link SwingConstants.CENTER}.
	 */
	public BaseColumnTableRenderer(Integer horizontalAlignment, Integer verticalAlignment) {
		if (horizontalAlignment == null) {
			horizontalAlignment = SwingConstants.LEFT;
		}
		if (verticalAlignment == null) {
			verticalAlignment = SwingConstants.CENTER;
		}
		this.setHorizontalAlignment(horizontalAlignment);
		this.setVerticalAlignment(verticalAlignment);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return super.getTableCellRendererComponent(table, this.format(value), isSelected, hasFocus, row, column);
	}

	/**
	 * Permite formatear el objeto que corresponde a la celda. Previamente ya se valida que el mismo no sea <code>null</code>.
	 * 
	 * @param value
	 *            El valor que vamos a formatear. No es <code>null</code>.
	 * @return El objeto que corresponde a lo que vamos a desplegar en la celda.
	 */
	protected abstract Object format(Object value);
}