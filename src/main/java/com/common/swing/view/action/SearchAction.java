package com.common.swing.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.common.swing.view.bean.SearchBean;
import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.SearchEvent;
import com.common.swing.view.listener.SearchListener;

/**
 * Permite establecer una acción para un filtro de búsqueda.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de los elementos que vamos a recuperar con el filtro de búsqueda.
 */
public class SearchAction<E extends SearchBean> extends BaseAction<E> {
	private static final long serialVersionUID = 1L;

	/**
	 * El escuchador del filtro de búsqueda.
	 */
	private SearchListener<E> searchListener;

	/**
	 * El constructor de una acción de un filtro de búsqueda.
	 * 
	 * @param filterListener
	 *            El escuchador del filtro de búsqueda.
	 * @param buttonDecorator
	 *            El decorador del botón.
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