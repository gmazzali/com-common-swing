package com.common.swing.crud.list.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.Serializable;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.common.swing.crud.list.EntityListFormContainer;
import com.common.util.model.Persistence;

/**
 * La clase que nos permite definir un dialogo para mostrar un listado de las entidades que vamos a tener dentro del sistema para poder controlarlas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de las entidades que vamos a manipular.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a manipular.
 */
public abstract class EntityListFormContainerDialog<E extends Persistence<PK>, PK extends Serializable> extends JDialog implements
		EntityListFormContainer<E, PK> {

	private static final long serialVersionUID = 8662949359810446241L;

	/**
	 * El logger de la ventana.
	 */
	private static final Logger log = Logger.getLogger(EntityListFormContainerDialog.class);

	/**
	 * El constructor de un dialogo con un panel en blanco.
	 */
	public EntityListFormContainerDialog() {
		super();
		EntityListFormContainerDialog.log.trace("create");

		this.init();
	}

	/**
	 * La función encargada de inicializar los elementos de la ventana.
	 */
	private void init() {
		EntityListFormContainerDialog.log.trace("init");

		this.setTitle("EntityListDialog");
		this.setModal(true);
		this.setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(400, 100));
		this.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel label = new JLabel("EMPTY PANEL");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 37));
		label.setBounds(10, 11, 380, 78);
		panel.add(label);
	}

	/**
	 * La función encargada de actualizar el formulario de listado de entidades.
	 */
	private void updatePanel() {
		if (this.getEntityListForm() == null) {
			this.init();
		} else {
			this.getContentPane().removeAll();
			this.getContentPane().add((Component) this.getEntityListForm(), BorderLayout.CENTER);
		}

		this.setTitle(this.getListContainerTitle());
		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.pack();
	}

	@Override
	public void setEnabled(boolean b) {
		this.getEntityListForm().setEnabled(false);
	}

	@Override
	public void open() {
		this.updatePanel();
		this.updateEntityListForm();
		this.setVisible(true);
	}

	@Override
	public void close() {
		this.dispose();
	}
}
