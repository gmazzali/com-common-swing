package com.crud.swing.view.form.list;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.common.swing.view.action.TableAction;
import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.panel.BaseListPanel;
import com.common.util.business.tool.collection.CollectionUtil;
import com.crud.swing.view.form.BaseForm;

/**
 * La clase que permite definir un panel donde vamos a desplegar una lista de elementos con las acciones correspondiente para las mismas.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            Las clases de las entidades que vamos a manipular dentro de este panel.
 */
public abstract class BaseListForm<E extends RowBean> extends JPanel implements BaseForm {
	private static final long serialVersionUID = 1L;

	/**
	 * El panel de la tabla de las entidades.
	 */
	protected BaseListPanel<E> tablePanel;
	/**
	 * El listado de las acciones y su acceso exclusivo.
	 */
	protected Collection<TableAction<E>> tableActions;
	private Object actionsMutex = new Object();

	/**
	 * Metodo que permite iniciar el panel de la tabla.
	 */
	@PostConstruct
	protected void init() {
		this.setLayout(new BorderLayout());

		this.tablePanel = this.createTablePanel();
		this.add(this.tablePanel, BorderLayout.CENTER);

		this.tableActions = this.getTableActions();
		if (CollectionUtil.isNotEmpty(this.tableActions)) {
			this.initTableActionPanel(this);
		}

		ListSelectionModel cellSelectionModel = this.tablePanel.getTable().getSelectionModel();
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				new Thread() {
					public void run() {
						synchronized (actionsMutex) {
							for (TableAction<E> tableAction : tableActions) {
								tableAction.getButton().setVisible(tableAction.isVisibleAction(tablePanel.getTable().getSelectedValues()));
								tableAction.getButton().setEnabled(tableAction.isEnabledAction(tablePanel.getTable().getSelectedValues()));
							}
						}
					}
				}.start();
			}
		});

		this.afterInit();
	}

	/**
	 * Permite cargar el panel de las acciones de la tabla.
	 * 
	 * @param panel
	 *            El panel donde va a cargarse el panel de acciones de la tabla.
	 */
	protected void initTableActionPanel(JPanel panel) {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		for (TableAction<E> tableAction : this.tableActions) {
			buttonPanel.add(tableAction.createButton());
		}
		panel.add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void setEnabled(boolean enabled) {
		synchronized (actionsMutex) {
			for (TableAction<E> tableAction : this.tableActions) {
				tableAction.getButton().setEnabled(enabled);
			}
		}
	}

	@Override
	public void enabled() {
		this.tablePanel.enabled();
		this.setEnabled(true);
	}

	@Override
	public void disabled() {
		this.tablePanel.disabled();
		this.setEnabled(false);
	}

	@Override
	public void emptyFields() {
		this.tablePanel.emptyFields();
	}

	/**
	 * Permite cargar una lista de entidades dentro de la tabla.
	 * 
	 * @param entities
	 *            La lista de entidades que vamos a cargar. En caso de que la misma sea vacía o <code>null</code> se va a limpiar la misma.
	 */
	public void setEntities(Collection<E> entities) {
		if (CollectionUtil.isNotEmpty(entities)) {
			this.tablePanel.getTable().setValues(entities);
		} else {
			this.emptyFields();
		}
	}

	/**
	 * Permite recuperar el panel de la tabla.
	 * 
	 * @return El panel de la tabla.
	 */
	public BaseListPanel<E> getTablePanel() {
		return tablePanel;
	}

	/**
	 * Permite terminar de configurar el formulario.
	 */
	protected abstract void afterInit();

	/**
	 * Permite recuperar la instancia del panel de la tabla.
	 * 
	 * @return El panel de la tabla para listar las entidades.
	 */
	protected abstract BaseListPanel<E> createTablePanel();

	/**
	 * Retorna el listado de las acciones de la tabla.
	 */
	protected abstract Collection<TableAction<E>> getTableActions();
}