package com.common.swing.icon;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * La clase que va a contener el listado de los iconos que vamos a poder utilizar para las acciones dentro de las ventanas creadas con SWING.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ActionIcon extends IconResources {

	/**
	 * Los anchos y alto de las imágenes.
	 */
	private static Integer height = 30;
	private static Integer width = 30;

	/**
	 * El path de los recursos de acción.
	 */
	private static final String ACTION_RESOURCE_PATH = IconResources.RESOURCE_PATH + "action/";

	/**
	 * El icono para agregar elementos.
	 */
	public static ImageIcon ADD_ELEMENT_ICON;
	/**
	 * El icono para editar elementos.
	 */
	public static ImageIcon EDIT_ELEMENT_ICON;
	/**
	 * El icono para quitar elementos.
	 */
	public static ImageIcon REMOVE_ELEMENT_ICON;
	/**
	 * El icono para seleccionar elementos.
	 */
	public static ImageIcon SELECT_ELEMENT_ICON;

	/**
	 * El icono para procesar elementos.
	 */
	public static ImageIcon PROCCESS_ELEMENT_ICON;

	/**
	 * El icono para bloquear elementos.
	 */
	public static ImageIcon LOCK_ELEMENT_ICON;
	/**
	 * El icono para desbloquear elementos.
	 */
	public static ImageIcon UNLOCK_ELEMENT_ICON;

	/**
	 * El icono para aceptar una ventana.
	 */
	public static ImageIcon COMMIT_ICON;
	/**
	 * El icono para cancelar una ventana.
	 */
	public static ImageIcon REJECT_ICON;

	static {
		// Acciones sobre los elementos.
		ActionIcon.ADD_ELEMENT_ICON = new ImageIcon(new ImageIcon(ActionIcon.class.getClassLoader().getResource(
				ActionIcon.ACTION_RESOURCE_PATH + "add_32.png")).getImage()
				.getScaledInstance(ActionIcon.width, ActionIcon.height, Image.SCALE_SMOOTH));

		ActionIcon.EDIT_ELEMENT_ICON = new ImageIcon(new ImageIcon(ActionIcon.class.getClassLoader().getResource(
				ActionIcon.ACTION_RESOURCE_PATH + "edit_32.png")).getImage().getScaledInstance(ActionIcon.width, ActionIcon.height,
				Image.SCALE_SMOOTH));

		ActionIcon.REMOVE_ELEMENT_ICON = new ImageIcon(new ImageIcon(ActionIcon.class.getClassLoader().getResource(
				ActionIcon.ACTION_RESOURCE_PATH + "remove_32.png")).getImage().getScaledInstance(ActionIcon.width, ActionIcon.height,
				Image.SCALE_SMOOTH));

		ActionIcon.SELECT_ELEMENT_ICON = new ImageIcon(new ImageIcon(ActionIcon.class.getClassLoader().getResource(
				ActionIcon.ACTION_RESOURCE_PATH + "select_32.png")).getImage().getScaledInstance(ActionIcon.width, ActionIcon.height,
				Image.SCALE_SMOOTH));

		// Procesamiento de los elementos.
		ActionIcon.PROCCESS_ELEMENT_ICON = new ImageIcon(new ImageIcon(ActionIcon.class.getClassLoader().getResource(
				ActionIcon.ACTION_RESOURCE_PATH + "proccess_32.png")).getImage().getScaledInstance(ActionIcon.width, ActionIcon.height,
				Image.SCALE_SMOOTH));

		// Bloque y desbloqueo de elementos.
		ActionIcon.LOCK_ELEMENT_ICON = new ImageIcon(new ImageIcon(ActionIcon.class.getClassLoader().getResource(
				ActionIcon.ACTION_RESOURCE_PATH + "lock_32.png")).getImage().getScaledInstance(ActionIcon.width, ActionIcon.height,
				Image.SCALE_SMOOTH));

		ActionIcon.UNLOCK_ELEMENT_ICON = new ImageIcon(new ImageIcon(ActionIcon.class.getClassLoader().getResource(
				ActionIcon.ACTION_RESOURCE_PATH + "unlock_32.png")).getImage().getScaledInstance(ActionIcon.width, ActionIcon.height,
				Image.SCALE_SMOOTH));

		// Acciones sobre la ventana.
		ActionIcon.COMMIT_ICON = new ImageIcon(new ImageIcon(ActionIcon.class.getClassLoader().getResource(
				ActionIcon.ACTION_RESOURCE_PATH + "commit_1_32.png")).getImage().getScaledInstance(ActionIcon.width, ActionIcon.height,
				Image.SCALE_SMOOTH));

		ActionIcon.REJECT_ICON = new ImageIcon(new ImageIcon(ActionIcon.class.getClassLoader().getResource(
				ActionIcon.ACTION_RESOURCE_PATH + "reject_1_32.png")).getImage().getScaledInstance(ActionIcon.width, ActionIcon.height,
				Image.SCALE_SMOOTH));
	}
}