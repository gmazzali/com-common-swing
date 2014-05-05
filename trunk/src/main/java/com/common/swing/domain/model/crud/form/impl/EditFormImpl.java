package com.common.swing.domain.model.crud.form.impl;

import java.awt.Dimension;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.domain.model.crud.container.BaseContainer;
import com.common.swing.domain.model.crud.form.EditForm;
import com.common.swing.domain.model.crud.util.CallbackForm;
import com.common.swing.domain.model.crud.util.FormTypeEnum;
import com.common.util.business.holder.HolderMessage;
import com.common.util.domain.model.Persistence;

/**
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
@SuppressWarnings("unchecked")
public abstract class EditFormImpl<E extends Persistence<PK>, PK extends Serializable> extends JPanel implements EditForm<E, PK> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(EditFormImpl.class);

	/**
	 * La clase de la entidad que vamos a manejar dentro de este formulario.
	 */
	private final Class<E> entityClass;
	/**
	 * La entidad que vamos a manejar dentro de este panel.
	 */
	protected E entity;
	/**
	 * Las acciones despues de procesar el aceptar o el cancelar de los botones.
	 */
	protected CallbackForm callback;

	/**
	 * El constructor por omisión.
	 */
	public EditFormImpl() {
		super();

		try {
			this.entityClass = (Class<E>) ((ParameterizedType) super.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception ex) {
			log.error("The generic parameter of this class doesn't must be empty", ex);
			// TODO agregar la clave del mensaje.
			throw new SwingException("The generic parameter of this class doesn't must be empty", "");
		}

		log.trace("load dimension=[" + this.getWidthSize() + "; " + this.getHeightSize() + "]");
		this.setPreferredSize(new Dimension(this.getWidthSize(), this.getHeightSize()));
	}

	@Override
	public E getEntity() {
		return this.entity;
	}

	@Override
	public void setEntity(E entity) {
		this.entity = entity;
	}

	@Override
	public void createForm(BaseContainer container, FormTypeEnum formType, CallbackForm callback) {
		// En caso de no recibir ningun tipo, tomamos el tipo de visualización.
		if (formType == null) {
			formType = FormTypeEnum.VIEW;
		}

		this.callback = callback;

		try {
			switch (formType) {

			case NEW:
				log.trace("new title='" + this.getNewTitle() + "'");
				log.debug("new entity=" + entity);

				// Cargamos el titulo.
				container.setTitle(this.getNewTitle());

				// Limpiamos la ventana para dar de alta una nueva entidad.
				this.entity = this.entityClass.newInstance();
				this.emptyFields();
				break;

			case EDIT:
				log.trace("edit title='" + this.getEditTitle() + "'");
				log.debug("edit entity=" + entity);

				// Cargamos el titulo.
				container.setTitle(this.getEditTitle());

				// Cargamos la entidad para su edición.
				this.emptyFields();
				this.fromEntityToFields();
				break;

			case VIEW:
				log.trace("view title='" + this.getViewTitle() + "'");
				log.debug("view entity=" + entity);

				// Cargamos el titulo.
				container.setTitle(this.getViewTitle());

				// Cargamos la entidad para la visualización.
				this.emptyFields();
				this.fromEntityToFields();
				break;
			}
		} catch (Exception e) {
			log.error("Failed to create form", e);
			// TODO agregar la clave del mensaje y el mensaje por default.
			throw new SwingException("Fail when create the form", "");

		}
	}

	@Override
	public void reject() {
		this.entity = null;
		this.emptyFields();

		// Llamamos el callback.
		if (this.callback != null) {
			callback.reject();
		}
	}

	@Override
	public void confirm() {
		new Thread() {
			@Override
			public void run() {
				try {
					EditFormImpl.this.beforeSaveEntity();
					EditFormImpl.this.fromFieldsToEntity();
					EditFormImpl.this.getService().saveOrUpdate(EditFormImpl.this.entity);

					// Llamamos el callback.
					if (EditFormImpl.this.callback != null) {
						callback.confirm();
					}
				} catch (Exception e) {
					EditFormImpl.log.error("save entity failed", e);
					JOptionPane.showMessageDialog(EditFormImpl.this, HolderMessage.getMessage(e.getMessage(), "form.edit.save.fail.message"),
							HolderMessage.getMessage("Error", "form.edit.save.fail.message.title"), JOptionPane.ERROR_MESSAGE);
				} finally {
					EditFormImpl.this.afterSaveEntity();
				}
			}
		}.start();
	}

	/**
	 * La función encargada de ejecutar acciones antes de guardar la entidad dentro de la base de datos.
	 */
	protected void beforeSaveEntity() {
		this.disabled();

		if (this.getProgressLabel() != null && getProgressIcon() != null) {
			this.getProgressIcon().setImageObserver(this.getProgressLabel());
			this.getProgressLabel().setIcon(this.getProgressIcon());
		}
	}

	/**
	 * La función encargada de ejecutar acciones después de guardar la entidad dentro de la base de datos.
	 */
	protected void afterSaveEntity() {
		this.enabled();

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

	/**
	 * Se encarga de retornar el titulo para el formulario de alta de una nueva entidad.
	 * 
	 * @return El titulo para el formulario de alta de una nueva entidad.
	 */
	public abstract String getNewTitle();

	/**
	 * Se encarga de retornar el titulo para el formulario de modificación de una entidad..
	 * 
	 * @return El titulo para el formulario de modificación de una entidad..
	 */
	public abstract String getEditTitle();

	/**
	 * Se encarga de retornar el titulo para el formulario de visualización de una entidad.
	 * 
	 * @return El titulo para el formulario de visualización de una entidad.
	 */
	public abstract String getViewTitle();
}