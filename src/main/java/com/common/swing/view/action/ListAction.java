package com.common.swing.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.list.BaseList;
import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.ListEvent;
import com.common.swing.view.listener.ListListener;

/**
 * Permite establecer una acción para una lista.
 * 
 * @since 10/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de las entidades que vamos a cargar dentro de la lista.
 */
public class ListAction<E extends RowBean> extends BaseAction<E> {
	private static final long serialVersionUID = 1L;

	/**
	 * Es escuchador de la acción.
	 */
	private final ListListener<E> listListener;
	/**
	 * La lista base.
	 */
	protected BaseList<E> list;

	/**
	 * El constructor de una acción de una lista que recibe la misma.
	 * 
	 * @param list
	 *            La lista sobre la que vamos a crear la acción.
	 * @param listListener
	 *            El escuchador de la acción.
	 * @param buttonDecorator
	 *            El decorador del botón.
	 */
	public ListAction(BaseList<E> list, ListListener<E> listListener, ButtonDecorator buttonDecorator) {
		super(buttonDecorator);
		this.list = list;
		this.listListener = listListener;
	}

	@Override
	protected ActionListener getActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listListener.fireEvent(new ListEvent<E>(list));
			}
		};
	}
}