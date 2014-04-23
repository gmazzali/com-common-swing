package com.common.swing.business.icon;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * La clase que va a contener el listado de los iconos para edición de elementos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class EditIcon {

	/**
	 * Los anchos y alto de las imágenes.
	 */
	private static Integer height = 30;
	private static Integer width = 30;

	/**
	 * El path de los recursos de acción.
	 */
	private static final String EDIT_RESOURCE_PATH = IconResources.RESOURCE_PATH + "edit/";

	/**
	 * El icono para deshacer.
	 */
	public static ImageIcon UNDO_ICON;
	/**
	 * El icono para rehacer.
	 */
	public static ImageIcon REDO_ICON;

	/**
	 * El icono de copiar elementos.
	 */
	public static ImageIcon COPY_ELEMENT_ICON;
	/**
	 * El icono de cortar elementos.
	 */
	public static ImageIcon CUT_ELEMENT_ICON;
	/**
	 * El icono de pegar elementos.
	 */
	public static ImageIcon PASTE_ELEMENT_ICON;

	/**
	 * El icono para refrescar elementos.
	 */
	public static ImageIcon REFRESH_ELEMENT_ICON;
	/**
	 * El icono para buscar elementos.
	 */
	public static ImageIcon SEARCH_ELEMENT_ICON;

	static {
		// Deshacer y Rehacer
		EditIcon.UNDO_ICON = new ImageIcon(new ImageIcon(EditIcon.class.getClassLoader().getResource(EditIcon.EDIT_RESOURCE_PATH + "undo_32.png"))
				.getImage().getScaledInstance(EditIcon.width, EditIcon.height, Image.SCALE_SMOOTH));

		EditIcon.REDO_ICON = new ImageIcon(new ImageIcon(EditIcon.class.getClassLoader().getResource(EditIcon.EDIT_RESOURCE_PATH + "redo_32.png"))
				.getImage().getScaledInstance(EditIcon.width, EditIcon.height, Image.SCALE_SMOOTH));

		// Copiar, Cortar y Pegar.
		EditIcon.COPY_ELEMENT_ICON = new ImageIcon(new ImageIcon(EditIcon.class.getClassLoader().getResource(EditIcon.EDIT_RESOURCE_PATH + "copy_32.png"))
				.getImage().getScaledInstance(EditIcon.width, EditIcon.height, Image.SCALE_SMOOTH));

		EditIcon.CUT_ELEMENT_ICON = new ImageIcon(new ImageIcon(EditIcon.class.getClassLoader().getResource(EditIcon.EDIT_RESOURCE_PATH + "cut_32.png"))
				.getImage().getScaledInstance(EditIcon.width, EditIcon.height, Image.SCALE_SMOOTH));

		EditIcon.PASTE_ELEMENT_ICON = new ImageIcon(new ImageIcon(EditIcon.class.getClassLoader().getResource(EditIcon.EDIT_RESOURCE_PATH + "paste_32.png"))
				.getImage().getScaledInstance(EditIcon.width, EditIcon.height, Image.SCALE_SMOOTH));

		// Buscar y Refrescar.
		EditIcon.REFRESH_ELEMENT_ICON = new ImageIcon(new ImageIcon(EditIcon.class.getClassLoader().getResource(
				EditIcon.EDIT_RESOURCE_PATH + "refresh_32.png")).getImage().getScaledInstance(EditIcon.width, EditIcon.height, Image.SCALE_SMOOTH));

		EditIcon.SEARCH_ELEMENT_ICON = new ImageIcon(new ImageIcon(EditIcon.class.getClassLoader().getResource(
				EditIcon.EDIT_RESOURCE_PATH + "search_32.png")).getImage().getScaledInstance(EditIcon.width, EditIcon.height, Image.SCALE_SMOOTH));
	}
}