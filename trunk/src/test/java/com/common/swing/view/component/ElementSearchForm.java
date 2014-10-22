package com.common.swing.view.component;

import java.util.Collection;

import javax.swing.JButton;

import com.common.swing.domain.model.Element;
import com.common.swing.domain.model.ElementFilter;
import com.common.swing.domain.model.ElementServiceImpl;
import com.common.swing.view.action.SearchAction;
import com.common.swing.view.bean.ElementSearchBean;
import com.common.swing.view.bean.SearchBean;
import com.common.swing.view.component.panel.BaseSearchPanel;
import com.common.swing.view.decorator.ButtonDecorator;
import com.common.util.business.service.BaseService;
import com.crud.swing.view.form.search.SearchForm;

public class ElementSearchForm extends SearchForm<Element, Integer> {
	private static final long serialVersionUID = 1L;

	private ElementServiceImpl elementServiceImpl;

	@Override
	public Integer getHeightSize() {
		return 100;
	}

	@Override
	public Integer getWidthSize() {
		return 350;
	}

	@Override
	protected BaseSearchPanel<? extends SearchBean> createSearchPanel() {
		return new ElementSearchPanel();
	}

	@Override
	protected ButtonDecorator getSearchButtonDecorator() {
		return new ButtonDecorator() {
			private static final long serialVersionUID = 1L;

			@Override
			public void decorateButton(JButton button) {
				button.setText("BUSCAR");
			}
		};
	}

	@Override
	protected ButtonDecorator getCleanFilterButtonDecorator() {
		return new ButtonDecorator() {
			private static final long serialVersionUID = 1L;

			@Override
			public void decorateButton(JButton button) {
				button.setText("LIMPIAR");
			}
		};
	}

	@Override
	protected Collection<SearchAction<Element>> getFilterActions() {
		return null;
	}

	@Override
	protected BaseService<Element, Integer> getService() {
		return elementServiceImpl;
	}

	public void setService(ElementServiceImpl elementServiceImpl) {
		this.elementServiceImpl = elementServiceImpl;
	}

	@Override
	protected ElementFilter createFilter(SearchBean filter) {
		ElementFilter elementFilter = new ElementFilter();
		elementFilter.setName(((ElementSearchBean) filter).getName());
		return elementFilter;
	}
}