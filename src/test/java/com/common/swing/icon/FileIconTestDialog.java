package com.common.swing.icon;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;

import com.common.swing.domain.icon.impl.FileIcon;

/**
 * La ventana de prueba para los iconos de los archivos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class FileIconTestDialog extends JDialog {

	private static final long serialVersionUID = -6140721450814683282L;

	/**
	 * Constructor de la ventana.
	 */
	public FileIconTestDialog() {
		super();
		this.init();
	}

	/**
	 * La función que inicializa los componentes de la ventana.
	 */
	private void init() {
		this.setTitle("Archivos");
		this.setResizable(false);
		this.setModal(true);
		this.setBounds(100, 100, 375, 129);
		this.getContentPane().setLayout(null);

		JButton pdfButton = new JButton(FileIcon.FILE_PDF_ICON);
		pdfButton.setContentAreaFilled(false);
		pdfButton.setBorderPainted(false);
		pdfButton.setBounds(10, 11, 30, 30);
		this.getContentPane().add(pdfButton);

		JButton xlsButton = new JButton(FileIcon.FILE_XLS_ICON);
		xlsButton.setContentAreaFilled(false);
		xlsButton.setBorderPainted(false);
		xlsButton.setBounds(10, 52, 30, 30);
		this.getContentPane().add(xlsButton);

		JButton button_1 = new JButton((Icon) null);
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setBounds(50, 11, 30, 30);
		this.getContentPane().add(button_1);

		JButton button_2 = new JButton((Icon) null);
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		button_2.setBounds(90, 11, 30, 30);
		this.getContentPane().add(button_2);

		JButton button_3 = new JButton((Icon) null);
		button_3.setContentAreaFilled(false);
		button_3.setBorderPainted(false);
		button_3.setBounds(130, 11, 30, 30);
		this.getContentPane().add(button_3);

		JButton button_4 = new JButton((Icon) null);
		button_4.setContentAreaFilled(false);
		button_4.setBorderPainted(false);
		button_4.setBounds(170, 11, 30, 30);
		this.getContentPane().add(button_4);

		JButton button_5 = new JButton((Icon) null);
		button_5.setContentAreaFilled(false);
		button_5.setBorderPainted(false);
		button_5.setBounds(210, 11, 30, 30);
		this.getContentPane().add(button_5);

		JButton button_6 = new JButton((Icon) null);
		button_6.setContentAreaFilled(false);
		button_6.setBorderPainted(false);
		button_6.setBounds(250, 11, 30, 30);
		this.getContentPane().add(button_6);

		JButton button_7 = new JButton((Icon) null);
		button_7.setContentAreaFilled(false);
		button_7.setBorderPainted(false);
		button_7.setBounds(290, 11, 30, 30);
		this.getContentPane().add(button_7);

		JButton button_8 = new JButton((Icon) null);
		button_8.setContentAreaFilled(false);
		button_8.setBorderPainted(false);
		button_8.setBounds(330, 11, 30, 30);
		this.getContentPane().add(button_8);

		JButton button_10 = new JButton((Icon) null);
		button_10.setContentAreaFilled(false);
		button_10.setBorderPainted(false);
		button_10.setBounds(50, 52, 30, 30);
		this.getContentPane().add(button_10);

		JButton button_11 = new JButton((Icon) null);
		button_11.setContentAreaFilled(false);
		button_11.setBorderPainted(false);
		button_11.setBounds(90, 52, 30, 30);
		this.getContentPane().add(button_11);

		JButton button_12 = new JButton((Icon) null);
		button_12.setContentAreaFilled(false);
		button_12.setBorderPainted(false);
		button_12.setBounds(130, 52, 30, 30);
		this.getContentPane().add(button_12);

		JButton button_13 = new JButton((Icon) null);
		button_13.setContentAreaFilled(false);
		button_13.setBorderPainted(false);
		button_13.setBounds(170, 52, 30, 30);
		this.getContentPane().add(button_13);

		JButton button_14 = new JButton((Icon) null);
		button_14.setContentAreaFilled(false);
		button_14.setBorderPainted(false);
		button_14.setBounds(210, 52, 30, 30);
		this.getContentPane().add(button_14);

		JButton button_15 = new JButton((Icon) null);
		button_15.setContentAreaFilled(false);
		button_15.setBorderPainted(false);
		button_15.setBounds(250, 52, 30, 30);
		this.getContentPane().add(button_15);

		JButton button_16 = new JButton((Icon) null);
		button_16.setContentAreaFilled(false);
		button_16.setBorderPainted(false);
		button_16.setBounds(290, 52, 30, 30);
		this.getContentPane().add(button_16);

		JButton button_17 = new JButton((Icon) null);
		button_17.setContentAreaFilled(false);
		button_17.setBorderPainted(false);
		button_17.setBounds(330, 52, 30, 30);
		this.getContentPane().add(button_17);
	}
}