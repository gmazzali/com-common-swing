package com.common.swing.view.event;

import java.util.Collection;
import java.util.Collections;

import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.table.BaseTable;

/**
 * Permite definir un evento de la tabla.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de elementos que contiene la tabla que disparo el evento.
 */
public class TableEvent<E extends RowBean> extends BaseEvent<E> {
	private static final long serialVersionUID = 1L;

	/**
	 * La tabla que disparo el evento.
	 */
	protected BaseTable<E> table;

	/**
	 * Crea un evento de la tabla.
	 * 
	 * @param table
	 *            La tabla que disparo el evento.
	 */
	public TableEvent(BaseTable<E> table) {
		this.table = table;
	}

	/**
	 * Retorna las entidades seleccionadas de la tabla.
	 * 
	 * @return Las entidades.
	 */
	public Collection<E> getSelectedEntities() {
		return Collections.unmodifiableCollection(this.table.getValues());
	}

	/**
	 * Retorna la tabla que disparo el evento.
	 * 
	 * @return La tabla que disparo el evento.
	 */
	public BaseTable<E> getTable() {
		return table;
	}
}