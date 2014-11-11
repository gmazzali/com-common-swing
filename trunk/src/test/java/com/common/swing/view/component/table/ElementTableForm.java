package com.common.swing.view.component.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import com.common.swing.domain.model.Element;
import com.common.swing.domain.model.ElementFilter;
import com.common.swing.domain.model.ElementServiceImpl;
import com.common.swing.view.action.TableAction;
import com.common.swing.view.action.parameter.BaseActionParameter;
import com.common.swing.view.action.parameter.TableActionParameter;
import com.common.swing.view.component.panel.BaseTablePanel;
import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.TableEvent;
import com.common.swing.view.listener.TableListener;
import com.common.swing.view.notification.Notificaction;
import com.crud.swing.view.form.list.BaseTableForm;

/**
 * La clase que extiende el formulario de listado de entidades.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementTableForm extends BaseTableForm<Element> {
	private static final long serialVersionUID = 1L;

	private ElementServiceImpl service;

	public ElementTableForm() {
		this.init();
	}

	@Override
	protected BaseTablePanel<Element> createTablePanel() {
		return new ElementTablePanel();
	}

	@Override
	protected void afterInit() {
		this.getTablePanel().getTable().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}

	@Override
	protected Collection<TableAction<Element>> getTableActions() {
		List<TableAction<Element>> actions = new ArrayList<TableAction<Element>>();
		actions.add(this.getNuevoAction());
		actions.add(this.getBorrarAction());
		actions.add(this.getLimpiarAction());
		actions.add(this.getCargarAction());
		actions.add(this.getVerAction());
		return actions;
	}

	protected TableAction<Element> getNuevoAction() {
		TableListener<Element> tableListener = new TableListener<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(final TableEvent<Element> tableEvent) {
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
		return new TableAction<Element>(this.getTablePanel().getTable(), tableListener, buttonDecorator);
	}

	protected TableAction<Element> getBorrarAction() {
		TableListener<Element> tableListener = new TableListener<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(final TableEvent<Element> tableEvent) {
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
		return new TableAction<Element>(this.getTablePanel().getTable(), tableListener, buttonDecorator) {
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

	protected TableAction<Element> getLimpiarAction() {
		TableListener<Element> tableListener = new TableListener<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(final TableEvent<Element> tableEvent) {
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
		return new TableAction<Element>(this.getTablePanel().getTable(), tableListener, buttonDecorator);
	}

	protected TableAction<Element> getCargarAction() {
		TableListener<Element> tableListener = new TableListener<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(final TableEvent<Element> tableEvent) {
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
		return new TableAction<Element>(this.getTablePanel().getTable(), tableListener, buttonDecorator);
	}

	protected TableAction<Element> getVerAction() {
		TableListener<Element> tableListener = new TableListener<Element>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(final TableEvent<Element> tableEvent) {
				StringBuffer buffer = new StringBuffer();
				Collection<Element> elements = tableEvent.getSelectedEntities();
				for (Element element : elements) {
					buffer.append(element.toString());
					buffer.append("\n");
				}
				if (buffer.toString().length() != 0) {
					Notificaction.showMessage(ElementTableForm.this, buffer.toString());
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
		return new TableAction<Element>(this.getTablePanel().getTable(), tableListener, buttonDecorator);
	}

	public void setService(ElementServiceImpl service) {
		this.service = service;
	}
}