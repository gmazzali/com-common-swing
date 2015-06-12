package com.common.swing.domain.icon.impl;

import javax.swing.ImageIcon;

import com.common.swing.domain.icon.IconResources;

/**
 * La clase que va a contener el listado de los iconos para edición de elementos.
 * 
 * @since 06/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class EditIcon extends IconResources {
	private static final long serialVersionUID = 1L;

	/**
	 * El path de los recursos de acción.
	 */
	public static final String EDIT_RESOURCE_PATH = IconResources.RESOURCE_PATH + "edit/";

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
		return IconResources.createStaticImage(EditIcon.EDIT_RESOURCE_PATH + name, EditIcon.width, EditIcon.height);
	}

	/**
	 * El icono para deshacer.
	 */
	public static final ImageIcon UNDO_ICON = EditIcon.create("undo_32.png");
	/**
	 * El icono para rehacer.
	 */
	public static final ImageIcon REDO_ICON = EditIcon.create("redo_32.png");

	/**
	 * El icono de copiar elementos.
	 */
	public static final ImageIcon COPY_ELEMENT_ICON = EditIcon.create("copy_32.png");
	/**
	 * El icono de cortar elementos.
	 */
	public static final ImageIcon CUT_ELEMENT_ICON = EditIcon.create("cut_32.png");
	/**
	 * El icono de pegar elementos.
	 */
	public static final ImageIcon PASTE_ELEMENT_ICON = EditIcon.create("paste_32.png");

	/**
	 * El icono para refrescar elementos.
	 */
	public static final ImageIcon REFRESH_ELEMENT_ICON = EditIcon.create("refresh_32.png");
	/**
	 * El icono para buscar elementos.
	 */
	public static final ImageIcon SEARCH_ELEMENT_ICON = EditIcon.create("search_32.png");
}