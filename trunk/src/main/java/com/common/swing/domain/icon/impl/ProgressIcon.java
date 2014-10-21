package com.common.swing.domain.icon.impl;

import javax.swing.ImageIcon;

import com.common.swing.domain.icon.IconResources;

/**
 * La clase que contiene los iconos de progreso de actividades.
 * 
 * @since 06/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ProgressIcon extends IconResources {
	private static final long serialVersionUID = 1L;

	/**
	 * El path de los recursos de acción.
	 */
	public static final String PROGRESS_RESOURCE_PATH = IconResources.RESOURCE_PATH + "progress/";

	/**
	 * Permite crear de manera rápida los iconos.
	 * 
	 * @param name
	 *            El nombre del archivo.
	 * @return El icono creado.
	 */
	public static ImageIcon create(String name) {
		return IconResources.createGifImage(ProgressIcon.PROGRESS_RESOURCE_PATH + name);
	}

	/**
	 * El GIF de progreso en forma de barra horizontal.
	 */
	public static final ImageIcon PROGRESS_HORIZONTAL_BAR_ICON = ProgressIcon.create("progress_circle_bar_32.gif");
	/**
	 * El GIF de progreso en forma de barra vertical.
	 */
	public static final ImageIcon PROGRESS_VERTICAL_BAR_ICON = ProgressIcon.create("progress_circle_bar_32.gif");
	/**
	 * El GIF de progreso en forma circular con puntos.
	 */
	public static final ImageIcon PROGRESS_CIRCULAR_DOT_ICON = ProgressIcon.create("progress_circle_bar_32.gif");
	/**
	 * El GIF de progreso en forma circular con barras.
	 */
	public static final ImageIcon PROGRESS_CIRCULAR_BAR_ICON = ProgressIcon.create("progress_circle_bar_32.gif");
}