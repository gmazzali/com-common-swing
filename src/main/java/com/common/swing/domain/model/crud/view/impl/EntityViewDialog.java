package com.common.swing.domain.model.crud.view.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.Serializable;

import javax.swing.JDialog;

import org.apache.log4j.Logger;

import com.common.swing.domain.model.crud.edit.impl.EntityFormDialog;
import com.common.swing.domain.model.crud.view.EntityViewContainer;
import com.common.util.domain.model.Persistence;

/**
 * El dialogo que implementa interfaz que define los métodos para el contenedor de un formulario de edición de entidades.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que establece las entidades que vamos a editar dentro de esta ventana.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a editar.
 */
public abstract class EntityViewDialog<E extends Persistence<PK>, PK extends Serializable> extends JDialog implements EntityViewContainer<E, PK> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(EntityFormDialog.class);

	/**
	 * El constructor por omisión.
	 */
	public EntityViewDialog() {
		super();
		this.setModal(true);
	}

	@Override
	public EntityViewContainer<E, PK> createViewForm(E entity) {
		log.trace("title='" + this.getViewTitle() + "'");
		this.setTitle(this.getViewTitle());

		this.getContentPane().removeAll();
		this.getContentPane().add((Component) this.getViewForm().createViewForm(entity), BorderLayout.CENTER);
		this.pack();

		return this;
	}

	@Override
	public void open() {
		this.setVisible(true);
	}

	@Override
	public void close() {
		this.dispose();
	}
}