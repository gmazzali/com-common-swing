package com.common.swing.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.table.BaseTable;
import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.TableEvent;
import com.common.swing.view.listener.TableListener;

/**
 * Permite establecer una acción para una tabla.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de las entidades que vamos a cargar dentro de la tabla.
 */
public class TableAction<E extends RowBean> extends BaseAction<E> {
	private static final long serialVersionUID = 1L;

	/**
	 * Es escuchador de la acción.
	 */
	private TableListener<E> tableListener;
	/**
	 * La tabla base.
	 */
	protected BaseTable<E> table;

	/**
	 * El constructor de una acción de una tabla que recibe la misma.
	 * 
	 * @param table
	 *            La tabla sobre la que vamos a crear la acción.
	 * @param tableListener
	 *            El escuchador de la acción.
	 * @param buttonDecorator
	 *            El decorador del botón.
	 */
	public TableAction(BaseTable<E> table, TableListener<E> tableListener, ButtonDecorator buttonDecorator) {
		super(buttonDecorator);
		this.table = table;
		this.tableListener = tableListener;
	}

	@Override
	protected ActionListener getActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tableListener.fireEvent(new TableEvent<E>(table));
			}
		};
	}
}