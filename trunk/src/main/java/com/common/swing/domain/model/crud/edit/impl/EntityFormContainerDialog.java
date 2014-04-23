package com.common.swing.domain.model.crud.edit.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.Serializable;

import javax.swing.JDialog;

import com.common.swing.domain.model.crud.edit.EntityEditFormContainer;
import com.common.util.domain.model.Persistence;

/**
 * La clase que nos permite crear una ventana para la edición de un entidad.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que establece las entidades que vamos a editar dentro de esta ventana.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a editar.
 */
public abstract class EntityFormContainerDialog<E extends Persistence<PK>, PK extends Serializable> extends JDialog implements
		EntityEditFormContainer<E, PK> {
	private static final long serialVersionUID = 1L;
//	private static final Logger log = Logger.getLogger(EntityFormContainerDialog.class);

	/**
	 * El constructor por omisión.
	 */
	public EntityFormContainerDialog() {
		super();
		this.setModal(true);
	}

	@Override
	public EntityFormContainerDialog<E, PK> createNewForm() {
		this.setTitle(this.getNewFormTitle());

		this.getContentPane().removeAll();
		this.getContentPane().add((Component) this.getEntityForm().createNewEntityForm(), BorderLayout.CENTER);
		this.pack();

		return this;
	}

	@Override
	public EntityFormContainerDialog<E, PK> createEditForm(E editEntity) {
		this.setTitle(this.getEditFormTitle());

		this.getContentPane().removeAll();
		this.getContentPane().add((Component) this.getEntityForm().createEditEntityForm(editEntity), BorderLayout.CENTER);
		this.pack();

		return this;
	}

	@Override
	public E getEntity() {
		return this.getEntityForm().getEntity();
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