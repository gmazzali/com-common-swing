package com.common.swing.view.callback;

import java.io.Serializable;
import java.util.Collection;

/**
 * Interface para el manejo de eventos de un formulario de selección.
 * 
 * @since 05/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <T>
 *            El tipo de elemento que vamos a poder seleccionar.
 */
public interface CallbackSelect<T extends Serializable> extends Serializable {

	/**
	 * Se encarga de mandar los elementos seleccionados.
	 * 
	 * @param dataSelected
	 *            El conjunto de datos seleccionados.
	 */
	public abstract void select(Collection<T> dataSelected);
}