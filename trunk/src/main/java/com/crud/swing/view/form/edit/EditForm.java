package com.crud.swing.view.form.edit;

import java.awt.Dimension;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.view.callback.CallbackForm;
import com.common.swing.view.notification.Notificaction;
import com.common.util.business.service.BaseService;
import com.common.util.domain.model.Persistence;
import com.crud.swing.view.container.BaseContainer;
import com.crud.swing.view.form.BaseForm;
import com.crud.swing.view.form.util.EditFormTypeEnum;

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
public abstract class EditForm<E extends Persistence<PK>, PK extends Serializable> extends JPanel implements BaseForm {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(EditForm.class);

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
	public EditForm() {
		super();
		try {
			this.entityClass = (Class<E>) ((ParameterizedType) super.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception ex) {
			LOGGER.error("The generic parameter of this class doesn't must be empty", ex);
			throw new SwingException("The generic parameter of this class doesn't must be empty", "form.edit.error.parameter.empty");
		}
		LOGGER.trace("load dimension=[" + this.getWidthSize() + "; " + this.getHeightSize() + "]");
		this.setPreferredSize(new Dimension(this.getWidthSize(), this.getHeightSize()));
		this.init();
	}

	/**
	 * Permite inicializar los componentes del formulario.
	 */
	protected abstract void init();

	/**
	 * Se encarga de crear un formulario de acuerdo al tipo recibido y con la entidad que tenemos cargada dentro de esta.
	 * 
	 * @param formType
	 *            El tipo de formulario que vamos a crear, puede ser {@link EditFormTypeEnum#NEW}, {@link EditFormTypeEnum#EDIT} o {@link EditFormTypeEnum#VIEW}
	 * @param callback
	 *            El comportamiento que vamos a controlar despues de apretar el boton de aceptar o cancelar. Puede ser <code>null</code>.
	 * @param container
	 *            El contenedor donde vamos a colocar el formulario.
	 */
	public void createForm(BaseContainer container, EditFormTypeEnum formType, CallbackForm callback) {
		this.callback = callback;

		try {
			if (formType == null) {
				formType = EditFormTypeEnum.VIEW;
			}
			switch (formType) {
				case NEW:
					LOGGER.trace("new title='" + this.getNewTitle() + "'");
	
					container.setTitle(this.getNewTitle());
					this.entity = this.entityClass.newInstance();
					this.emptyFields();
					break;
	
				case EDIT:
					LOGGER.trace("edit title='" + this.getEditTitle() + "'");
					LOGGER.debug("edit entity=" + entity);
	
					container.setTitle(this.getEditTitle());
					this.emptyFields();
					this.fromEntityToFields();
					break;
	
				case VIEW:
					LOGGER.trace("view title='" + this.getViewTitle() + "'");
					LOGGER.debug("view entity=" + entity);
	
					container.setTitle(this.getViewTitle());
					this.emptyFields();
					this.fromEntityToFields();
					break;
			}
		} catch (Exception e) {
			LOGGER.error("failed to create form", e);
			throw new SwingException("Fail when create the form", "form.edit.error.create.form");
		}
	}

	/**
	 * Permite recuperar la entidad que estamos manipulando dentro de este formulario, en caso de que el formulario se cancele, esto retornará
	 * <code>null</code>.
	 * 
	 * @return La entidad que estamos manipulando dentro de este formulario, o <code>null</code> en caso de cancelar la edición de la entidad.
	 */
	public E getEntity() {
		return this.entity;
	}

	/**
	 * Permite cargar dentro del formulario la entidad que vamos a editar.
	 * 
	 * @param entity
	 *            La entidad que vamos a editar.
	 */
	public void setEntity(E entity) {
		this.entity = entity;
	}

	/**
	 * Se encarga de cerrar el formulario de edición de entidades y no guardar ningún cambio de esta en la base de datos.
	 */
	protected void reject() {
		this.entity = null;
		this.emptyFields();
		if (this.callback != null) {
			callback.reject();
		}
	}

	/**
	 * Se encarga del guardado de la entidad que estamos editando dentro de la base de datos.
	 */
	protected void confirm() {
		new Thread() {
			@Override
			public void run() {
				try {
					beforeSaveEntity();
					preValidate();
					fromFieldsToEntity();
					getService().saveOrUpdate(entity);
					if (callback != null) {
						callback.confirm();
					}
				} catch (Exception e) {
					LOGGER.error("save entity failed", e);
					Notificaction.showErrorMessage(EditForm.this, "form.edit.save.fail.message");
				} finally {
					afterSaveEntity();
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
	 * Se encarga de cargar los campos de la ventana de acuerdo a los valores de los atributos de la entidad que queremos editar.
	 */
	protected abstract void fromEntityToFields();

	/**
	 * Permite pre validar los datos que tenemos dentro del formulario antes de cargarlos dentro de la entidad.
	 * 
	 * @throws SwingException
	 *             En caso de que no se pase alguna validación.
	 */
	protected abstract void preValidate() throws SwingException;

	/**
	 * Se encarga de tomar los valores de los campos de la ventana y cargarlos dentro de la entidad antes de guardarlo dentro de la base de datos.
	 */
	protected abstract void fromFieldsToEntity();

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
	protected abstract JLabel getProgressLabel();

	/**
	 * Se encarga de retornar el titulo para el formulario de alta de una nueva entidad.
	 * 
	 * @return El titulo para el formulario de alta de una nueva entidad.
	 */
	protected abstract String getNewTitle();

	/**
	 * Se encarga de retornar el titulo para el formulario de modificación de una entidad..
	 * 
	 * @return El titulo para el formulario de modificación de una entidad..
	 */
	protected abstract String getEditTitle();

	/**
	 * Se encarga de retornar el titulo para el formulario de visualización de una entidad.
	 * 
	 * @return El titulo para el formulario de visualización de una entidad.
	 */
	protected abstract String getViewTitle();

	/**
	 * Se encarga de retornar el servicio que vamos a utilizar para la entidad que tenemos en el panel.
	 * 
	 * @return El servicio para la entidad que tenemos en el panel.
	 */
	protected abstract BaseService<E, PK> getService();
}