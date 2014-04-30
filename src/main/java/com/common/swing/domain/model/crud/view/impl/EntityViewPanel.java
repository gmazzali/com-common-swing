package com.common.swing.domain.model.crud.view.impl;

import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.common.swing.domain.model.crud.view.EntityViewForm;
import com.common.util.domain.model.Persistence;

/**
 * El panel que implementa la interfaz que nos permite definir un formulario para la visualización de un elemento.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que establece las entidades que vamos a visualizar dentro de este formulario.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a visualizar.
 */
public abstract class EntityViewPanel<E extends Persistence<PK>, PK extends Serializable> extends JPanel implements EntityViewForm<E, PK> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(EntityViewPanel.class);

	/**
	 * La entidad que vamos a manejar dentro de este panel.
	 */
	protected E entity;

	/**
	 * El constructor por omisión.
	 */
	public EntityViewPanel() {
		super();
		Dimension dimension = new Dimension(this.getWidthSize(), this.getHeightSize());
		this.setPreferredSize(dimension);
	}

	@Override
	public EntityViewForm<E, PK> createViewForm(E entity) {
		log.info("view entity=" + entity);
		this.entity = entity;

		this.emptyFields();
		this.fromEntityToFields();

		return this;
	}
}