package com.common.swing.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.common.swing.view.bean.EditBean;
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
public class EditAction<E extends EditBean> extends BaseAction<E> {
	private static final long serialVersionUID = 1L;

	/**
	 * El escuchador de la edici�n.
	 */
	private EditListener<E> editListener;

	/**
	 * El constructor del escuchador de la edici�n.
	 * 
	 * @param filterListener
	 *            El escuchador de la edici�n.
	 * @param buttonDecorator
	 *            El decorador del bot�n.
	 */
	public EditAction(EditListener<E> editListener, ButtonDecorator buttonDecorator) {
		super(buttonDecorator);
		this.editListener = editListener;
	}

	@Override
	protected ActionListener getActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editListener.fireEvent(new EditEvent<E>());
			}
		};
	}
}