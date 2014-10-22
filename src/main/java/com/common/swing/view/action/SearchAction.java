package com.common.swing.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;

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
public class SearchAction<E extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * El escuchador del filtro de b�squeda..
	 */
	private SearchListener<E> searchListener;
	/**
	 * El bot�n de la acci�n.
	 */
	private JButton button;
	/**
	 * El decorador del bot�n.
	 */
	private ButtonDecorator buttonDecorator;

	/**
	 * El constructor de una acci�n de un filtro de b�squeda.
	 * 
	 * @param filterListener
	 *            El escuchador del filtro de b�squeda.
	 * @param buttonDecorator
	 *            El decorador del bot�n.
	 */
	public SearchAction(SearchListener<E> searchListener, ButtonDecorator buttonDecorator) {
		this.searchListener = searchListener;
		this.buttonDecorator = buttonDecorator;
	}

	/**
	 * Permite crear un bot�n dado de acuerdo al escuchador y el decorador recibido.
	 * 
	 * @return El bot�n creado.
	 */
	public JButton createButton() {
		if (this.button == null) {
			this.button = new JButton();
			this.buttonDecorator.decorateButton(this.button);
			this.button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					searchListener.fireEvent(new SearchEvent<E>());
				}
			});
		}
		return this.button;
	}

	/**
	 * Permite recuperar el bot�n de la acci�n.
	 * 
	 * @return El bot�n de la acci�n.
	 */
	public JButton getButton() {
		return button;
	}
}