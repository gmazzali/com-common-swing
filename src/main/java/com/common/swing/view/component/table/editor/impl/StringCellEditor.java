package com.common.swing.view.component.table.editor.impl;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.common.swing.view.component.table.editor.BaseTableCellEditor;

/**
 * La clase que permite definir el editor de las cadenas de textos en las celdas de una tabla.
 * 
 * @since 29/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class StringCellEditor extends AbstractCellEditor implements BaseTableCellEditor {
	private static final long serialVersionUID = 1L;

	/**
	 * El campo de edición.
	 */
	private JTextField field;

	/**
	 * El constructor de un editor de cadenas de textos en las celdas.
	 */
	public StringCellEditor() {
		this.field = new JTextField();
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		if (anEvent instanceof MouseEvent) {
			return ((MouseEvent) anEvent).getClickCount() >= CLICK_COUNT;
		}
		return false;
	}

	@Override
	public Object getCellEditorValue() {
		return this.field.getText();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.field.setText(value.toString());
		return this.field;
	}
}