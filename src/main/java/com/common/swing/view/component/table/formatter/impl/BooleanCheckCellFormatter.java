package com.common.swing.view.component.table.formatter.impl;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 * La clase para los formateadores de las celdas de valores booleanos mediante un checkbox.
 * 
 * @see BaseCellFormatter
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class BooleanCheckCellFormatter extends BaseCellFormatter<JCheckBox> {
	private static final long serialVersionUID = 1L;

	/**
	 * El checkbox de visualización.
	 */
	private JCheckBox viewCheckBox;

	/**
	 * Constructor de un formateador de celdas de valores booleanos.
	 */
	public BooleanCheckCellFormatter() {
		this(null, null);
	}

	/**
	 * Constructor de un formateador de celdas de valores booleanos.
	 * 
	 * @param horizontalAlignment
	 *            La alineación horizontal del componente de visualización.
	 * @param verticalAlignment
	 *            La alineación vertical del componente de visualización.
	 */
	public BooleanCheckCellFormatter(Integer horizontalAlignment, Integer verticalAlignment) {
		super(new JCheckBox(), horizontalAlignment, verticalAlignment);
		this.viewCheckBox = new JCheckBox();
		this.viewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		this.viewCheckBox.setOpaque(true);
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		return true;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (value != null) {
			this.viewCheckBox.setSelected((boolean) this.format(value));
		}
		this.viewCheckBox.setEnabled(table.isCellEditable(row, column));

		if (isSelected) {
			this.viewCheckBox.setBackground((Color) UIManager.get("Table.selectionBackground"));
			this.viewCheckBox.setForeground((Color) UIManager.get("Table.selectionForeground"));
			this.viewCheckBox.setBorder(BorderFactory.createEmptyBorder());
		} else {
			this.viewCheckBox.setForeground((Color) UIManager.get("Table.foreground"));
			this.viewCheckBox.setBackground((Color) UIManager.get("Table.background"));
			this.viewCheckBox.setBorder(BorderFactory.createEmptyBorder());
		}

		if (hasFocus) {
			this.viewCheckBox.setForeground((Color) UIManager.get("Table.focusCellForeground"));
			this.viewCheckBox.setBackground((Color) UIManager.get("Table.focusCellBackground"));
			this.viewCheckBox.setBorder((Border) UIManager.get("Table.focusCellHighlightBorder"));
		}
		return this.viewCheckBox;
	}

	@Override
	public Object format(Object beanValue) {
		return beanValue;
	}

	@Override
	public Object parse(JCheckBox editComponent) {
		return (Boolean) editComponent.isSelected();
	}

	@Override
	public void setValueToEditComponent(Object beanValue, JCheckBox editComponent) {
		editComponent.setHorizontalAlignment(SwingConstants.CENTER);
		Boolean bool = (Boolean) beanValue;
		if (bool != null) {
			editComponent.setSelected(bool);
		}
	}
}