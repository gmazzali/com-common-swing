package com.common.swing.crud.form;

import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

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
public abstract class GenericFormPanel<E extends Persistence<PK>, PK extends Serializable> extends JPanel {

	private static final long serialVersionUID = 1856825573432952068L;

	/**
	 * El logger de la ventana.
	 */
	private static final Logger log = Logger.getLogger(GenericFormPanel.class);

	/**
	 * La ventana que va a contener de este panel para poder controlar la ventana que contiene este panel.
	 */
	protected GenericFormDialog<E, PK> formDialog;

	/**
	 * El objeto que vamos a manipular.
	 */
	protected E entity;

	/**
	 * El constructor de un panel de edici�n.
	 */
	public GenericFormPanel() {
		super();
		GenericFormPanel.log.trace("create");

		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 2));

		this.setPreferredSize(this.getDimension());
	}

	/**
	 * La funci�n encargada de almacenar dentro de la base de datos la entidad y luego cerrar la ventana.
	 */
	public void commit() {
		GenericFormPanel.log.trace("saveEntity");

		try {
			// Lo primero que hacemos es setear la entidad desde los valores de la ventana.
			this.loadEntityFromFields();

			try {
				// Validamos el contenido del elemento antes de almacenarlo.
				this.getEntityService().validate(this.entity);

				// Una vez almacenado los datos dentro de la entidad, creamos el proceso de guardado.
				Thread thread = new Thread() {
					@Override
					public void run() {
						try {
							// Ejecutamos algo antes de guardar las entidades.
							GenericFormPanel.this.beforeSaveAction();

							// Almacenamos el elemento dentro de la base de datos si es nuevo o lo modificamos si ya esta almacenado.
							GenericFormPanel.this.getEntityService().saveOrUpdate(GenericFormPanel.this.entity);

							// Cerramos la ventana.
							if (GenericFormPanel.this.formDialog != null) {
								GenericFormPanel.this.formDialog.commit();
							} else {
								GenericFormPanel.this.setVisible(false);
							}

						} catch (Exception e) {
							GenericFormPanel.log.error("saveEntity failed");
							JOptionPane.showMessageDialog(null, e.getMessage(), "Falla", JOptionPane.ERROR_MESSAGE);
						} finally {
							GenericFormPanel.this.afterSaveAction();
						}
					}
				};
				thread.start();
			} catch (CheckedException e) {
				GenericFormPanel.log.error("validate failed");
				JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		} catch (CheckedException e) {
			GenericFormPanel.log.error("loadEntityFromFields validate failed");
			JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * La funci�n que cancela la edici�n o creaci�n de la entidad que tenemos dentro de esta ventana y luego cerramos la ventana.
	 */
	public void reject() {
		GenericFormPanel.log.trace("dontSaveEntity");

		// Cerramos la ventana.
		if (this.formDialog != null) {
			this.formDialog.reject();
		} else {
			this.setVisible(false);
		}
	}

	/**
	 * Funci�n que permite retornar el servicio de la entidad que queremos editar o crear.
	 * 
	 * @return El servicio que vamos a ocupar para las entidades que tenemos para editar.
	 */
	public abstract GenericService<E, PK> getEntityService();

	/**
	 * La funci�n encargada de cargar los campos de la ventana de acuerdo a los valores de los atributos de la entidad que queremos editar.
	 */
	protected abstract void loadFieldsFromEntity();

	/**
	 * La funci�n encargada de tomar los valores de los campos de la ventana y cargarlos dentro de la entidad antes de guardarlo dentro de la base de
	 * datos.
	 * 
	 * @throws CheckedException
	 *             En caso de que la carga de datos desde la ventana no sea v�lida.
	 */
	protected abstract void loadEntityFromFields() throws CheckedException;

	/**
	 * La funci�n encargada de vaciar los campos de la ventana de edici�n de una entidad para poder cargar una nueva desde cero.
	 */
	protected abstract void emptyFields();

	/**
	 * La funci�n encargada de retornar el tama�o del panel para poder empacarlo dentro de la ventana contenedora.
	 * 
	 * @return La dimensi�n que pretende establecerse al panel.
	 */
	protected abstract Dimension getDimension();

	/**
	 * La funci�n encargada de retornar el t�tulo de la ventana para la creaci�n de una nueva entidad.
	 * 
	 * @return El t�tulo de la ventana para crear una nueva entidad.
	 */
	protected abstract String getNewTitle();

	/**
	 * La funci�n encargada de retornar el t�tulo de la ventana para la edici�n de una nueva entidad.
	 * 
	 * @return El t�tulo de la ventana para editar una entidad.
	 */
	protected abstract String getEditTitle();

	/**
	 * La funci�n que va a ejecutar un conjunto de acciones antes de comenzar el guardado de la entidad dentro de la base de datos.
	 */
	protected abstract void beforeSaveAction();

	/**
	 * La funci�n que va a ejecutar un conjunto de acciones despu�s del guardado de la entidad dentro de la base de datos.
	 */
	protected abstract void afterSaveAction();

	/**
	 * Funci�n encargada de cargar la entidad que estamos por editar dentro de la ventana.
	 * 
	 * @param entity
	 *            La entidad que vamos a editar dentro de este panel.
	 */
	public void setEntity(E entity) {
		this.entity = entity;
	}

	/**
	 * La funci�n encargada de retornar la entidad que vamos a editar dentro de este panel.
	 * 
	 * @return La entidad que vamos a editar dentro de este panel.
	 */
	public E getEntity() {
		return this.entity;
	}

	/**
	 * La funci�n encargada de cargar el contenedor de esta ventana para poder controlar el mismo desde este panel.
	 * 
	 * @param formDialog
	 *            El contenedor de este panel para su control.
	 */
	protected void setFormDialog(GenericFormDialog<E, PK> formDialog) {
		if (formDialog != null) {
			this.formDialog = formDialog;
		}
	}
}