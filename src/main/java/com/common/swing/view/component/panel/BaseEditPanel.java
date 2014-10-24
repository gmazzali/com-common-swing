package com.common.swing.view.component.panel;

import javax.swing.JPanel;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.view.bean.EditBean;

/**
 * La clase que permite definir un panel donde vamos a desplegar los campos para la edición o visualización de datos.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <B>
 *            El bean de edición que vamos a manipular dentro de este panel.
 */
public abstract class BaseEditPanel<B extends EditBean> extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * El constructor de un panel de edición.
	 */
	public BaseEditPanel() {
		this.init();
	}

	/**
	 * Permite inicializar el panel.
	 */
	protected abstract void init();

	/**
	 * Permite vaciar el contenido del panel de edición.
	 */
	public abstract void emptyFields();

	/**
	 * Se encarga de validar y crear el bean que estamos editando dentro de este panel.
	 * 
	 * @return El bean que acabamos de editar dentro del panel.
	 * @throws SwingException
	 *             En caso de que algún campo no cumpla las condiciones necesarias.
	 */
	public abstract B getBean() throws SwingException;
}