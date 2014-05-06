package com.common.swing.domain.icon.impl;

import javax.swing.ImageIcon;

import com.common.swing.domain.icon.IconResources;

/**
 * La clase que contiene los iconos para las acciones de los medios dentro de las ventanas hechos con SWING.
 * 
 * @since 06/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class MediaIcon {

	/**
	 * El path de los recursos de acción.
	 */
	public static final String MEDIA_RESOURCE_PATH = IconResources.RESOURCE_PATH + "media/";

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
		return IconResources.createStaticImage(MediaIcon.MEDIA_RESOURCE_PATH + name, MediaIcon.width, MediaIcon.height);
	}

	/**
	 * El icono para agregar medios.
	 */
	public static final ImageIcon ADD_MEDIA_ICON = MediaIcon.create("add_32.png");
	/**
	 * El icono para quitar medios.
	 */
	public static final ImageIcon REMOVE_MEDIA_ICON = MediaIcon.create("remove_32.png");
	/**
	 * El icono para cerrar un medio.
	 */
	public static final ImageIcon CLOSE_MEDIA_ICON = MediaIcon.create("closed_32.png");
	/**
	 * El icono para chequear medios.
	 */
	public static final ImageIcon CHECK_MEDIA_ICON = MediaIcon.create("check_32.png");
	/**
	 * El icono para retroceder medios.
	 */
	public static final ImageIcon BACKWARD_MEDIA_ICON = MediaIcon.create("backward_32.png");
	/**
	 * El icono para avanzar medios.
	 */
	public static final ImageIcon FORWARD_MEDIA_ICON = MediaIcon.create("forward_32.png");
	/**
	 * El icono de medio previo.
	 */
	public static final ImageIcon PREVIOUS_MEDIA_ICON = MediaIcon.create("previous_32.png");
	/**
	 * El icono de medio siguiente.
	 */
	public static final ImageIcon NEXT_MEDIA_ICON = MediaIcon.create("next_32.png");
	/**
	 * El icono para abrir medios.
	 */
	public static final ImageIcon OPEN_MEDIA_ICON = MediaIcon.create("open_32.png");
	/**
	 * El icono para pausar medios.
	 */
	public static final ImageIcon PAUSE_MEDIA_ICON = MediaIcon.create("pause_32.png");
	/**
	 * El icono para reproducir medios.
	 */
	public static final ImageIcon PLAY_MEDIA_ICON = MediaIcon.create("play_32.png");
	/**
	 * El icono para grabar medios.
	 */
	public static final ImageIcon RECORD_MEDIA_ICON = MediaIcon.create("record_32.png");
	/**
	 * El icono para detener medios.
	 */
	public static final ImageIcon STOP_MEDIA_ICON = MediaIcon.create("stop_32.png");
}