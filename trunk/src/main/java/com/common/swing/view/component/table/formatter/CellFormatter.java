package com.common.swing.view.component.table.formatter;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * La interfaz que define un formateador / parseador de datos dentro de una celda.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface CellFormatter extends TableCellEditor, TableCellRenderer {

	/**
	 * La cantidad de click que hacemos para editar la celda.
	 */
	public static final int CLICK_COUNT = 2;
}