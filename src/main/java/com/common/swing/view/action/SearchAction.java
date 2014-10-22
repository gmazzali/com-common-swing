package com.common.swing.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;

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
public class SearchAction<E extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * El escuchador del filtro de búsqueda..
	 */
	private SearchListener<E> searchListener;
	/**
	 * El botón de la acción.
	 */
	private JButton button;
	/**
	 * El decorador del botón.
	 */
	private ButtonDecorator buttonDecorator;

	/**
	 * El constructor de una acción de un filtro de búsqueda.
	 * 
	 * @param filterListener
	 *            El escuchador del filtro de búsqueda.
	 * @param buttonDecorator
	 *            El decorador del botón.
	 */
	public SearchAction(SearchListener<E> searchListener, ButtonDecorator buttonDecorator) {
		this.searchListener = searchListener;
		this.buttonDecorator = buttonDecorator;
	}

	/**
	 * Permite crear un botón dado de acuerdo al escuchador y el decorador recibido.
	 * 
	 * @return El botón creado.
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
	 * Permite recuperar el botón de la acción.
	 * 
	 * @return El botón de la acción.
	 */
	public JButton getButton() {
		return button;
	}
}