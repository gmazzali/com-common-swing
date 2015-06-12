package com.common.swing.view.component.edit;

import java.util.Collection;

import javax.swing.JButton;

import com.common.swing.domain.model.Element;
import com.common.swing.view.action.EditAction;
import com.common.swing.view.component.panel.BaseEditPanel;
import com.common.swing.view.decorator.ButtonDecorator;
import com.crud.swing.view.form.edit.BaseEditForm;

/**
 * El formulario de edición de entidades.
 * 
 * @since 24/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementEditForm extends BaseEditForm<Element> {
	private static final long serialVersionUID = 1L;

	public ElementEditForm() {
		this.init();
	}

	@Override
	protected void afterInit() {
	}

	@Override
	protected Collection<EditAction<Element>> getEditActions() {
		return null;
	}

	@Override
	protected BaseEditPanel<Element> createEditPanel() {
		return new ElementEditPanel();
	}

	@Override
	protected ButtonDecorator getConfirmButtonDecorator() {
		return new ButtonDecorator() {
			private static final long serialVersionUID = 1L;

			@Override
			public void decorateButton(JButton button) {
				button.setText("ACEPTAR");
			}
		};
	}

	@Override
	protected ButtonDecorator getCancelButtonDecorator() {
		return new ButtonDecorator() {
			private static final long serialVersionUID = 1L;

			@Override
			public void decorateButton(JButton button) {
				button.setText("CANCELAR");
			}
		};
	}

	@Override
	protected String getNewTitle() {
		return "Nuevo elemento";
	}

	@Override
	protected String getEditTitle() {
		return "Edición de elemento";
	}

	@Override
	protected String getViewTitle() {
		return "Vista elemento";
	}

	@Override
	protected boolean isEnabledConfirmButton(Element entity) {
		return true;
	}
}