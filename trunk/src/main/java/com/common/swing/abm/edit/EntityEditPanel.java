package com.common.swing.abm.edit;

import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import com.common.swing.domain.model.GenericPanel;
import com.common.util.exception.CheckedException;
import com.common.util.model.Persistence;
import com.common.util.service.GenericService;

/**
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
public abstract class EntityEditPanel<E extends Persistence<PK>, PK extends Serializable> extends GenericPanel {

	private static final long serialVersionUID = 1856825573432952068L;
	
	/**
	 * El logger de la ventana.
	 */
	private static final Logger log = Logger.getLogger(EntityEditPanel.class);

	/**
	 * La ventana contenedora de este panel para poder controlar la ventana que contiene este panel.
	 */
	protected EntityEditDialog<E, PK> entityEditDialog;

	/**
	 * El objeto que vamos a manipular.
	 */
	protected E entity;
	/**
	 * El servicio que nos permite manipular la entidad.
	 */
	protected GenericService<E, PK> entityService;

	/**
	 * El constructor de un panel de edición.
	 */
	public EntityEditPanel() {
		super();
		EntityEditPanel.log.trace("EntityEditPanel create");

		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 2));

		this.setPreferredSize(this.getDimension());
	}

	/**
	 * La función encargada de almacenar dentro de la base de datos la entidad y luego cerrar la ventana.
	 */
	protected void saveEntity() {
		EntityEditPanel.log.trace("EntityEditPanel saveEntity");

		try {
			// Lo primero que hacemos es setear la entidad desde los valores de la ventana.
			this.loadEntityFromFields();

			try {
				// Validamos el contenido del elemento antes de almacenarlo.
				this.entityService.validate(this.entity);

				// Una vez almacenado los datos dentro de la entidad, creamos el proceso de guardado.
				Thread thread = new Thread() {
					@Override
					public void run() {
						try {
							// Ejecutamos algo antes de guardar las entidades.
							EntityEditPanel.this.beforeSaveAction();

							// Almacenamos el elemento dentro de la base de datos si es nuevo o lo modificamos si ya esta almacenado.
							EntityEditPanel.this.entityService.saveOrUpdate(EntityEditPanel.this.entity);

							// Cerramos la ventana.
							if (EntityEditPanel.this.entityEditDialog != null) {
								EntityEditPanel.this.entityEditDialog.confirm();
							} else {
								EntityEditPanel.this.setVisible(false);
							}

						} catch (Exception e) {
							EntityEditPanel.log.error("EntityEditPanel saveEntity failed");
							JOptionPane.showMessageDialog(null, e.getMessage(), "Falla", JOptionPane.ERROR_MESSAGE);
						} finally {
							EntityEditPanel.this.afterSaveAction();
						}
					}
				};
				thread.start();
			} catch (CheckedException e) {
				EntityEditPanel.log.error("EntityEditPanel validate failed");
				JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		} catch (CheckedException e) {
			EntityEditPanel.log.error("loadEntityFromFields validate failed");
			JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * La función que cancela la edición o creación de la entidad que tenemos dentro de esta ventana y luego cerramos la ventana.
	 */
	protected void dontSaveEntity() {
		EntityEditPanel.log.trace("EntityEditPanel dontSaveEntity");

		// Cerramos la ventana.
		if (this.entityEditDialog != null) {
			this.entityEditDialog.cancel();
		} else {
			this.setVisible(false);
		}
	}

	/**
	 * La función encargada de cargar los campos de la ventana de acuerdo a los valores de los atributos de la entidad que queremos editar.
	 */
	protected abstract void loadFieldsFromEntity();

	/**
	 * La función encargada de tomar los valores de los campos de la ventana y cargarlos dentro de la entidad antes de guardarlo dentro de la base de
	 * datos.
	 * 
	 * @throws CheckedException
	 *             En caso de que la carga de datos desde la ventana no sea válida.
	 */
	protected abstract void loadEntityFromFields() throws CheckedException;

	/**
	 * La función encargada de vaciar los campos de la ventana de edición de una entidad para poder cargar una nueva desde cero.
	 */
	protected abstract void emptyFields();

	/**
	 * La función encargada de retornar el tamaño del panel para poder empacarlo dentro de la ventana contenedora.
	 * 
	 * @return La dimensión que pretende establecerse al panel.
	 */
	protected abstract Dimension getDimension();

	/**
	 * La función encargada de retornar el título de la ventana para la creación de una nueva entidad.
	 * 
	 * @return El título de la ventana para crear una nueva entidad.
	 */
	protected abstract String getNewTitle();

	/**
	 * La función encargada de retornar el título de la ventana para la edición de una nueva entidad.
	 * 
	 * @return El título de la ventana para editar una entidad.
	 */
	protected abstract String getEditTitle();

	/**
	 * La función que va a ejecutar un conjunto de acciones antes de comenzar el guardado de la entidad dentro de la base de datos.
	 */
	protected abstract void beforeSaveAction();

	/**
	 * La función que va a ejecutar un conjunto de acciones despues del guardado de la entidad dentro de la base de datos.
	 */
	protected abstract void afterSaveAction();

	/**
	 * Función encargada de setear la entidad que estamos por editar dentro de la ventana.
	 * 
	 * @param entity
	 *            La entidad que vamos a editar dentro de este panel.
	 */
	public void setEntity(E entity) {
		if (entity != null) {
			this.entity = entity;
		}
	}

	/**
	 * Funcíon que permite cargar el servicio de la entidad que queremos editar o crear.
	 * 
	 * @param entityService
	 *            El servicio para la entidad que queremos crear o editar.
	 */
	public void setEntityService(GenericService<E, PK> entityService) {
		if (entityService != null) {
			this.entityService = entityService;
		}
	}

	/**
	 * La función encargada de retornar la entidad que vamos a editar dentro de este panel.
	 * 
	 * @return La entidad que vamos a editar dentro de este panel.
	 */
	public E getEntity() {
		return this.entity;
	}

	/**
	 * Funcíon que permite retornar el servicio de la entidad que queremos editar o crear.
	 * 
	 * @return El servicio que vamos a ocupar para las entidades que tenemos para editar.
	 */
	public GenericService<E, PK> getEntityService() {
		return this.entityService;
	}

	/**
	 * La funhción encargada de setear el contenedor de esta ventana para poder controlar el mismo desde este panel.
	 * 
	 * @param entityEditDialog
	 *            El contenedor de este panel para su control.
	 */
	protected void setContainerDialog(EntityEditDialog<E, PK> entityEditDialog) {
		if (entityEditDialog != null) {
			this.entityEditDialog = entityEditDialog;
		}
	}
}
