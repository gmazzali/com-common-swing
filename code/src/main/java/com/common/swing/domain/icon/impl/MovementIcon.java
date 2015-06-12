package com.common.swing.domain.icon.impl;

import javax.swing.ImageIcon;

import com.common.swing.domain.icon.IconResources;

/**
 * La clase que contiene los iconos de direcciones a usarse dentro de las ventanas creadas con SWING.
 * 
 * @since 06/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class MovementIcon extends IconResources {
	private static final long serialVersionUID = 1L;

	/**
	 * El path de los recursos de acción.
	 */
	public static final String MOVEMENT_RESOURCE_PATH = IconResources.RESOURCE_PATH + "movement/";

	/**
	 * Los anchos y alto de las imágenes.
	 */
	private static final Integer height = 30;
	private static final Integer width = 30;

	/**
	 * Permite crear de manera rápida los iconos.
	 * 
	 * @param name
	 *            El nombre del archivo.
	 * @return El icono creado.
	 */
	public static ImageIcon create(String name) {
		return IconResources.createStaticImage(MovementIcon.MOVEMENT_RESOURCE_PATH + name, MovementIcon.width, MovementIcon.height);
	}

	/**
	 * El iconos de flecha arriba.
	 */
	public static final ImageIcon ARROW_UP_ICON = MovementIcon.create("arrow_up_32.png");
	/**
	 * El iconos de flecha arriba a la derecha.
	 */
	public static final ImageIcon ARROW_UP_RIGHT_ICON = MovementIcon.create("arrow_up_right_32.png");
	/**
	 * El iconos de flecha a la derecha.
	 */
	public static final ImageIcon ARROW_RIGHT_ICON = MovementIcon.create("arrow_right_32.png");
	/**
	 * El iconos de flecha abajo a la derecha.
	 */
	public static final ImageIcon ARROW_DOWN_RIGHT_ICON = MovementIcon.create("arrow_down_right_32.png");
	/**
	 * El iconos de flecha abajo.
	 */
	public static final ImageIcon ARROW_DOWN_ICON = MovementIcon.create("arrow_down_32.png");
	/**
	 * El iconos de flecha abajo a la izquierda.
	 */
	public static final ImageIcon ARROW_DOWN_LEFT_ICON = MovementIcon.create("arrow_down_left_32.png");
	/**
	 * El iconos de flecha a la izquierda.
	 */
	public static final ImageIcon ARROW_LEFT_ICON = MovementIcon.create("arrow_left_32.png");
	/**
	 * El iconos de flecha arriba a la izquierda.
	 */
	public static final ImageIcon ARROW_UP_LEFT_ICON = MovementIcon.create("arrow_up_left_32.png");
}