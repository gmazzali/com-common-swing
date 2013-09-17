package com.common.swing.icon;

import javax.swing.ImageIcon;

/**
 * La clase que contiene los iconos para las acciones de los medios dentro de las ventanas hechos con SWING.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class MediaIcon {

	/**
	 * Los anchos y alto de las imágenes.
	 */
	private static Integer height = 30;
	private static Integer width = 30;

	/**
	 * El path de los recursos de acción.
	 */
	private static final String MEDIA_RESOURCE_PATH = IconResources.RESOURCE_PATH + "media/";

	/**
	 * El icono para agregar medios.
	 */
	public static final ImageIcon ADD_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "add_32.png", MediaIcon.width,
			MediaIcon.height);
	/**
	 * El icono para quitar medios.
	 */
	public static final ImageIcon REMOVE_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "remove_32.png",
			MediaIcon.width, MediaIcon.height);
	/**
	 * El icono para cerrar un medio.
	 */
	public static final ImageIcon CLOSE_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "closed_32.png",
			MediaIcon.width, MediaIcon.height);
	/**
	 * El icono para chequear medios.
	 */
	public static final ImageIcon CHECK_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "check_32.png", MediaIcon.width,
			MediaIcon.height);
	/**
	 * El icono para retroceder medios.
	 */
	public static final ImageIcon BACKWARD_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "backward_32.png",
			MediaIcon.width, MediaIcon.height);
	/**
	 * El icono para avanzar medios.
	 */
	public static final ImageIcon FORWARD_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "forward_32.png",
			MediaIcon.width, MediaIcon.height);
	/**
	 * El icono de medio previo.
	 */
	public static final ImageIcon PREVIOUS_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "previous_32.png",
			MediaIcon.width, MediaIcon.height);
	/**
	 * El icono de medio siguiente.
	 */
	public static final ImageIcon NEXT_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "next_32.png", MediaIcon.width,
			MediaIcon.height);
	/**
	 * El icono para abrir medios.
	 */
	public static final ImageIcon OPEN_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "open_32.png", MediaIcon.width,
			MediaIcon.height);
	/**
	 * El icono para pausar medios.
	 */
	public static final ImageIcon PAUSE_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "pause_32.png", MediaIcon.width,
			MediaIcon.height);
	/**
	 * El icono para reproducir medios.
	 */
	public static final ImageIcon PLAY_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "play_32.png", MediaIcon.width,
			MediaIcon.height);
	/**
	 * El icono para grabar medios.
	 */
	public static final ImageIcon RECORD_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "record_32.png",
			MediaIcon.width, MediaIcon.height);
	/**
	 * El icono para detener medios.
	 */
	public static final ImageIcon STOP_MEDIA_ICON = IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + "stop_32.png", MediaIcon.width,
			MediaIcon.height);
}