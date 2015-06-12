package com.common.swing.domain.icon;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.common.swing.domain.icon.impl.MovementIcon;

/**
 * La ventana de prueba para los iconos de movimientos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class MovementIconTestDialog extends JDialog {

	private static final long serialVersionUID = -4971437318881058035L;

	/**
	 * Constructor de una ventana de iconos.
	 */
	public MovementIconTestDialog() {
		super();
		this.setTitle("Movimientos");
		this.init();
	}

	/**
	 * La función que inicializa los componentes de la ventana.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setBounds(100, 100, 133, 155);
		this.getContentPane().setLayout(null);

		JButton upLeftButton = new JButton(MovementIcon.ARROW_UP_LEFT_ICON);
		upLeftButton.setContentAreaFilled(false);
		upLeftButton.setBorderPainted(false);
		upLeftButton.setBounds(10, 11, 30, 30);
		this.getContentPane().add(upLeftButton);

		JButton upButton = new JButton(MovementIcon.ARROW_UP_ICON);
		upButton.setContentAreaFilled(false);
		upButton.setBorderPainted(false);
		upButton.setBounds(50, 11, 30, 30);
		this.getContentPane().add(upButton);

		JButton upRightButton = new JButton(MovementIcon.ARROW_UP_RIGHT_ICON);
		upRightButton.setContentAreaFilled(false);
		upRightButton.setBorderPainted(false);
		upRightButton.setBounds(90, 11, 30, 30);
		this.getContentPane().add(upRightButton);

		JButton leftButton = new JButton(MovementIcon.ARROW_LEFT_ICON);
		leftButton.setContentAreaFilled(false);
		leftButton.setBorderPainted(false);
		leftButton.setBounds(10, 52, 30, 30);
		this.getContentPane().add(leftButton);

		JButton downLeftButton = new JButton(MovementIcon.ARROW_DOWN_LEFT_ICON);
		downLeftButton.setContentAreaFilled(false);
		downLeftButton.setBorderPainted(false);
		downLeftButton.setBounds(10, 93, 30, 30);
		this.getContentPane().add(downLeftButton);

		JButton downButton = new JButton(MovementIcon.ARROW_DOWN_ICON);
		downButton.setContentAreaFilled(false);
		downButton.setBorderPainted(false);
		downButton.setBounds(50, 93, 30, 30);
		this.getContentPane().add(downButton);

		JButton downRightButton = new JButton(MovementIcon.ARROW_DOWN_RIGHT_ICON);
		downRightButton.setContentAreaFilled(false);
		downRightButton.setBorderPainted(false);
		downRightButton.setBounds(90, 93, 30, 30);
		this.getContentPane().add(downRightButton);

		JButton rightButton = new JButton(MovementIcon.ARROW_RIGHT_ICON);
		rightButton.setContentAreaFilled(false);
		rightButton.setBorderPainted(false);
		rightButton.setBounds(90, 52, 30, 30);
		this.getContentPane().add(rightButton);
	}
}
