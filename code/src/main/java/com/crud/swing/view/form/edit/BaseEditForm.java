package com.crud.swing.view.form.edit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.view.action.EditAction;
import com.common.swing.view.action.parameter.BaseActionParameter;
import com.common.swing.view.action.parameter.EditActionParameter;
import com.common.swing.view.bean.EditBean;
import com.common.swing.view.callback.CallbackEdit;
import com.common.swing.view.component.panel.BaseEditPanel;
import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.EditEvent;
import com.common.swing.view.listener.EditListener;
import com.common.swing.view.notification.Notificaction;
import com.common.util.business.tool.VerifierUtil;
import com.common.util.business.tool.collection.CollectionUtil;
import com.crud.swing.view.container.BaseContainer;
import com.crud.swing.view.form.BaseForm;
import com.crud.swing.view.form.util.EditType;

/**
 * El panel base para la edición y/o visualización de entidades dentro del sistema.
 * 
 * @since 27/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de beans que vamos a desplegar dentro de este panel.
 */
public abstract class BaseEditForm<E extends EditBean<?>> extends JPanel implements BaseForm {
	private static final long serialVersionUID = 1L;

	/**
	 * El panel de edición.
	 */
	protected BaseEditPanel<E> editPanel;
	/**
	 * El listado de las acciones del panel de edición.
	 */
	protected Collection<EditAction<E>> editActions;
	/**
	 * El callback de edición.
	 */
	protected CallbackEdit<E> callbackEdit;
	/**
	 * Los títulos de la ventana.
	 */
	private Map<EditType, String> titles;
	/**
	 * El elemento de exclusividad de la busqueda.
	 */
	private final Object editMutex = new Object();

	/**
	 * Permite inicializar los componentes del panel.
	 */
	@PostConstruct
	protected void init() {
		this.removeAll();
		this.setLayout(new BorderLayout());

		this.editPanel = this.createEditPanel();
		this.add(this.editPanel, BorderLayout.CENTER);

		this.editActions = this.getDefaultEditActions();
		Collection<EditAction<E>> temporal = this.getEditActions();
		if (CollectionUtil.isNotEmpty(temporal)) {
			this.editActions.addAll(temporal);
		}
		if (CollectionUtil.isNotEmpty(this.editActions)) {
			this.initEditActionPanel(this);
		}

		this.titles = new HashMap<EditType, String>();
		this.titles.put(EditType.NEW, this.getNewTitle());
		this.titles.put(EditType.EDIT, this.getEditTitle());
		this.titles.put(EditType.VIEW, this.getViewTitle());

		this.afterInit();
	}

	public void initNewForm(BaseContainer baseContainer, CallbackEdit<E> callbackEdit, final E newBean) {
		this.initForm(baseContainer, callbackEdit, newBean, EditType.NEW);
	}

	public void initEditForm(BaseContainer baseContainer, CallbackEdit<E> callbackEdit, final E editBean) {
		this.initForm(baseContainer, callbackEdit, editBean, EditType.EDIT);
	}

	public void initViewForm(BaseContainer baseContainer, CallbackEdit<E> callbackEdit, final E viewBean) {
		this.initForm(baseContainer, callbackEdit, viewBean, EditType.VIEW);
	}

