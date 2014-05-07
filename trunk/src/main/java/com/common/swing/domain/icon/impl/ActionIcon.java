package com.common.swing.domain.icon.impl;

import javax.swing.ImageIcon;

import com.common.swing.domain.icon.IconResources;

/**
 * La clase que va a contener el listado de los iconos que vamos a poder utilizar para las acciones dentro de las ventanas creadas con SWING.
 * 
 * @since 06/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ActionIcon extends IconResources {
	private static final long serialVersionUID = 1L;

	/**
	 * El path de los recursos de acción.
	 */
	public static final String ACTION_RESOURCE_PATH = IconResources.RESOURCE_PATH + "action/";

	/**
	 * Los anchos y alto de las imágenes.
	 */
	private static Integer height = 30;
	private static Integer width = 30;

	/**
	 * Permite crear de manera rápida los iconos.
	 * 
	 * @param name
	 *            El nombre del archivo.
	 * @return El icono creado.
	 */
	public static ImageIcon create(String name) {
		return IconResources.createStaticImage(ActionIcon.ACTION_RESOURCE_PATH + name, ActionIcon.width, ActionIcon.height);
	}

	/**
	 * El icono para agregar elementos.
	 */
	public static final ImageIcon ADD_ELEMENT_ICON = ActionIcon.create("add_32.png");
	/**
	 * El icono para editar elementos.
	 */
	public static final ImageIcon EDIT_ELEMENT_ICON = ActionIcon.create("edit_32.png");
	/**
	 * El icono para quitar elementos.
	 */
	public static final ImageIcon REMOVE_ELEMENT_ICON = ActionIcon.create("remove_32.png");
	/**
	 * El icono para seleccionar elementos.
	 */
	public static final ImageIcon SELECT_ELEMENT_ICON = ActionIcon.create("select_32.png");

	/**
	 * El icono para procesar elementos.
	 */
	public static final ImageIcon PROCCESS_ELEMENT_ICON = ActionIcon.create("proccess_32.png");

	/**
	 * El icono para bloquear elementos.
	 */
	public static final ImageIcon LOCK_ELEMENT_ICON = ActionIcon.create("lock_32.png");
	/**
	 * El icono para desbloquear elementos.
	 */
	public static final ImageIcon UNLOCK_ELEMENT_ICON = ActionIcon.create("unlock_32.png");

	/**
	 * El icono para aceptar una ventana.
	 */
	public static final ImageIcon COMMIT_ICON = ActionIcon.create("commit_1_32.png");
	/**
	 * El icono para cancelar una ventana.
	 */
	public static final ImageIcon REJECT_ICON = ActionIcon.create("reject_1_32.png");
}