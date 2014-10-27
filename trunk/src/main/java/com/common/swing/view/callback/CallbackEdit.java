package com.common.swing.view.callback;

import java.io.Serializable;

import com.common.swing.view.bean.EditBean;

/**
 * Interfaz para el manejo de eventos de un formulario de alta / modificación / visualización.
 * 
 * @since 05/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            El tipo de objeto que vamos a editar.
 */
public interface CallbackEdit<E extends EditBean> extends Serializable {

	/**
	 * Permite confirmar un formulario de alta / modificación / visualización.
	 * 
	 * @param entity
	 *            La entidad del bean que acabamos de editar.
	 */
	public void confirm(E entity);

	/**
	 * Permite cancelar un formulario de alta / modificación / visualización.
	 */
	public void cancel();
}