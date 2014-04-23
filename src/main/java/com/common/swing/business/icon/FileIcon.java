package com.common.swing.business.icon;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * La clase que va a contener el listado de los iconos que vamos a poder utilizar para los archivos que vamos a usar en los sistemas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class FileIcon {

	/**
	 * Los anchos y alto de las imágenes.
	 */
	private static Integer height = 30;
	private static Integer width = 30;

	/**
	 * El path de los recursos de acción.
	 */
	private static final String FILE_RESOURCE_PATH = IconResources.RESOURCE_PATH + "file/";

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
