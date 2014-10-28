package com.common.swing.view.action;

import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;

import com.common.swing.view.action.parameter.BaseActionParameter;
import com.common.swing.view.decorator.ButtonDecorator;

/**
 * Permite establecer una acción base para los componentes.
 * 
 * @since 28/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de los elementos que vamos a tener dentro de esta acción.
 */
public abstract class BaseAction<E extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * El botón de la acción.
	 */
	private JButton button;
	/**
	 * El decorador del botón.
	 */
	private ButtonDecorator buttonDecorator;

	/**
	 * Contructor de una acción base.
	 * 
	 * @param buttonDecorator
	 *            El decorador del botón de la acción.
	 */
	public BaseAction(ButtonDecorator buttonDecorator) {
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
			this.button.addActionListener(this.getActionListener());
		}
		return this.button;
	}

	/**
	 * Permite definir si la acción va a estar activa.
	 * 
	 * @param parameter
	 *            El parámetro de la acción.
	 * @return <code>true</code> en caso de que la acción este habilitada para la entidad, en caso contrario, retorna <code>false</code>.
	 */
	public <P extends BaseActionParameter<E>> boolean  isEnabledAction(P parameter) {
		return true;
	}

	/**
	 * Permite definir si la acción va a estar visible.
	 * 
	 * @param parameter
	 *            El parámetro de la acción.
	 * @return <code>true</code> en caso de que la acción este visible para la entidad, en caso contrario, retorna <code>false</code>.
	 */
	public <P extends BaseActionParameter<E>> boolean isVisibleAction(P parameter) {
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

	/**
	 * Retorna la acción que va a desencadenarse.
	 * 
	 * @return La acción.
	 */
	protected abstract ActionListener getActionListener();
}