package com.common.swing.domain;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JWindow;

/**
 * La clase que va a contener un listado de funcionalidades genéricas para los elementos creados a partir de SWING.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class SwingUtils {

	/**
	 * La función encargada de centrar la ventana en la pantalla.
	 * 
	 * @param window
	 *            La ventana que vamos a centrar.
	 */
	public static void center(JWindow window) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle r = window.getBounds();
		window.setLocation((screen.width - r.width) / 2, (screen.height - r.height) / 2);
	}

	/**
	 * La función encargada de crear una imagen a partir de una URL.
	 * 
	 * @param path
	 *            La URL de la imagen.
	 * @return La imagen que corresponde con la URL que se recibio, en caso de que no se encuentre, se retornar NULL.
	 */
	public static Image loadImage(URL path) {
		if (path == null) {
			return null;
		}
		return Toolkit.getDefaultToolkit().createImage(path);
	}
}
