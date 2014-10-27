package com.common.swing.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;

import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.EditEvent;
import com.common.swing.view.listener.EditListener;

/**
 * Permite establecer una acción para un panel de edición.
 * 
 * @since 27/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de los elementos que vamos a editar.
 */
public class EditAction<E extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * El escuchador de la edición.
	 */
	private EditListener<E> editListener;
	/**
	 * El botón de la acción.
	 */
	private JButton button;
	/**
	 * El decorador del botón.
	 */
	private ButtonDecorator buttonDecorator;

	/**
	 * El constructor del escuchador de la edición.
	 * 
	 * @param filterListener
	 *            El escuchador de la edición.
	 * @param buttonDecorator
	 *            El decorador del botón.
	 */
	public EditAction(EditListener<E> editListener, ButtonDecorator buttonDecorator) {
		this.editListener = editListener;
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
					editListener.fireEvent(new EditEvent<E>());
				}
			});
		}
		return this.button;
	}

	/**
	 * Permite definir si la acción va a estar activa.
	 * 
	 * @param entity
	 *            La entidad que tenemos dentro del panel de edición.
	 * @return <code>true</code> en caso de que la acción este habilitada para la entidad, en caso contrario, retorna <code>false</code>.
	 */
	public boolean isEnabledAction(E entity) {
		return true;
	}

	/**
	 * Permite definir si la acción va a estar visible.
	 * 
	 * @param entity
	 *            La entidad que tenemos dentro del panel de edición.
	 * @return <code>true</code> en caso de que la acción este visible para la entidad, en caso contrario, retorna <code>false</code>.
	 */
	public boolean isVisibleAction(E entity) {
		return true;
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