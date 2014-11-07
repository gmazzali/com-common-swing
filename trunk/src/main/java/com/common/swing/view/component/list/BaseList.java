package com.common.swing.view.component.list;

import javax.swing.JList;
import javax.swing.ListModel;

import org.apache.log4j.Logger;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.list.formatter.PropertyCellFormatter;
import com.common.swing.view.component.list.model.BaseListModel;
import com.common.util.business.tool.StringUtil;

public abstract class BaseList<B extends RowBean> extends JList<B> {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(BaseList.class);

	/**
	 * El modelo de la lista.
	 */
	protected BaseListModel<B> listModel;

	public BaseList(String visibleProperty) {
		this.init(visibleProperty);
	}

	protected void init(String visibleProperty) {
		if (StringUtil.isEmpty(visibleProperty)) {
			LOGGER.warn("The property must least have one");
			throw new SwingException("The list of visibles properties must least have one", "swing.component.baselist.error.property.null");
		}
		this.listModel = this.createListModel();
		super.setModel(this.listModel);
		this.setCellRenderer(new PropertyCellFormatter<B>(visibleProperty));
	}

	@Override
	public void setModel(ListModel<B> model) {
		// No se le carga ningun modelo a la lista, ya que se lo creamos nosotros al mismo.
	}

	protected abstract BaseListModel<B> createListModel();
}