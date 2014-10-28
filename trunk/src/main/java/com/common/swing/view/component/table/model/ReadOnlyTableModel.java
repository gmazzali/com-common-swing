package com.common.swing.view.component.table.model;
import javax.swing.table.DefaultTableModel;

/**
 * El modelo de la tabla que solo permite visualizar los atributos.
 * 
 * @since 23/04/2014
 * @author Guillermo Mazzali.
 * @version 1.0
 */
public class ReadOnlyTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}	
}