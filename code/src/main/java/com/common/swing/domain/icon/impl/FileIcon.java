package com.common.swing.domain.icon.impl;

import javax.swing.ImageIcon;

import com.common.swing.domain.icon.IconResources;

/**
 * La clase que va a contener el listado de los iconos que vamos a poder utilizar para los archivos que vamos a usar en los sistemas.
 * 
 * @since 06/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class FileIcon extends IconResources {
	private static final long serialVersionUID = 1L;

	/**
	 * El path de los recursos de acción.
	 */
	public static final String FILE_RESOURCE_PATH = IconResources.RESOURCE_PATH + "file/";

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
		return IconResources.createStaticImage(FileIcon.FILE_RESOURCE_PATH + name, FileIcon.width, FileIcon.height);
	}

	/**
	 * El icono de los archivos PDF.
	 */
	public static final ImageIcon FILE_PDF_ICON = FileIcon.create("pdf_32.png");
	/**
	 * El icono de los archivos XLS.
	 */
	public static final ImageIcon FILE_XLS_ICON = MediaIcon.create("xls_32.png");
}