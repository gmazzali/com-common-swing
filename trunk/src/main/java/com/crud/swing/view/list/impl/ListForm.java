package com.crud.swing.view.list.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.common.swing.view.action.TableAction;
import com.common.swing.view.component.table.BaseTable;
import com.common.util.business.tool.collection.CollectionUtil;
import com.crud.swing.view.BaseForm;

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
public abstract class ListForm<E extends Serializable> extends JPanel implements BaseForm {
	private static final long serialVersionUID = 1L;

	/**
	 * La tabla de las entidades.
	 */
	protected BaseTable<E> table;
	/**
	 * El listado de los botones de las acciones.
	 */
	protected Collection<JButton> tableActionButton;

	/**
	 * El contrustor del panel de listado.
	 */
	public ListForm() {
		this.init();
	}

	/**
	 * Metodo que permite iniciar el panel de la tabla.
	 */
	protected void init() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(this.getWidthSize(), this.getHeightSize()));

		this.table = this.createTable();
		this.table.setFillsViewportHeight(true);
		this.addColumnGenerators(this.table);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.add(this.table);
		scrollPane.setViewportView(this.table);
		this.add(scrollPane, BorderLayout.CENTER);

		this.tableActionButton = new ArrayList<JButton>();
		if (CollectionUtil.isNotEmpty(this.getTableActions())) {
			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			for (TableAction<E> tableAction : this.getTableActions()) {
				JButton button = tableAction.createButton();
				buttonPanel.add(button);
				this.tableActionButton.add(button);
			}
			this.add(buttonPanel, BorderLayout.SOUTH);
		}

		this.afterInit();
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.table.setEnabled(enabled);
		for (JButton button : this.tableActionButton) {
			button.setEnabled(enabled);
		}
	}

	@Override
	public void emptyFields() {
		this.table.clearTable();
	}

	/**
	 * Permite cargar una lista de entidades dentro de la tabla.
	 * 
	 * @param entities
	 *            La lista de entidades que vamos a cargar. En caso de que la misma sea vacía o <code>null</code> se va a limpiar la misma.
	 */
	public void setEntities(Collection<E> entities) {
		if (CollectionUtil.isNotEmpty(entities)) {
			this.table.setValues(entities);
		} else {
			this.emptyFields();
		}
	}

	/**
	 * Permite recuperar la tabla.
	 * 
	 * @return La tabla del listado de entidades.
	 */
	public BaseTable<E> getTable() {
		return table;
	}

	/**
	 * Permite terminar de configurar los elementos del panel del listado.
	 */
	protected abstract void afterInit();

	/**
	 * Permite crear la tabla para cargar las entidades.
	 * 
	 * @return La tabla creada.
	 */
	protected abstract BaseTable<E> createTable();

	/**
	 * Permite cargar los generadores de columnas a la tabla.
	 * 
	 * @param table
	 *            La tabla a la que vamos a cargar los generadores de columnas.
	 */
	protected abstract void addColumnGenerators(BaseTable<E> table);

	/**
	 * Retorna el listado de las acciones de la tabla.
	 */
	protected abstract Collection<TableAction<E>> getTableActions();
}