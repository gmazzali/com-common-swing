package com.common.swing.view.crud.util;

/**
 * La enumeración que contiene los modos en los que puede ser llamado un formulario de edición de una entidad.
 * 
 * <pre>
 * {@link FormTypeEnum#NEW}
 * {@link FormTypeEnum#EDIT}
 * {@link FormTypeEnum#VIEW}
 * </pre>
 * 
 * @since 05/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum FormTypeEnum {

	/**
	 * El tipo de formulario para el alta de una entidad.
	 */
	NEW,
	/**
	 * El tipo de formulario para la edición.
	 */
	EDIT,
	/**
	 * El tipo de formulario para la visualización de la entidad.
	 */
	VIEW;
}