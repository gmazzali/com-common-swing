package com.common.swing.view.component.list.formatter;

import java.awt.Component;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.view.bean.RowBean;

/**
 * 
 * @author gmazzali
 * 
 * @param <B>
 */
public class PropertyCellFormatter<B extends RowBean> extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;

	/**
	 * La propiedad que vamos a cargar en la celda.
	 */
	protected Field property;

	/**
	 * El constructor del formateador de la celda que recibe la propiedad que vamos a desplegar.
	 * 
	 * @param clazz
	 *            La clase de elementos que vamos a manejar.
	 * @param property
	 *            La propiedad que vamos a desplegar.
	 */
	public PropertyCellFormatter(String property) {
		try {
			Class<?> actualType = (Class<?>) ((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
			this.property = actualType.getDeclaredField(property);
		} catch (Exception ex) {
			throw new SwingException(ex);
		}
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		Component component;
		try {
			component = super.getListCellRendererComponent(list, this.property.get(value), index, isSelected, cellHasFocus);
		} catch (Exception e) {
			component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			e.printStackTrace();
		}
		return component;
	}
}