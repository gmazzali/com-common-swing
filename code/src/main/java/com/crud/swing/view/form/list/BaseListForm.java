package com.crud.swing.view.form.list;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.common.swing.view.action.ListAction;
import com.common.swing.view.action.parameter.TableActionParameter;
import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.panel.BaseListPanel;
import com.common.util.business.tool.collection.CollectionUtil;
import com.crud.swing.view.form.BaseForm;

/**
 * La clase que permite definir un panel donde vamos a desplegar una lista de elementos con las acciones correspondiente para las mismas.
 * 
 * @since 10/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <B>
 *            Las clases de las entidades que vamos a manipular dentro de este panel.
 */
public abstract class BaseListForm<B extends RowBean> extends JPanel implements BaseForm {
	private static final long serialVersionUID = 1L;

	/**
	 * El panel de la tabla de las entidades.
	 */
	protected BaseListPanel<B> listPanel;
	/**
	 * El listado de las acciones.
	 */
	protected Collection<ListAction<B>> listActions;
	/**
	 * El objecto que permite el acceso exclusivo a las acciones.
	 */
	private final Object actionsMutex = new Object();

	/**
	 * Metodo que permite iniciar el panel de la tabla.
	 */
	@PostConstruct
	protected void init() {
		this.removeAll();
		this.setLayout(new BorderLayout());

		this.listPanel = this.createListPanel();
		this.add(this.listPanel, BorderLayout.CENTER);

		this.listActions = this.getListActions();
		if (CollectionUtil.isNotEmpty(this.listActions)) {
			this.initListActionPanel(this);
		}

		ListSelectionModel cellSelectionModel = this.listPanel.getList().getSelectionModel();
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				new Thread() {
					@Override
					public void run() {
						synchronized (actionsMutex) {
							for (ListAction<B> listAction : listActions) {
								listAction.getButton().setVisible(
										listAction.isVisibleAction(new TableActionParameter<B>(listPanel.getList().getValues())));
								listAction.getButton().setEnabled(
										listAction.isEnabledAction(new TableActionParameter<B>(listPanel.getList().getValues())));
							}
						}
					}
				}.start();
			}
		});

		this.afterInit();
	}

	/**
	 * Permite cargar el panel de las acciones de la lista.
	 * 
	 * @param panel
	 *            El panel donde va a cargarse el panel de acciones de la lista.
	 */
	protected void initListActionPanel(JPanel panel) {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		for (ListAction<B> listAction : this.listActions) {
			buttonPanel.add(listAction.createButton());
		}
		panel.add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void setEnabled(boolean enabled) {
		synchronized (this.actionsMutex) {
			for (ListAction<B> listAction : this.listActions) {
				listAction.getButton().setEnabled(enabled);
			}
		}
	}

	@Override
	public void enabled() {
		this.listPanel.enabled();
		this.setEnabled(true);
	}

	@Override
	public void disabled() {
		this.listPanel.disabled();
		this.setEnabled(false);
	}

	@Override
	public void emptyFields() {
		this.listPanel.emptyFields();
	}

	/**
	 * Permite cargar una lista de entidades dentro de la lista.
	 * 
	 * @param entities
	 *            La lista de entidades que vamos a cargar. En caso de que la misma sea vacía o <code>null</code> se va a limpiar la misma.
	 */
	public void setEntities(Collection<B> entities) {
		if (CollectionUtil.isNotEmpty(entities)) {
			this.listPanel.getList().setValues(entities);
		} else {
			this.emptyFields();
		}
	}

	/**
	 * Permite recuperar el panel de la lista.
	 * 
	 * @return El panel de la lista.
	 */
	public BaseListPanel<B> getListPanel() {
		return listPanel;
	}

	/**
	 * Permite terminar de configurar el formulario.
	 */
	protected abstract void afterInit();

	/**
	 * Permite recuperar la instancia del panel de la lista.
	 * 
	 * @return El panel de la lista de las entidades.
	 */
	protected abstract BaseListPanel<B> createListPanel();

	/**
	 * Retorna el listado de las acciones de la lista.
	 * 
	 * @return La lista de acción de la lista.
	 */
	protected abstract Collection<ListAction<B>> getListActions();
}