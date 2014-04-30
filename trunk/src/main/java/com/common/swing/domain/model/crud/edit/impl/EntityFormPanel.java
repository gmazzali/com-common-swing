package com.common.swing.domain.model.crud.edit.impl;

import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.common.swing.domain.model.crud.edit.EntityEditForm;
import com.common.util.business.holder.HolderMessage;
import com.common.util.domain.model.Persistence;

/**
 * 
 * La clase que nos permite definir un panel donde vamos a desplegar los atributos de las entidades, ya sea para dar de alta una nueva, modificar
 * entidades del sistema o visualizar sus atributos.
 * 
 * @since 30/04/2014
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
	public EntityEditForm<E, PK> createNewForm() {
		this.entity = this.createNewEntity();

		this.emptyFields();

		return this;
	}

	@Override
	public EntityEditForm<E, PK> createEditForm(E editEntity) {
		log.info("edit entity=" + editEntity);
		this.entity = editEntity;

		this.emptyFields();
		this.fromEntityToFields();

		return this;
	}

	@Override
	public void reject() {
		this.entity = this.createNewEntity();
		this.emptyFields();

		this.getFormContainer().close();
	}

	@Override
	public E getEntity() {
		return this.entity;
	}

	@Override
	public void confirm() {
		new Thread() {
			@Override
			public void run() {
				try {
					EntityFormPanel.this.beforeSaveEntity();
					EntityFormPanel.this.fromFieldsToEntity();
					EntityFormPanel.this.getService().saveOrUpdate(EntityFormPanel.this.entity);

					// Cerramos la ventana si se puede cerrar.
					EntityFormPanel.this.getFormContainer().close();

				} catch (Exception e) {
					EntityFormPanel.log.error("save entity failed", e);
					JOptionPane.showMessageDialog(EntityFormPanel.this,
							HolderMessage.getMessage(e.getMessage(), "form.edit.save.fail.message"),
							HolderMessage.getMessage("Error", "form.edit.save.fail.message.title"), JOptionPane.ERROR_MESSAGE);
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

		if (this.getProgressLabel() != null && getProgressIcon() != null) {
			this.getProgressIcon().setImageObserver(this.getProgressLabel());
			this.getProgressLabel().setIcon(this.getProgressIcon());
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
	 * Se encarga de retornar la imagen que vamos a desplegar en caso de que se ejecute un proceso en segundo plano.
	 * 
	 * @return El icono de progreso.
	 */
	protected abstract ImageIcon getProgressIcon();

	/**
	 * Se encarga de retornar el label donde vamos a tener un GIF de progreso para mostrar que la ventana se encuentra en actividad.
	 * 
	 * @return El label donde tenemos el GIF de progreso.
	 */
	public abstract JLabel getProgressLabel();
}