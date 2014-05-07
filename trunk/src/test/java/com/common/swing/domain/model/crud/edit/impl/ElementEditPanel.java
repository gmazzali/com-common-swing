package com.common.swing.domain.model.crud.edit.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.common.swing.domain.model.crud.model.Element;
import com.common.swing.domain.model.crud.model.ElementServiceImpl;
import com.common.swing.view.crud.form.impl.EditFormImpl;
import com.common.util.business.service.BaseService;
import com.common.util.domain.exception.CheckedException;

/**
 * La clase que extiende el formulario de edición de entidades.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementEditPanel extends EditFormImpl<Element, Integer> {
	private static final long serialVersionUID = 1L;

	private ElementServiceImpl service;

	private JTextField textField;
	private JLabel progressLabel;
	private JButton botonCancelar;
	private JButton botonAceptar;

	/**
	 * Create the panel.
	 */
	public ElementEditPanel() {
		super();
		this.init();
	}

	/**
	 * La función de inicialización de componentes.
	 */
	private void init() {
		this.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		this.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 15, 48, 14);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 11));
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lblNombre);

		this.textField = new JTextField();
		this.textField.setBounds(68, 11, 222, 22);
		this.textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				ElementEditPanel.this.textField.selectAll();
			}
		});
		this.add(this.textField);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 44, 280, 2);
		this.add(separator);

		this.botonCancelar = new JButton("Cancelar");
		this.botonCancelar.setBounds(10, 57, 89, 32);
		this.botonCancelar.setFont(new Font("Arial", Font.BOLD, 12));
		this.botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ElementEditPanel.this.reject();
			}
		});
		this.add(this.botonCancelar);

		this.progressLabel = new JLabel("");
		this.progressLabel.setBounds(109, 57, 82, 32);
		this.progressLabel.setVisible(true);
		this.progressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(this.progressLabel);

		this.botonAceptar = new JButton("Aceptar");
		this.botonAceptar.setBounds(201, 57, 89, 32);
		this.botonAceptar.setFont(new Font("Arial", Font.BOLD, 12));
		this.botonAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ElementEditPanel.this.confirm();
			}
		});
		this.add(this.botonAceptar);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);

		this.textField.setEnabled(enabled);

		this.botonAceptar.setEnabled(enabled);
		this.botonCancelar.setEnabled(enabled);
	}

	@Override
	public BaseService<Element, Integer> getService() {
		return this.service;
	}

	public void setService(ElementServiceImpl service) {
		this.service = service;
	}

	@Override
	public void emptyFields() {
		System.out.println("EMPTY FIELD");
		this.textField.setText("");
	}

	@Override
	public void fromEntityToFields() {
		System.out.println("LOAD ENTITY TO PANEL");
		this.textField.setText(this.entity.getName());
	}

	@Override
	public void fromFieldsToEntity() throws CheckedException {
		System.out.println("LOAD ENTITY FROM PANEL");
		this.entity.setName(this.textField.getText());
	}

	@Override
	public JLabel getProgressLabel() {
		return this.progressLabel;
	}

	@Override
	public Integer getWidthSize() {
		return 300;
	}

	@Override
	public Integer getHeightSize() {
		return 100;
	}

	@Override
	protected ImageIcon getProgressIcon() {
		return null;
	}

	@Override
	public String getNewTitle() {
		return "Nuevo elemento";
	}

	@Override
	public String getEditTitle() {
		return "Editar elemento";
	}

	@Override
	public String getViewTitle() {
		return "Visualizar elemento";
	}

	@Override
	public void enabled() {
		this.setEnabled(true);
	}

	@Override
	public void disabled() {
		this.setEnabled(false);
	}
}