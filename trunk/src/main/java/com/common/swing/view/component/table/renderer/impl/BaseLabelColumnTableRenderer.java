package com.common.swing.view.component.table.renderer.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.common.swing.view.component.table.renderer.ColumnTableRenderer;

/**
 * La clase que implementa la interfaz que define un render para las columnas de solo lecturas de una tabla.
 * 
 * @since 21/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class BaseLabelColumnTableRenderer extends JLabel implements ColumnTableRenderer {
	private static final long serialVersionUID = 1L;

	/**
	 * La alineación del campo.
	 */
	private Integer aligment;

	/**
	 * El contructor del render que recibe la alineación del campo.
	 * 
	 * @param aligment
	 *            La alineación de la columna. Puede ser <code>null</code> y en ese caso, toma el valor de {@link SwingConstants.LEFT}.
	 */
	public BaseLabelColumnTableRenderer(Integer aligment) {
		if (aligment != null) {
			this.aligment = aligment;
		} else {
			this.aligment = SwingConstants.LEFT;
		}
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel label = new JLabel();
		label.setFont((Font) UIManager.get("Table.font"));
		label.setOpaque(true);
		label.setHorizontalAlignment(this.aligment);

		if (value != null) {
			label.setText(this.format(value));
		} else {
			label.setText(null);
		}

		if (isSelected) {
			label.setBackground((Color) UIManager.get("Table.selectionBackground"));
			label.setForeground((Color) UIManager.get("Table.selectionForeground"));

			if (hasFocus) {
				label.setBorder((Border) UIManager.get("Table.focusCellHighlightBorder"));
			} else {
				label.setBorder(BorderFactory.createEmptyBorder());
			}
		} else {
			label.setForeground((Color) UIManager.get("Table.foreground"));
			label.setBackground((Color) UIManager.get("Table.background"));
			label.setBorder(BorderFactory.createEmptyBorder());
		}

		return label;
	}

	/**
	 * Permite formatear el objeto que corresponde a la celda. Previamente ya se valida que el mismo no sea <code>null</code>.
	 * 
	 * @param value
	 *            El valor que vamos a formatear. No es <code>null</code>.
	 * @return La cadena que corresponde a lo que vamos a desplegar en la celda.
	 */
	protected abstract String format(Object value);
}