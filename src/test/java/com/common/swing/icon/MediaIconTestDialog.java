package com.common.swing.icon;

import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.common.swing.domain.icon.impl.MediaIcon;

/**
 * La ventana de prueba para los iconos de medios.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class MediaIconTestDialog extends JDialog {

	private static final long serialVersionUID = 688137758798868363L;

	/**
	 * Constructor de un cuadro de prueba de botones de medios.
	 */
	public MediaIconTestDialog() {
		super();
		this.init();
	}

	/**
	 * La función de inicialización.
	 */
	private void init() {
		this.setResizable(false);
		this.setModal(true);
		this.setBounds(100, 100, 378, 121);
		this.getContentPane().setLayout(null);

		JButton previousButton = new JButton(MediaIcon.PREVIOUS_MEDIA_ICON);
		previousButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		previousButton.setContentAreaFilled(false);
		previousButton.setBorderPainted(false);
		previousButton.setBounds(10, 11, 30, 30);
		this.getContentPane().add(previousButton);

		JButton backwardButton = new JButton(MediaIcon.BACKWARD_MEDIA_ICON);
		backwardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backwardButton.setBorderPainted(false);
		backwardButton.setContentAreaFilled(false);
		backwardButton.setBounds(50, 11, 30, 30);
		this.getContentPane().add(backwardButton);

		JButton playButton = new JButton(MediaIcon.PLAY_MEDIA_ICON);
		playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		playButton.setBorderPainted(false);
		playButton.setContentAreaFilled(false);
		playButton.setBounds(90, 11, 30, 30);
		this.getContentPane().add(playButton);

		JButton pauseButton = new JButton(MediaIcon.PAUSE_MEDIA_ICON);
		pauseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pauseButton.setBorderPainted(false);
		pauseButton.setContentAreaFilled(false);
		pauseButton.setBounds(130, 11, 30, 30);
		this.getContentPane().add(pauseButton);

		JButton stopButton = new JButton(MediaIcon.STOP_MEDIA_ICON);
		stopButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		stopButton.setBorderPainted(false);
		stopButton.setContentAreaFilled(false);
		stopButton.setBounds(170, 11, 30, 30);
		this.getContentPane().add(stopButton);

		JButton recordButton = new JButton(MediaIcon.RECORD_MEDIA_ICON);
		recordButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		recordButton.setBorderPainted(false);
		recordButton.setContentAreaFilled(false);
		recordButton.setBounds(210, 11, 30, 30);
		this.getContentPane().add(recordButton);

		JButton forwardButton = new JButton(MediaIcon.FORWARD_MEDIA_ICON);
		forwardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forwardButton.setBorderPainted(false);
		forwardButton.setContentAreaFilled(false);
		forwardButton.setBounds(250, 11, 30, 30);
		this.getContentPane().add(forwardButton);

		JButton nextButton = new JButton(MediaIcon.NEXT_MEDIA_ICON);
		nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nextButton.setBorderPainted(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setBounds(290, 11, 30, 30);
		this.getContentPane().add(nextButton);

		JButton closeButton = new JButton(MediaIcon.CLOSE_MEDIA_ICON);
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.setBorderPainted(false);
		closeButton.setContentAreaFilled(false);
		closeButton.setBounds(330, 11, 30, 30);
		this.getContentPane().add(closeButton);

		JButton addButton = new JButton(MediaIcon.ADD_MEDIA_ICON);
		addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addButton.setBorderPainted(false);
		addButton.setContentAreaFilled(false);
		addButton.setBounds(110, 52, 30, 30);
		this.getContentPane().add(addButton);

		JButton removeButton = new JButton(MediaIcon.REMOVE_MEDIA_ICON);
		removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		removeButton.setBorderPainted(false);
		removeButton.setContentAreaFilled(false);
		removeButton.setBounds(150, 52, 30, 30);
		this.getContentPane().add(removeButton);

		JButton checkButton = new JButton(MediaIcon.CHECK_MEDIA_ICON);
		checkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkButton.setBorderPainted(false);
		checkButton.setContentAreaFilled(false);
		checkButton.setBounds(190, 52, 30, 30);
		this.getContentPane().add(checkButton);

		JButton openButton = new JButton(MediaIcon.OPEN_MEDIA_ICON);
		openButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		openButton.setBorderPainted(false);
		openButton.setContentAreaFilled(false);
		openButton.setBounds(230, 52, 30, 30);
		this.getContentPane().add(openButton);
	}
}
