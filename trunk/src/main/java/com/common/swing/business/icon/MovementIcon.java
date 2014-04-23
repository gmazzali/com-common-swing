package com.common.swing.business.icon;

import javax.swing.ImageIcon;

/**
 * La clase que contiene los iconos de direcciones a usarse dentro de las ventanas creadas con SWING.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class MovementIcon {

	/**
	 * Los anchos y alto de las imágenes.
	 */
	private static final Integer height = 30;
	private static final Integer width = 30;

	/**
	 * El path de los recursos de acción.
	 */
	private static final String MOVEMENT_RESOURCE_PATH = IconResources.RESOURCE_PATH + "movement/";

	/**
	 * El iconos de flecha arriba.
	 */
	public static final ImageIcon ARROW_UP_ICON = IconResources.createStaticImage(MovementIcon.MOVEMENT_RESOURCE_PATH + "arrow_up_32.png",
			MovementIcon.width, MovementIcon.height);
	/**
	 * El iconos de flecha arriba a la derecha.
	 */
	public static final ImageIcon ARROW_UP_RIGHT_ICON = IconResources.createStaticImage(
			MovementIcon.MOVEMENT_RESOURCE_PATH + "arrow_up_right_32.png", MovementIcon.width, MovementIcon.height);
	/**
	 * El iconos de flecha a la derecha.
	 */
	public static final ImageIcon ARROW_RIGHT_ICON = IconResources.createStaticImage(MovementIcon.MOVEMENT_RESOURCE_PATH + "arrow_right_32.png",
			MovementIcon.width, MovementIcon.height);
	/**
	 * El iconos de flecha abajo a la derecha.
	 */
	public static final ImageIcon ARROW_DOWN_RIGHT_ICON = IconResources.createStaticImage(MovementIcon.MOVEMENT_RESOURCE_PATH
			+ "arrow_down_right_32.png", MovementIcon.width, MovementIcon.height);
	/**
	 * El iconos de flecha abajo.
	 */
	public static final ImageIcon ARROW_DOWN_ICON = IconResources.createStaticImage(MovementIcon.MOVEMENT_RESOURCE_PATH + "arrow_down_32.png",
			MovementIcon.width, MovementIcon.height);
	/**
	 * El iconos de flecha abajo a la izquierda.
	 */
	public static final ImageIcon ARROW_DOWN_LEFT_ICON = IconResources.createStaticImage(MovementIcon.MOVEMENT_RESOURCE_PATH
			+ "arrow_down_left_32.png", MovementIcon.width, MovementIcon.height);
	/**
	 * El iconos de flecha a la izquierda.
	 */
	public static final ImageIcon ARROW_LEFT_ICON = IconResources.createStaticImage(MovementIcon.MOVEMENT_RESOURCE_PATH + "arrow_left_32.png",
			MovementIcon.width, MovementIcon.height);
	/**
	 * El iconos de flecha arriba a la izquierda.
	 */
	public static final ImageIcon ARROW_UP_LEFT_ICON = IconResources.createStaticImage(MovementIcon.MOVEMENT_RESOURCE_PATH + "arrow_up_left_32.png",
			MovementIcon.width, MovementIcon.height);
}