package com.common.swing.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;

import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.EditEvent;
import com.common.swing.view.listener.EditListener;

/**
 * Permite establecer una acci�n para un panel de edici�n.
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
	 * El escuchador de la edici�n.
	 */
	private EditListener<E> editListener;
	/**
	 * El bot�n de la acci�n.
	 */
	private JButton button;
	/**
	 * El decorador del bot�n.
	 */
	private ButtonDecorator buttonDecorator;

	/**
	 * El constructor del escuchador de la edici�n.
	 * 
	 * @param filterListener
	 *            El escuchador de la edici�n.
	 * @param buttonDecorator
	 *            El decorador del bot�n.
	 */
	public EditAction(EditListener<E> editListener, ButtonDecorator buttonDecorator) {
		this.editListener = editListener;
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
					editListener.fireEvent(new EditEvent<E>());
				}
			});
		}
		return this.button;
	}

	/**
	 * Permite definir si la acci�n va a estar activa.
	 * 
	 * @param entity
	 *            La entidad que tenemos dentro del panel de edici�n.
	 * @return <code>true</code> en caso de que la acci�n este habilitada para la entidad, en caso contrario, retorna <code>false</code>.
	 */
	public boolean isEnabledAction(E entity) {
		return true;
	}

	/**
	 * Permite definir si la acci�n va a estar visible.
	 * 
	 * @param entity
	 *            La entidad que tenemos dentro del panel de edici�n.
	 * @return <code>true</code> en caso de que la acci�n este visible para la entidad, en caso contrario, retorna <code>false</code>.
	 */
	public boolean isVisibleAction(E entity) {
		return true;
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