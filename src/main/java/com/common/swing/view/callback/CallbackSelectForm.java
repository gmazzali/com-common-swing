package com.common.swing.view.callback;

import java.io.Serializable;
import java.util.List;

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
public interface CallbackSelectForm<T> extends Serializable {

	/**
	 * Se encarga de cargar dentro del formulario de llamada los elementos seleccionados dentro de este.
	 * 
	 * @param data
	 *            El conjunto de datos seleccionados dentro de este formulario.
	 */
	public abstract void select(List<T> data);
}