package com.commons.swing.abm.edit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.common.swing.abm.edit.EntityEditPanel;
import com.common.swing.util.SwingResources;
import com.common.util.service.GenericService;
import com.commons.swing.abm.model.Element;
import com.commons.swing.abm.model.ElementServiceImpl;

/**
 * La clase que maneja el panel de pruebas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ElementEditPanel extends EntityEditPanel<Element, Integer> {

	private ElementServiceImpl service;

	private JTextField textField;

	protected JLabel progressLabel;
	private JButton botonCancelar;
	private JButton botonAceptar;

	/**
	 * Create the panel.
	 */
	public ElementEditPanel() {
		this.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		this.setPreferredSize(new Dimension(300, 100));
		this.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Arial", Font.BOLD, 11));
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 15, 48, 14);
		this.add(lblNombre);

		this.textField = new JTextField();
		this.textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				ElementEditPanel.this.textField.selectAll();
			}
		});
		this.textField.setBounds(68, 11, 222, 22);
		this.add(this.textField);

		this.botonCancelar = new JButton("Cancelar");
		this.botonCancelar.setFont(new Font("Arial", Font.BOLD, 12));
		this.botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ElementEditPanel.this.dontSaveEntity();
			}
		});
		this.botonCancelar.setBounds(10, 57, 89, 32);
		this.add(this.botonCancelar);

		this.botonAceptar = new JButton("Aceptar");
		this.botonAceptar.setFont(new Font("Arial", Font.BOLD, 12));
		this.botonAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ElementEditPanel.this.saveEntity();
			}
		});
		this.botonAceptar.setBounds(201, 57, 89, 32);
		this.add(this.botonAceptar);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 44, 280, 2);
		this.add(separator);

		this.progressLabel = new JLabel("");
		this.progressLabel.setVisible(true);
		this.progressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.progressLabel.setBounds(109, 57, 82, 32);
		this.add(this.progressLabel);
	}

	/**
	 * @see com.common.swing.abm.edit.EntityEditPanel#getDimension()
	 */
	@Override
	protected Dimension getDimension() {
		return new Dimension(300, 100);
	}

	/**
	 * @see com.common.swing.abm.edit.EntityEditPanel#getNewTitle()
	 */
	@Override
	protected String getNewTitle() {
		return "Nuevo elemento";
	}

	/**
	 * @see com.common.swing.abm.edit.EntityEditPanel#getEditTitle()
	 */
	@Override
	protected String getEditTitle() {
		return "Editar elemento";
	}

	/**
	 * @see com.common.swing.abm.edit.EntityEditPanel#beforeSaveAction()
	 */
	@Override
	protected void beforeSaveAction() {
		SwingResources.PROGRESS_LIST_ICON.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(SwingResources.PROGRESS_LIST_ICON);

		this.botonAceptar.setEnabled(false);
		this.botonCancelar.setEnabled(false);
	}

	/**
	 * @see com.common.swing.abm.edit.EntityEditPanel#afterSaveAction()
	 */
	@Override
	protected void afterSaveAction() {
		this.progressLabel.setIcon(null);

		this.botonAceptar.setEnabled(true);
		this.botonCancelar.setEnabled(true);
	}

	/**
	 * @see com.common.swing.abm.edit.EntityEditPanel#loadFieldsFromEntity()
	 */
	@Override
	protected void loadFieldsFromEntity() {
		System.out.println("LOAD ENTITY TO PANEL");

		this.textField.setText(this.entity.getName());
	}

	/**
	 * @see com.common.swing.abm.edit.EntityEditPanel#loadEntityFromFields()
	 */
	@Override
	protected void loadEntityFromFields() {
		System.out.println("LOAD ENTITY FROM PANEL");

		this.entity.setName(this.textField.getText());
	}

	/**
	 * @see com.common.swing.abm.edit.EntityEditPanel#emptyFields()
	 */
	@Override
	protected void emptyFields() {
		System.out.println("EMPTY FIELD");

		this.textField.setText("");
	}

	/**
	 * @see com.common.swing.abm.edit.EntityEditPanel#getEntityService()
	 */
	@Override
	public GenericService<Element, Integer> getEntityService() {
		return this.service;
	}
}
