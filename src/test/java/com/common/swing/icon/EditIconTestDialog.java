package com.common.swing.icon;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.common.swing.domain.icon.impl.EditIcon;

/**
 * La ventana de prueba para los iconos para editar elementos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class EditIconTestDialog extends JDialog {

	private static final long serialVersionUID = -4886796347084867120L;

	/**
	 * Constructor de una ventana.
	 */
	public EditIconTestDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar los componentes de la ventana.
	 */
	private void init() {
		this.setModal(true);
		this.setTitle("Edici\u00F3n");
		this.setResizable(false);
		this.setBounds(100, 100, 141, 161);
		this.getContentPane().setLayout(null);

		JButton copyButton = new JButton(EditIcon.COPY_ELEMENT_ICON);
		copyButton.setContentAreaFilled(false);
		copyButton.setBorderPainted(false);
		copyButton.setBounds(10, 11, 30, 30);
		this.getContentPane().add(copyButton);

		JButton cutButton = new JButton(EditIcon.CUT_ELEMENT_ICON);
		cutButton.setContentAreaFilled(false);
		cutButton.setBorderPainted(false);
		cutButton.setBounds(50, 11, 30, 30);
		this.getContentPane().add(cutButton);

		JButton pasteButton = new JButton(EditIcon.PASTE_ELEMENT_ICON);
		pasteButton.setContentAreaFilled(false);
		pasteButton.setBorderPainted(false);
		pasteButton.setBounds(90, 11, 30, 30);
		this.getContentPane().add(pasteButton);

		JButton refreshButton = new JButton(EditIcon.REFRESH_ELEMENT_ICON);
		refreshButton.setContentAreaFilled(false);
		refreshButton.setBorderPainted(false);
		refreshButton.setBounds(10, 52, 30, 30);
		this.getContentPane().add(refreshButton);

		JButton searchButton = new JButton(EditIcon.SEARCH_ELEMENT_ICON);
		searchButton.setContentAreaFilled(false);
		searchButton.setBorderPainted(false);
		searchButton.setBounds(90, 52, 30, 30);
		this.getContentPane().add(searchButton);

		JButton undoButton = new JButton(EditIcon.UNDO_ICON);
		undoButton.setContentAreaFilled(false);
		undoButton.setBorderPainted(false);
		undoButton.setBounds(10, 93, 30, 30);
		this.getContentPane().add(undoButton);

		JButton redoButton = new JButton(EditIcon.REDO_ICON);
		redoButton.setContentAreaFilled(false);
		redoButton.setBorderPainted(false);
		redoButton.setBounds(90, 93, 30, 30);
		this.getContentPane().add(redoButton);
	}
}