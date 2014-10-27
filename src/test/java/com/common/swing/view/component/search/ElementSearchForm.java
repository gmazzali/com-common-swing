package com.common.swing.view.component.search;

import java.util.Collection;

import javax.swing.JButton;

import com.common.swing.domain.model.Element;
import com.common.swing.domain.model.ElementServiceImpl;
import com.common.swing.view.action.SearchAction;
import com.common.swing.view.bean.SearchBean;
import com.common.swing.view.decorator.ButtonDecorator;
import com.crud.swing.view.form.search.BaseSearchForm;

/**
 * Permite crear un formulario de búsqueda para pruebas.
 * 
 * @since 23/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementSearchForm extends BaseSearchForm<Element, Element> {
	private static final long serialVersionUID = 1L;

	private ElementServiceImpl service;

	public ElementSearchForm() {
		this.init();
	}

	@Override
	protected void afterInit() {
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