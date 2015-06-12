package com.crud.swing.view.form.util;

/**
 * La enumeraci�n que contiene los modos en los que puede ser llamado un formulario de edici�n de una entidad.
 * 
 * <pre>
 * {@link EditType#NEW}
 * {@link EditType#EDIT}
 * {@link EditType#VIEW}
 * </pre>
 * 
 * @since 05/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum EditType {

	/**
	 * El tipo de formulario para el alta de una entidad.
	 */
	NEW,
	/**
	 * El tipo de formulario para la edici�n.
	 */
	EDIT,
	/**
	 * El tipo de formulario para la visualizaci�n de la entidad.
	 */
	VIEW;
}