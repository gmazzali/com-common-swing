package com.common.swing.view.component.table.editor;

import java.io.Serializable;

import javax.swing.table.TableCellEditor;

/**
 * La interfaz que define el editor base de las celdas de una tabla.
 * 
 * @since 29/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface BaseTableCellEditor extends TableCellEditor, Serializable {

	/**
	 * La cantidad de click que hacemos para editar la celda.
	 */
	public static final int CLICK_COUNT = 2;
}