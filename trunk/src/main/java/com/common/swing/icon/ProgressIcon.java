package com.common.swing.icon;

import javax.swing.ImageIcon;

/**
 * La clase que contiene los iconos de progreso de actividades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ProgressIcon {

	/**
	 * El path de los recursos de acción.
	 */
	private static final String PROGRESS_RESOURCE_PATH = IconResources.RESOURCE_PATH + "progress/";

	/**
	 * El GIF de progreso en forma de barra horizontal.
	 */
	public static ImageIcon PROGRESS_HORIZONTAL_BAR_ICON;
	/**
	 * El GIF de progreso en forma de barra vertical.
	 */
	public static ImageIcon PROGRESS_VERTICAL_BAR_ICON;
	/**
	 * El GIF de progreso en forma circular con puntos.
	 */
	public static ImageIcon PROGRESS_CIRCULAR_DOT_ICON;
	/**
	 * El GIF de progreso en forma circular con barras.
	 */
	public static ImageIcon PROGRESS_CIRCULAR_BAR_ICON;

	static {
		ProgressIcon.PROGRESS_HORIZONTAL_BAR_ICON = new ImageIcon(ProgressIcon.class.getClassLoader().getResource(
				ProgressIcon.PROGRESS_RESOURCE_PATH + "horizontal_progress_bar.gif"));

		ProgressIcon.PROGRESS_VERTICAL_BAR_ICON = new ImageIcon(ProgressIcon.class.getClassLoader().getResource(
				ProgressIcon.PROGRESS_RESOURCE_PATH + "progress_list.gif"));

		ProgressIcon.PROGRESS_CIRCULAR_DOT_ICON = new ImageIcon(ProgressIcon.class.getClassLoader().getResource(
				ProgressIcon.PROGRESS_RESOURCE_PATH + "progress_save.gif"));

		ProgressIcon.PROGRESS_CIRCULAR_BAR_ICON = new ImageIcon(ProgressIcon.class.getClassLoader().getResource(
				ProgressIcon.PROGRESS_RESOURCE_PATH + "progress_save.gif"));
	}
}