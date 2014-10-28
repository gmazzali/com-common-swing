package com.common.swing.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.common.swing.view.bean.SearchBean;
import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.SearchEvent;
import com.common.swing.view.listener.SearchListener;

/**
 * Permite establecer una acci�n para un filtro de b�squeda.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de los elementos que vamos a recuperar con el filtro de b�squeda.
 */
public class SearchAction<E extends SearchBean> extends BaseAction<E> {
	private static final long serialVersionUID = 1L;

	/**
	 * El escuchador del filtro de b�squeda.
	 */
	private SearchListener<E> searchListener;

	/**
	 * El constructor de una acci�n de un filtro de b�squeda.
	 * 
	 * @param filterListener
	 *            El escuchador del filtro de b�squeda.
	 * @param buttonDecorator
	 *            El decorador del bot�n.
	 */
	public SearchAction(SearchListener<E> searchListener, ButtonDecorator buttonDecorator) {
		super(buttonDecorator);
		this.searchListener = searchListener;
	}

	@Override
	protected ActionListener getActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchListener.fireEvent(new SearchEvent<E>());
			}
		};
	}
}