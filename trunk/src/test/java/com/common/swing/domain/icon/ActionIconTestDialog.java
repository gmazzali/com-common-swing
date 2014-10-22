package com.common.swing.domain.icon;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.common.swing.domain.icon.impl.ActionIcon;

/**
 * La ventana de prueba para los iconos de acciones.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ActionIconTestDialog extends JDialog {

	private static final long serialVersionUID = -4673415443454569340L;

	/**
	 * Constructor de la ventana.
	 */
	public ActionIconTestDialog() {
		super();
		this.init();
	}

	/**
	 * La función de inicialización de los componentes de la ventana.
	 */
	private void init() {
		this.setTitle("Acciones");
		this.setResizable(false);
		this.setModal(true);
		this.setBounds(100, 100, 139, 203);
		this.getContentPane().setLayout(null);

		JButton addButton = new JButton(ActionIcon.ADD_ELEMENT_ICON);
		addButton.setContentAreaFilled(false);
		addButton.setBorderPainted(false);
		addButton.setBounds(10, 11, 30, 30);
		this.getContentPane().add(addButton);

		JButton modifyButton = new JButton(ActionIcon.EDIT_ELEMENT_ICON);
		modifyButton.setContentAreaFilled(false);
		modifyButton.setBorderPainted(false);
		modifyButton.setBounds(50, 11, 30, 30);
		this.getContentPane().add(modifyButton);

		JButton deleteButton = new JButton(ActionIcon.REMOVE_ELEMENT_ICON);
		deleteButton.setContentAreaFilled(false);
		deleteButton.setBorderPainted(false);
		deleteButton.setBounds(90, 11, 30, 30);
		this.getContentPane().add(deleteButton);

		JButton selectButton = new JButton(ActionIcon.SELECT_ELEMENT_ICON);
		selectButton.setContentAreaFilled(false);
		selectButton.setBorderPainted(false);
		selectButton.setBounds(50, 52, 30, 30);
		this.getContentPane().add(selectButton);

		JButton lockButton = new JButton(ActionIcon.LOCK_ELEMENT_ICON);
		lockButton.setContentAreaFilled(false);
		lockButton.setBorderPainted(false);
		lockButton.setBounds(10, 52, 30, 30);
		this.getContentPane().add(lockButton);

		JButton unlockButton = new JButton(ActionIcon.UNLOCK_ELEMENT_ICON);
		unlockButton.setContentAreaFilled(false);
		unlockButton.setBorderPainted(false);
		unlockButton.setBounds(90, 52, 30, 30);
		this.getContentPane().add(unlockButton);

		JButton proccessButton = new JButton(ActionIcon.PROCCESS_ELEMENT_ICON);
		proccessButton.setContentAreaFilled(false);
		proccessButton.setBorderPainted(false);
		proccessButton.setBounds(50, 93, 30, 30);
		this.getContentPane().add(proccessButton);

		JButton commitButton = new JButton(ActionIcon.COMMIT_ICON);
		commitButton.setContentAreaFilled(false);
		commitButton.setBorderPainted(false);
		commitButton.setBounds(10, 134, 30, 30);
		this.getContentPane().add(commitButton);

		JButton rejectButton = new JButton(ActionIcon.REJECT_ICON);
		rejectButton.setContentAreaFilled(false);
		rejectButton.setBorderPainted(false);
		rejectButton.setBounds(90, 134, 30, 30);
		this.getContentPane().add(rejectButton);

	}
}
