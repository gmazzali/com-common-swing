package com.common.swing.icon;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * La clase que va a contener todos los recursos gráficos que vamos a ocupar dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class IconResources {

	/**
	 * El path de los recursos.
	 */
	protected static final String RESOURCE_PATH = "com/common/swing/image/";

	/**
	 * La función encargada de crear los iconos de acuerdo al path recibidos y al tamaño requerido de redimensión.
	 * 
	 * @param path
	 *            El path donde se encuentra la imagen que vamos a utilizar para crear el icono.
	 * @param width
	 *            El ancho con el que vamos a redimensionar el icono a crear.
	 * @param height
	 *            El alto con el que vamos a redimensionar el icono a crear.
	 * @return El icono que creamos de acuerdo a los parámetros recibidos.
	 */
	public static ImageIcon createStaticImage(String path, Integer width, Integer height) {
		return new ImageIcon(new ImageIcon(IconResources.class.getClassLoader().getResource(path)).getImage().getScaledInstance(width, height,
				Image.SCALE_SMOOTH));
	}

	/**
	 * La función encargada de crear los iconos animado de acuerdo al path recibidos.
	 * 
	 * @param path
	 *            El path donde se encuentra la imagen en formato GIF que vamos a utilizar para crear el icono.
	 * @return El icono animado que creamos de acuerdo a los parámetros recibidos.
	 */
	public static ImageIcon createGifImage(String path) {
		return new ImageIcon(IconResources.class.getClassLoader().getResource(path));
	}
}