	/**
	 * Se encarga de inicializar el formulario de acuerdo al bean recibido y al tipo que queremos.
	 * 
	 * @param baseContainer
	 *            El contenedor del panel, en caso que sea <code>null</code> no se le va a cargar el título.
	 * @param callbackEdit
	 *            El callback de edición.
	 * @param bean
	 *            El elemento que vamos a tener dentro de este panel. Si es un panel de alta, se debe recibir un elemento nuevo.
	 * @param type
	 *            El tipo de formulario que vamos a inicializar, puede ser {@link EditType#NEW}, {@link EditType#EDIT} o {@link EditType#VIEW}
	 */
	protected void initForm(BaseContainer baseContainer, CallbackEdit<E> callbackEdit, final E bean, EditType type) {
		VerifierUtil.checkNotNull(bean, "The bean cannot be null");
		VerifierUtil.checkNotNull(callbackEdit, "The callback cannot be null");
		type = type != null ? type : EditType.VIEW;

		synchronized (editMutex) {
			this.callbackEdit = callbackEdit;
			this.editPanel.emptyFields();
			this.editPanel.setBean(bean);

			if (type == EditType.VIEW) {
				this.editPanel.setReadOnly(true);
			} else {
				this.editPanel.setReadOnly(false);
			}

			if (baseContainer != null) {
				baseContainer.setTitle(this.titles.get(type));
			}
		}
	}

	/**
	 * Permite actualizar los estados de los botones de las acciones de acuerdo al bean que tenemos aca dentro.
	 */
	protected void updateActionButtons(final E bean) {
		// Actualizamos los botones.
		new Thread() {
			@Override
			public void run() {
				synchronized (editMutex) {
					for (EditAction<E> editAction : editActions) {
						editAction.getButton().setVisible(editAction.isVisibleAction(new EditActionParameter<E>(bean)));
						editAction.getButton().setEnabled(editAction.isEnabledAction(new EditActionParameter<E>(bean)));
					}
				}
			}
		}.start();
	}

	/**
	 * Permite cargar el panel de las acciones de edición.
	 * 
	 * @param panel
	 *            El panel donde va a cargarse el panel de acciones.
	 */
	protected void initEditActionPanel(JPanel panel) {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		for (EditAction<E> editAction : this.editActions) {
			buttonPanel.add(editAction.createButton());
		}
		panel.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Permite crear las acciones por omisión para la edición.
	 * 
	 * @return Las acciones por omisión para la edición.
	 */
	protected Collection<EditAction<E>> getDefaultEditActions() {
		EditListener<E> confirmListener = new EditListener<E>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(EditEvent<E> editEvent) {
				executeConfirm();
			}
		};
		EditListener<E> cancelListener = new EditListener<E>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(EditEvent<E> editEvent) {
				executeCancel();
			}
		};

