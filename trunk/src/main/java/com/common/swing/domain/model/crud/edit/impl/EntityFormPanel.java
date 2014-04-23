package com.common.swing.domain.model.crud.edit.impl;

import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.common.swing.business.icon.ProgressIcon;
import com.common.swing.domain.model.crud.edit.EntityEditForm;
import com.common.util.domain.exception.CheckedException;
import com.common.util.domain.model.Persistence;

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
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(EntityFormPanel.class);

	/**
	 * La entidad que vamos a manejar dentro de este panel.
	 */
	protected E entity;

	/**
	 * El constructor por omisión.
	 */
	public EntityFormPanel() {
		super();
		Dimension dimension = new Dimension(this.getWidthSize(), this.getHeightSize());
		this.setPreferredSize(dimension);
	}

	@Override
	public EntityEditForm<E, PK> createNewEntityForm() {
		this.entity = this.createNewEntity();

		this.emptyFields();

		return this;
	}

	@Override
	public EntityEditForm<E, PK> createEditEntityForm(E editEntity) {
		this.entity = editEntity;

		this.emptyFields();
		this.fromEntityToFields();

		return this;
	}

	@Override
	public void rejectForm() {
		this.entity = null;

		if (this.isContainerCloseable()) {
			this.getFormContainer().close();
		}
	}

	@Override
	public E getEntity() {
		return this.entity;
	}

	@Override
	public void saveEntity() {
		new Thread() {
			@Override
			public void run() {
				try {
					EntityFormPanel.this.beforeSaveEntity();
					EntityFormPanel.this.fromFieldsToEntity();
					EntityFormPanel.this.getService().saveOrUpdate(EntityFormPanel.this.entity);

					// Cerramos la ventana.
					if (EntityFormPanel.this.isContainerCloseable()) {
						EntityFormPanel.this.getFormContainer().close();
					}
				} catch (CheckedException e) {
					EntityFormPanel.log.error("saveEntity failed", e);
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
		this.setEnabled(false);

		if (this.getProgressLabel() != null) {
			ProgressIcon.PROGRESS_CIRCULAR_BAR_ICON.setImageObserver(this.getProgressLabel());
			this.getProgressLabel().setIcon(ProgressIcon.PROGRESS_CIRCULAR_BAR_ICON);
		}
	}

	/**
	 * La función encargada de ejecutar acciones después de guardar la entidad dentro de la base de datos.
	 */
	protected void afterSaveEntity() {
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