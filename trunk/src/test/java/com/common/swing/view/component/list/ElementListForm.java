package com.common.swing.view.component.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;

import com.common.swing.domain.model.Element;
import com.common.swing.domain.model.ElementFilter;
import com.common.swing.domain.model.ElementServiceImpl;
import com.common.swing.view.action.ListAction;
import com.common.swing.view.action.parameter.BaseActionParameter;
import com.common.swing.view.action.parameter.TableActionParameter;
import com.common.swing.view.component.panel.BaseListPanel;
import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.ListEvent;
import com.common.swing.view.listener.ListListener;
import com.common.swing.view.notification.Notificaction;
import com.crud.swing.view.form.list.BaseListForm;

/**
 * La clase que extiende el formulario de listado de entidades.
 * 
 * @since 10/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementListForm extends BaseListForm<Element> {
	private static final long serialVersionUID = 1L;

	private ElementServiceImpl service;

	public ElementListForm() {
		this.init();
	}
	
	@Override
	protected void afterInit() {
	}

	@Override
	protected BaseListPanel<Element> createListPanel() {
		return new ElementListPanel();
	}

	public void setService(ElementServiceImpl service) {
		this.service = service;
	}

	@Override
	protected Collection<ListAction<Element>> getListActions() {
		List<ListAction<Element>> actions = new ArrayList<ListAction<Element>>();
		actions.add(this.getNuevoAction());
		actions.add(this.getBorrarAction());
		actions.add(this.getLimpiarAction());
		actions.add(this.getCargarAction());
		actions.add(this.getVerAction());
		return actions;
	}

	protected ListAction<Element> getNuevoAction() {
		ListListener<Element> tableListener = new ListListener<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(final ListEvent<Element> tableEvent) {
				new Thread() {
					public void run() {
						disabled();
						service.save(new Element("ELEMENTO " + (int) (Math.random() * 1000)));
						setEntities(service.findByFilter(new ElementFilter()));
						enabled();
					};
				}.start();
			};
		};

		ButtonDecorator buttonDecorator = new ButtonDecorator() {
			private static final long serialVersionUID = 1L;

			@Override
			public void decorateButton(JButton button) {
				button.setText("AGREGAR");
			}
		};
		return new ListAction<Element>(this.getListPanel().getList(), tableListener, buttonDecorator);
	}

	protected ListAction<Element> getBorrarAction() {
		ListListener<Element> tableListener = new ListListener<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(final ListEvent<Element> tableEvent) {
				new Thread() {
					public void run() {
						disabled();
						Collection<Element> elements = tableEvent.getSelectedEntities();
						for (Element element : elements) {
							service.delete(element);
						}
						setEntities(service.findByFilter(new ElementFilter()));
						enabled();
					};
				}.start();
			}
		};
		ButtonDecorator buttonDecorator = new ButtonDecorator() {
			private static final long serialVersionUID = 1L;

			@Override
			public void decorateButton(JButton button) {
				button.setText("BORRAR");
			}
		};
		return new ListAction<Element>(this.getListPanel().getList(), tableListener, buttonDecorator) {
			private static final long serialVersionUID = 1L;

			@Override
			public <P extends BaseActionParameter<Element>> boolean isEnabledAction(P parameter) {
				boolean permiteBorrar = true;
				for (Element element : ((TableActionParameter<Element>) parameter).getEntities()) {
					if (element.getFecha() != null) {
						permiteBorrar = false;
						break;
					}
				}
				return permiteBorrar;
			}
		};
	}

	protected ListAction<Element> getLimpiarAction() {
		ListListener<Element> tableListener = new ListListener<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(final ListEvent<Element> tableEvent) {
				emptyFields();
			}
		};
		ButtonDecorator buttonDecorator = new ButtonDecorator() {
			private static final long serialVersionUID = 1L;

			@Override
			public void decorateButton(JButton button) {
				button.setText("LIMPIAR");
			}
		};
		return new ListAction<Element>(this.getListPanel().getList(), tableListener, buttonDecorator);
	}

	protected ListAction<Element> getCargarAction() {
		ListListener<Element> tableListener = new ListListener<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(final ListEvent<Element> tableEvent) {
				setEntities(service.findByFilter(new ElementFilter()));
			}
		};
		ButtonDecorator buttonDecorator = new ButtonDecorator() {
			private static final long serialVersionUID = 1L;

			@Override
			public void decorateButton(JButton button) {
				button.setText("CARGAR");
			}
		};
		return new ListAction<Element>(this.getListPanel().getList(), tableListener, buttonDecorator);
	}

	protected ListAction<Element> getVerAction() {
		ListListener<Element> tableListener = new ListListener<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(final ListEvent<Element> tableEvent) {
				StringBuffer buffer = new StringBuffer();
				Collection<Element> elements = tableEvent.getSelectedEntities();
				for (Element element : elements) {
					buffer.append(element.toString());
					buffer.append("\n");
				}
				if (buffer.toString().length() != 0) {
					Notificaction.showMessage(ElementListForm.this, buffer.toString());
				}
			}
		};
		ButtonDecorator buttonDecorator = new ButtonDecorator() {
			private static final long serialVersionUID = 1L;

			@Override
			public void decorateButton(JButton button) {
				button.setText("VER");
			}
		};
		return new ListAction<Element>(this.getListPanel().getList(), tableListener, buttonDecorator);
	}
}