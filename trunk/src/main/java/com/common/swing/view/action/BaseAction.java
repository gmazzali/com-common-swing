package com.common.swing.view.action;

import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;

import com.common.swing.view.action.parameter.BaseActionParameter;
import com.common.swing.view.decorator.ButtonDecorator;

/**
 * Permite establecer una acci�n base para los componentes.
 * 
 * @since 28/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de los elementos que vamos a tener dentro de esta acci�n.
 */
public abstract class BaseAction<E extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * El bot�n de la acci�n.
	 */
	private JButton button;
	/**
	 * El decorador del bot�n.
	 */
	private ButtonDecorator buttonDecorator;

	/**
	 * Contructor de una acci�n base.
	 * 
	 * @param buttonDecorator
	 *            El decorador del bot�n de la acci�n.
	 */
	public BaseAction(ButtonDecorator buttonDecorator) {
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
			this.button.addActionListener(this.getActionListener());
		}
		return this.button;
	}

	/**
	 * Permite definir si la acci�n va a estar activa.
	 * 
	 * @param parameter
	 *            El par�metro de la acci�n.
	 * @return <code>true</code> en caso de que la acci�n este habilitada para la entidad, en caso contrario, retorna <code>false</code>.
	 */
	public <P extends BaseActionParameter<E>> boolean  isEnabledAction(P parameter) {
		return true;
	}

	/**
	 * Permite definir si la acci�n va a estar visible.
	 * 
	 * @param parameter
	 *            El par�metro de la acci�n.
	 * @return <code>true</code> en caso de que la acci�n este visible para la entidad, en caso contrario, retorna <code>false</code>.
	 */
	public <P extends BaseActionParameter<E>> boolean isVisibleAction(P parameter) {
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

	/**
	 * Retorna la acci�n que va a desencadenarse.
	 * 
	 * @return La acci�n.
	 */
	protected abstract ActionListener getActionListener();
}