package com.common.swing.util;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * La clase que va a contener todos los recursos graficos que vamos a ocupar dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class SwingResources {

	/**
	 * Los anchos y alto de las imágenes.
	 */
	private static Integer height = 15;
	private static Integer width = 15;

	/**
	 * El icono para "nueva entidad".
	 */
	public static ImageIcon ADD_ICON;
	/**
	 * El icono para "modificar entidad".
	 */
	public static ImageIcon MODIFY_ICON;
	/**
	 * El icono para "eliminar entidad".
	 */
	public static ImageIcon DELETE_ICON;

	/**
	 * El icono para "salir del abm de entidades".
	 */
	public static ImageIcon RETURN_ICON;

	/**
	 * Los archivos de los gif de progresos.
	 */
	public static ImageIcon PROGRESS_LIST_ICON;
	public static ImageIcon PROGRESS_SAVE_ICON;
	public static ImageIcon PROGRESS_BAR_ICON;

	static {
		SwingResources.ADD_ICON = new ImageIcon(new ImageIcon(SwingResources.class.getClassLoader().getResource("com/common/swing/image/add.png"))
				.getImage().getScaledInstance(SwingResources.width, SwingResources.height, Image.SCALE_SMOOTH));

		SwingResources.MODIFY_ICON = new ImageIcon(new ImageIcon(SwingResources.class.getClassLoader().getResource(
				"com/common/swing/image/modify.png")).getImage().getScaledInstance(SwingResources.width, SwingResources.height, Image.SCALE_SMOOTH));

		SwingResources.DELETE_ICON = new ImageIcon(new ImageIcon(SwingResources.class.getClassLoader().getResource(
				"com/common/swing/image/delete.png")).getImage().getScaledInstance(SwingResources.width, SwingResources.height, Image.SCALE_SMOOTH));

		SwingResources.RETURN_ICON = new ImageIcon(new ImageIcon(SwingResources.class.getClassLoader().getResource(
				"com/common/swing/image/return.png")).getImage().getScaledInstance(SwingResources.width, SwingResources.height, Image.SCALE_SMOOTH));

		SwingResources.PROGRESS_LIST_ICON = new ImageIcon(SwingResources.class.getClassLoader().getResource(
				"com/common/swing/image/progress_list.gif"));

		SwingResources.PROGRESS_SAVE_ICON = new ImageIcon(SwingResources.class.getClassLoader().getResource(
				"com/common/swing/image/progress_save.gif"));

		SwingResources.PROGRESS_BAR_ICON = new ImageIcon(SwingResources.class.getClassLoader().getResource("com/common/swing/image/progress_bar.gif"));
	}
}
