package com.common.swing.view.component.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.list.BaseList;
import com.common.swing.view.component.list.formatter.PropertyCellFormatter;

/**
 * La clase que permite definir un panel donde vamos a desplegar una lista con datos.
 * 
 * @since 10/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <B>
 *            El bean de tabla que vamos a manipular dentro de este panel.
 */
public abstract class BaseListPanel<B extends RowBean> extends JPanel implements BasePanel {
	private static final long serialVersionUID = 1L;

	/**
	 * La lista del panel.
	 */
	private BaseList<B> list;

	/**
	 * Permite inicializar los componentes del panel.
	 */
	@PostConstruct
	@SuppressWarnings("unchecked")
	protected void init() {
		this.removeAll();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(this.getWidthSize(), this.getHeightSize()));

		this.list = this.createList();
		ListCellRenderer<B> listCellRenderer = (ListCellRenderer<B>) this.getFormatterProperty();
		if (listCellRenderer != null) {
			this.list.setCellRenderer(listCellRenderer);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.add(this.list);
		scrollPane.setViewportView(this.list);
		this.add(scrollPane, BorderLayout.CENTER);

		this.afterInit();
	}

	@Override
	public void enabled() {
		this.list.setEnabled(true);
	}

	@Override
	public void disabled() {
		this.list.setEnabled(false);
	}

	@Override
	public void emptyFields() {
		this.list.clearList();
	}

	/**
	 * Permite retornar la lista del panel.
	 * 
	 * @return La lista del panel.
	 */
	public BaseList<B> getList() {
		return list;
	}

	/**
	 * Permite terminar de configurar el panel.
	 */
	protected abstract void afterInit();

	/**
	 * Permite crear una lista para desplegar en el panel.
	 * 
	 * @return La instancia de la lista.
	 */
	protected abstract BaseList<B> createList();

	/**
	 * Permite crear el formateador de las fila de la lista.
	 * 
	 * @return El formateador de cada fila de la lista.
	 */
	protected abstract PropertyCellFormatter<B> getFormatterProperty();
}