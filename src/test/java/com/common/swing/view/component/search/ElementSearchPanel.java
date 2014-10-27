package com.common.swing.view.component.search;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.domain.model.Element;
import com.common.swing.view.component.panel.BaseSearchPanel;

/**
 * El panel de búsqueda de los elementos.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementSearchPanel extends BaseSearchPanel<Element> {
	private static final long serialVersionUID = 1L;

	private JTextField textField;

	public ElementSearchPanel() {
		this.init();
	}

	@Override
	protected void afterInit() {
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
				textField.selectAll();
			}
		});
		this.add(this.textField);
	}

	@Override
	public void emptyFields() {
		this.textField.setText("");
	}

	@Override
	public Integer getHeightSize() {
		return 100;
	}

	@Override
	public Integer getWidthSize() {
		return 350;
	}

	@Override
	public void enabled() {
		this.textField.setEnabled(true);
	}

	@Override
	public void disabled() {
		this.textField.setEnabled(false);
	}

	@Override
	public Element getFilter() throws SwingException {
		Element bean = new Element();
		bean.setName("nombre");
		return bean;
	}
}