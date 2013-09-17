package com.common.swing.crud.edit.impl;

import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.common.swing.crud.edit.EntityEditForm;
import com.common.swing.icon.IconResources;
import com.common.util.exception.CheckedException;
import com.common.util.model.Persistence;

/**
 * 
 * La clase que nos permite definir un panel donde vamos a desplegar los atributos de las entidades, ya sea para dar de alta una nueva, modificar
 * entidades del sistema o visualizar sus atributos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            Las clases de las entidades que vamos a manipular dentro de este panel.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a editar.
 */
public abstract class EntityFormPanel<E extends Persistence<PK>, PK extends Serializable> extends JPanel implements EntityEditForm<E, PK> {

	private static final long serialVersionUID = -3862333576607454565L;

	private static final Logger logger = Logger.getLogger(EntityFormPanel.class);

	/**
	 * La entidad que vamos a manejar dentro de este panel.
	 */
	protected E entity;

	/**
	 * El constructor por omisión.
	 */
	public EntityFormPanel() {
		super();
		EntityFormPanel.logger.trace("create");

		Dimension dimension = new Dimension(this.getWidthSize(), this.getHeightSize());
		this.setPreferredSize(dimension);
	}

	@Override
	public EntityEditForm<E, PK> createNewEntityForm() {
		EntityFormPanel.logger.trace("createNewEntityForm");

		this.entity = this.createNewEntity();

		this.emptyFields();

		return this;
	}

	@Override
	public EntityEditForm<E, PK> createEditEntityForm(E editEntity) {
		EntityFormPanel.logger.trace("createEditEntityForm");

		this.entity = editEntity;

		this.emptyFields();
		this.fromEntityToFields();

		return this;
	}

	@Override
	public void rejectForm() {
		EntityFormPanel.logger.trace("rejectForm");

		this.entity = null;

		if (this.isContainerCloseable()) {
			this.getEntityFormContainer().close();
		}
	}

	@Override
	public E getEntity() {
		return this.entity;
	}

	@Override
	public void saveEntity() {
		EntityFormPanel.logger.trace("saveEntity");

		new Thread() {
			@Override
			public void run() {
				try {
					EntityFormPanel.this.beforeSaveEntity();
					EntityFormPanel.this.fromFieldsToEntity();
					EntityFormPanel.this.getEntityService().saveOrUpdate(EntityFormPanel.this.entity);

					// Cerramos la ventana.
					if (EntityFormPanel.this.isContainerCloseable()) {
						EntityFormPanel.this.getEntityFormContainer().close();
					}
				} catch (CheckedException e) {
					EntityFormPanel.logger.error("saveEntity failed", e);
					JOptionPane.showMessageDialog(EntityFormPanel.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					EntityFormPanel.this.afterSaveEntity();
				}
			}
		}.start();
	}

	/**
	 * La función encargada de ejecutar acciones antes de guardar la entidad dentro de la base de datos.
	 */
	protected void beforeSaveEntity() {
		EntityFormPanel.logger.trace("beforeSaveEntity");

		this.setEnabled(false);

		if (this.getProgressLabel() != null) {
			IconResources.PROGRESS_LIST_ICON.setImageObserver(this.getProgressLabel());
			this.getProgressLabel().setIcon(IconResources.PROGRESS_LIST_ICON);
		}
	}

	/**
	 * La función encargada de ejecutar acciones después de guardar la entidad dentro de la base de datos.
	 */
	protected void afterSaveEntity() {
		EntityFormPanel.logger.trace("afterSaveEntity");

		this.setEnabled(true);

		if (this.getProgressLabel() != null) {
			this.getProgressLabel().setIcon(null);
		}
	}

	/**
	 * La función encargada de retornar el label donde vamos a tener un GIF de progreso para mostrar que la ventana se encuentra en actividad.
	 * 
	 * @return El label donde tenemos el GIF de progreso.
	 */
	public abstract JLabel getProgressLabel();
}