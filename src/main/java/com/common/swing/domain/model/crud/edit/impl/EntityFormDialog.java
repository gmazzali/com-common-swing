package com.common.swing.domain.model.crud.edit.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.Serializable;

import javax.swing.JDialog;

import org.apache.log4j.Logger;

import com.common.swing.domain.model.crud.edit.EntityEditContainer;
import com.common.util.domain.model.Persistence;

/**
 * La clase que nos permite crear una ventana para la edición de un entidad.
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
public abstract class EntityFormDialog<E extends Persistence<PK>, PK extends Serializable> extends JDialog implements
		EntityEditContainer<E, PK> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(EntityFormDialog.class);

	/**
	 * El constructor por omisión.
	 */
	public EntityFormDialog() {
		super();
		this.setModal(true);
	}

	@Override
	public EntityFormDialog<E, PK> createNewForm() {
		log.trace("title='" + this.getNewTitle() + "'");
		this.setTitle(this.getNewTitle());

		this.getContentPane().removeAll();
		this.getContentPane().add((Component) this.getEditForm().createNewForm(), BorderLayout.CENTER);
		this.pack();

		return this;
	}

	@Override
	public EntityFormDialog<E, PK> createEditForm(E editEntity) {
		log.trace("title='" + this.getEditTitle() + "'");
		this.setTitle(this.getEditTitle());

		this.getContentPane().removeAll();
		this.getContentPane().add((Component) this.getEditForm().createEditForm(editEntity), BorderLayout.CENTER);
		this.pack();

		return this;
	}

	@Override
	public E getEntity() {
		return this.getEditForm().getEntity();
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