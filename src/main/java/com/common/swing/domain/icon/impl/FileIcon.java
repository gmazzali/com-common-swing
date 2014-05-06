package com.common.swing.domain.icon.impl;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.common.swing.domain.icon.IconResources;

/**
 * La clase que va a contener el listado de los iconos que vamos a poder utilizar para los archivos que vamos a usar en los sistemas.
 * 
 * @since 06/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class FileIcon {

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
	 * El icono de los archivos PDF.
	 */
	public static ImageIcon FILE_PDF_ICON;
	/**
	 * El icono de los archivos XLS.
	 */
	public static ImageIcon FILE_XLS_ICON;

	static {
		FileIcon.FILE_PDF_ICON = new ImageIcon(new ImageIcon(FileIcon.class.getClassLoader().getResource(FileIcon.FILE_RESOURCE_PATH + "pdf_32.png"))
				.getImage().getScaledInstance(FileIcon.width, FileIcon.height, Image.SCALE_SMOOTH));

		FileIcon.FILE_XLS_ICON = new ImageIcon(new ImageIcon(FileIcon.class.getClassLoader().getResource(FileIcon.FILE_RESOURCE_PATH + "xls_32.png"))
				.getImage().getScaledInstance(FileIcon.width, FileIcon.height, Image.SCALE_SMOOTH));
	}
}
