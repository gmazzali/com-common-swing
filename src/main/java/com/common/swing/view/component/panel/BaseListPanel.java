package com.common.swing.view.component.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.table.BaseTable;

/**
 * La clase que permite definir un panel donde vamos a desplegar una tabla con datos.
 * 
 * @since 27/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <B>
 *            El bean de tabla que vamos a manipular dentro de este panel.
 */
public abstract class BaseListPanel<B extends RowBean> extends JPanel implements BasePanel {
	private static final long serialVersionUID = 1L;

	/**
	 * La tabla del panel.
	 */
	private BaseTable<B> table;

	/**
	 * Permite inicializar los componentes del panel.
	 */
	@PostConstruct
	protected void init() {
		this.removeAll();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(this.getWidthSize(), this.getHeightSize()));

		this.table = this.createTable();
		this.table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.add(this.table);
		scrollPane.setViewportView(this.table);
		this.add(scrollPane, BorderLayout.CENTER);

		this.loadColumnsRenderer(this.table);
		this.loadHeadersRenderer(this.table);

		this.afterInit();
	}

	@Override
	public void enabled() {
		this.table.setEnabled(true);
	}

	@Override
	public void disabled() {
		this.table.setEnabled(false);
	}

	@Override
	public void emptyFields() {
		this.table.clearTable();
	}

	/**
	 * Permite retornar la tabla del panel.
	 * 
	 * @return La tabla del panel.
	 */
	public BaseTable<B> getTable() {
		return table;
	}

	/**
	 * Permite terminar de configurar el panel.
	 */
	protected abstract void afterInit();

	/**
	 * Permite crear una tabla para desplegar en el panel.
	 * 
	 * @return La instancia de la tabla.
	 */
	protected abstract BaseTable<B> createTable();

	/**
	 * Permite cargar los generadores de columnas a la tabla.
	 * 
	 * @param table
	 *            La tabla a la que le vamos a cargar el render de las columnas.
	 */
	protected abstract void loadColumnsRenderer(BaseTable<B> table);

	/**
	 * Permite cargar los generadores de cabeceras columnas a la tabla.
	 * 
	 * @param table
	 *            La tabla a la que le vamos a cargar el render de las cabeceras.
	 */
	protected abstract void loadHeadersRenderer(BaseTable<B> table);
}