		return CollectionUtil.newArrayList(new EditAction<E>(confirmListener, this.getConfirmButtonDecorator()) {
			private static final long serialVersionUID = 1L;

			@Override
			public <P extends BaseActionParameter<E>> boolean isEnabledAction(P parameter) {
				return isEnabledConfirmButton(((EditActionParameter<E>) parameter).getEntity());
			}

			@Override
			public <P extends BaseActionParameter<E>> boolean isVisibleAction(P parameter) {
				return isVisibleConfirmButton(((EditActionParameter<E>) parameter).getEntity());
			}
		}, new EditAction<E>(cancelListener, this.getCancelButtonDecorator()) {
			private static final long serialVersionUID = 1L;

			@Override
			public <P extends BaseActionParameter<E>> boolean isEnabledAction(P parameter) {
				return isEnabledCancelButton(((EditActionParameter<E>) parameter).getEntity());
			}

			@Override
			public <P extends BaseActionParameter<E>> boolean isVisibleAction(P parameter) {
				return isVisibleCancelButton(((EditActionParameter<E>) parameter).getEntity());
			}
		});
	}

	/**
	 * Permite saber si va a habilitarse o no el botón de confirmación.
	 * 
	 * @param entity
	 *            La entidad que tenemos dentro del panel de edición.
	 * @return <code>true</code> en caso que querramos el botón de confirmación habilitado, en caso contrario, retorna <code>false</code>.
	 */
	protected boolean isEnabledConfirmButton(E entity) {
		return true;
	}

	/**
	 * Permite saber si va a habilitarse o no el botón de cancelación.
	 * 
	 * @param entity
	 *            La entidad que tenemos dentro del panel de edición.
	 * @return <code>true</code> en caso que querramos el botón de cancelación habilitado, en caso contrario, retorna <code>false</code>.
	 */
	protected boolean isEnabledCancelButton(E entity) {
		return true;
	}

	/**
	 * Permite saber si va a visualizarse o no el botón de confirmación.
	 * 
	 * @param entity
	 *            La entidad que tenemos dentro del panel de edición.
	 * @return <code>true</code> en caso que querramos el botón de confirmación visualizado, en caso contrario, retorna <code>false</code>.
	 */
	protected boolean isVisibleConfirmButton(E entity) {
		return true;
	}

	/**
	 * Permite saber si va a visualizarse o no el botón de cancelación.
	 * 
	 * @param entity
	 *            La entidad que tenemos dentro del panel de edición.
	 * @return <code>true</code> en caso que querramos el botón de cancelación visualizado, en caso contrario, retorna <code>false</code>.
	 */
	protected boolean isVisibleCancelButton(E entity) {
		return true;
	}

	/**
	 * Permite guardar lo que estamos editando.
	 */
	protected void executeConfirm() {
		new Thread() {
			@Override
			public void run() {
				synchronized (editMutex) {
					try {
						disabled();
						E entity = editPanel.getBean();
						callbackEdit.confirm(entity);
					} catch (SwingException e) {
						Notificaction.showErrorMessage(BaseEditForm.this, e.getErrors());
					} finally {
						enabled();
					}
				}
			}
		}.start();
	}

	/**
	 * Permite descartar lo que estamos editando.
	 */
	protected void executeCancel() {
		new Thread() {
			@Override
			public void run() {
				synchronized (editMutex) {
					try {
						disabled();
						callbackEdit.cancel();
					} catch (SwingException e) {
						Notificaction.showErrorMessage(BaseEditForm.this, e.getErrors());
					} finally {
						enabled();
					}
				}
			}
		}.start();
	}

	@Override
	public void setEnabled(boolean enabled) {
		for (EditAction<E> editAction : this.editActions) {
			editAction.getButton().setEnabled(enabled);
		}
	}

	@Override
	public void enabled() {
		this.editPanel.enabled();
		this.setEnabled(true);
	}

	@Override
	public void disabled() {
		this.editPanel.disabled();
		this.setEnabled(false);
	}

	@Override
	public void emptyFields() {
		this.editPanel.emptyFields();
	}

	/**
	 * Permite recuperar el panel de edición.
	 * 
	 * @return El panel de edición.
	 */
	public BaseEditPanel<E> getEditPanel() {
		return editPanel;
	}

	/**
	 * Retorna el titulo para el formulario de alta de una nueva entidad.
	 * 
	 * @return El titulo para el alta.
	 */
	protected abstract String getNewTitle();

	/**
	 * Retorna el titulo para el formulario de modificación de una entidad.
	 * 
	 * @return El titulo para el edición.
	 */
	protected abstract String getEditTitle();

	/**
	 * Retorna el titulo para el formulario de visualización de una entidad.
	 * 
	 * @return El titulo para visualización.
	 */
	protected abstract String getViewTitle();

	/**
	 * Permite terminar de configurar el panel.
	 */
	protected abstract void afterInit();

	/**
	 * Permite crear el panel de edición.
	 * 
	 * @return El panel de edición creado.
	 */
	protected abstract BaseEditPanel<E> createEditPanel();

	/**
	 * Retorna el decorador del botón de confirmación de edición.
	 * 
	 * @return El decorador del botón de confirmación de edición.
	 */
	protected abstract ButtonDecorator getConfirmButtonDecorator();

	/**
	 * Retorna el decorador del botón de cancelación de edición.
	 * 
	 * @return El decorador del botón de cancelación de edición.
	 */
	protected abstract ButtonDecorator getCancelButtonDecorator();

	/**
	 * Permite crear acciones de edición propias.
	 * 
	 * @return Las nuevas acciones de edición que vamos a agregar.
	 */
	protected abstract Collection<EditAction<E>> getEditActions();
}