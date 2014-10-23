package com.common.swing.view.component;

import java.util.Collection;

import javax.swing.JButton;

import com.common.swing.domain.model.Element;
import com.common.swing.domain.model.ElementServiceImpl;
import com.common.swing.view.action.SearchAction;
import com.common.swing.view.bean.SearchBean;
import com.common.swing.view.decorator.ButtonDecorator;
import com.crud.swing.view.form.search.SearchForm;

public class ElementSearchForm extends SearchForm<Element, Element> {
	private static final long serialVersionUID = 1L;

	private ElementServiceImpl service;

	@Override
	public Integer getHeightSize() {
		return 100;
	}

	@Override
	public Integer getWidthSize() {
		return 350;
	}

	@Override
	protected ElementSearchPanel createSearchPanel() {
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

	public void setService(ElementServiceImpl service) {
		this.service = service;
	}

	@Override
	protected Collection<Element> search(SearchBean filter) {
		return this.service.findAll();
	}
}