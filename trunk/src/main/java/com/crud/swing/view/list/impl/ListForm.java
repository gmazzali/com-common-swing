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
import com.common.util.business.service.BaseService;
import com.common.util.business.tool.collection.CollectionUtil;
import com.common.util.domain.model.Persistence;
import com.common.util.persistence.filter.BaseFilter;
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
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a listar.
 */
public abstract class ListForm<E extends Persistence<PK>, PK extends Serializable> extends JPanel implements BaseForm {
	private static final long serialVersionUID = 1L;

	/**
	 * El filtro de busqueda.
	 */
	protected BaseFilter<E, PK> baseFilter;
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
	public void emptyFields() {
		this.table.clearTable();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		this.table.setEnabled(enabled);
		for (JButton button : this.tableActionButton) {
			button.setEnabled(enabled);
		}
	}

	/**
	 * Permite cargar una lista de entidades dentro de la tabla.
	 * 
	 * @param baseFilter
	 *            El filtro de busqueda de las entidades.
	 */
	public void loadEntities(BaseFilter<E, PK> baseFilter) {
		this.baseFilter = baseFilter;
		if (this.baseFilter != null) {
			this.table.setValues(this.getService().findByFilter(this.baseFilter));
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
	 * Se encarga de retornar el servicio que vamos a utilizar para la entidad que tenemos en el panel.
	 * 
	 * @return El servicio para la entidad que tenemos en el panel.
	 */
	protected abstract BaseService<E, PK> getService();

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