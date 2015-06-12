package com.common.swing.view.component.list.formatter;

import java.awt.Component;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import org.apache.log4j.Logger;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.view.bean.RowBean;

/**
 * La clase que permite definir un formateador del bean que vamos a desplegar dentro de una lista.
 * 
 * @since 10/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <B>
 *            El bean de tabla que vamos a manipular dentro de este formateador.
 */
public abstract class PropertyCellFormatter<B extends RowBean> extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(PropertyCellFormatter.class);

	/**
	 * La propiedad que vamos a cargar en la celda.
	 */
	private String property;
	/**
	 * El getter de la propiedad.
	 */
	protected Method getterProperty;

	/**
	 * El constructor del formateador de la celda que recibe la propiedad que vamos a desplegar.
	 * 
	 * @param property
	 *            La propiedad que vamos a desplegar.
	 */
	public PropertyCellFormatter(String property) {
		try {
			Class<?> type = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			this.property = property;
			String getterName = "get" + this.property.substring(0, 1).toUpperCase() + this.property.substring(1);
			this.getterProperty = type.getDeclaredMethod(getterName);
			this.getterProperty.setAccessible(true);
		} catch (Exception ex) {
			throw new SwingException(ex);
		}
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		Component component;
		try {
			component = super.getListCellRendererComponent(list, this.getterProperty.invoke(value), index, isSelected, cellHasFocus);
		} catch (Exception e) {
			component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			LOGGER.warn("Error in the getter of the propery: " + this.property, e);
		}
		return component;
	}
}