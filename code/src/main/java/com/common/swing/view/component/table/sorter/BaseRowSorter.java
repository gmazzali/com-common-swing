package com.common.swing.view.component.table.sorter;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Define un ordenador base de filas de una tabla.
 * 
 * @since 29/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <M>
 *            El modelo que vamos a tener dentro de este ordenador.
 */
public class BaseRowSorter<M extends TableModel> extends TableRowSorter<M> {

	/**
	 * Constructor por default.
	 */
	public BaseRowSorter() {
		super();
	}

	/**
	 * Constructor que recibe el modelo de la tabla.
	 * 
	 * @param model
	 *            El modelo de la tabla.
	 */
	public BaseRowSorter(M model) {
		super(model);
	}

	@Override
	public int convertRowIndexToModel(int index) {
		int rowModel = 0;
		try {
			rowModel = super.convertRowIndexToModel(index);
		} catch(Exception e) {
		}
		return rowModel;
	}
}