package com.crud.swing.view.list.impl;

import java.util.Collection;

import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import com.common.swing.view.action.TableAction;
import com.common.swing.view.component.table.BaseTable;
import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.TableEvent;
import com.common.swing.view.listener.TableListener;
import com.common.swing.view.notification.Notificaction;
import com.common.util.business.tool.collection.CollectionUtil;
import com.common.util.persistence.filter.BaseFilter;
import com.crud.swing.model.Element;
import com.crud.swing.model.ElementServiceImpl;
import com.crud.swing.model.ElementTable;

/**
 * La clase que extiende el formulario de listado de entidades.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementListForm extends ListForm<Element> {
	private static final long serialVersionUID = 1L;

	private ElementServiceImpl service;

	@Override
	public Integer getHeightSize() {
		return 400;
	}

	@Override
	public Integer getWidthSize() {
		return 500;
	}

	@Override
	protected BaseTable<Element> createTable() {
		return new ElementTable();
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
	protected void afterInit() {
		this.table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Collection<TableAction<Element>> getTableActions() {
		TableListener<Element> tableListener1 = new TableListener<Element>() {

			@Override
			public void fireEvent(final TableEvent<Element> tableEvent) {
				new Thread() {
					public void run() {
						setEnabled(false);
						service.save(new Element("ELEMENTO " + (int) (Math.random() * 1000)));
						setEntities(service.findByFilter(new BaseFilter<Element, Integer>()));
						setEnabled(true);
					};
				}.start();
			};
		};

		ButtonDecorator buttonDecorator1 = new ButtonDecorator() {

			@Override
			public void decorateButton(JButton button) {
				button.setText("AGREGAR");
			}
		};
		TableListener<Element> tableListener2 = new TableListener<Element>() {

			@Override
			public void fireEvent(final TableEvent<Element> tableEvent) {
				new Thread() {
					public void run() {
						setEnabled(false);
						for (Element element : tableEvent.getEntities()) {
							service.delete(element);
						}
						setEntities(service.findByFilter(new BaseFilter<Element, Integer>()));
						setEnabled(true);
					};
				}.start();
			}
		};
		ButtonDecorator buttonDecorator2 = new ButtonDecorator() {

			@Override
			public void decorateButton(JButton button) {
				button.setText("BORRAR");
			}
		};
		TableListener<Element> tableListener3 = new TableListener<Element>() {

			@Override
			public void fireEvent(final TableEvent<Element> tableEvent) {
				emptyFields();
			}
		};
		ButtonDecorator buttonDecorator3 = new ButtonDecorator() {

			@Override
			public void decorateButton(JButton button) {
				button.setText("LIMPIAR");
			}
		};
		TableListener<Element> tableListener4 = new TableListener<Element>() {

			@Override
			public void fireEvent(final TableEvent<Element> tableEvent) {
				setEntities(service.findByFilter(new BaseFilter<Element, Integer>()));
			}
		};
		ButtonDecorator buttonDecorator4 = new ButtonDecorator() {

			@Override
			public void decorateButton(JButton button) {
				button.setText("CARGAR");
			}
		};
		TableListener<Element> tableListener5 = new TableListener<Element>() {

			@Override
			public void fireEvent(final TableEvent<Element> tableEvent) {
				StringBuffer buffer = new StringBuffer();
				for (Element element : tableEvent.getEntities()) {
					buffer.append(element.toString());
					buffer.append("\n");
				}
				if (buffer.toString().length() != 0) {
					Notificaction.showMessage(ElementListForm.this, buffer.toString());
				}
			}
		};
		ButtonDecorator buttonDecorator5 = new ButtonDecorator() {

			@Override
			public void decorateButton(JButton button) {
				button.setText("VER");
			}
		};

		return CollectionUtil.newArrayList(new TableAction<Element>(table, tableListener1, buttonDecorator1),
				new TableAction<Element>(table,	tableListener2, buttonDecorator2) {
					private static final long serialVersionUID = 1L;
		
					@Override
					public boolean isVivibleAction(Collection<Element> entities) {
						boolean permiteBorrar = true;
						for (Element element : entities) {
							if (element.getFecha() != null) {
								permiteBorrar = false;
								break;
							}
						}
						return permiteBorrar;
					}
				}, 
				new TableAction<Element>(table, tableListener3, buttonDecorator3), 
				new TableAction<Element>(table, tableListener4, buttonDecorator4),
				new TableAction<Element>(table, tableListener5, buttonDecorator5));
	}

	public void setService(ElementServiceImpl service) {
		this.service = service;
	}
}