package com.common.swing.view.component.table.formatter.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.JComboBox;

import com.common.util.business.tool.collection.CollectionUtil;

/**
 * La clase para los formateadores de las celdas de tipo listas.
 * 
 * @see BaseCellFormatter
 * 
 * @since 31/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class EnumCellFormatter<E extends Serializable> extends BaseCellFormatter<JComboBox<E>> {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de un formateador de celdas de listas.
	 */
	public EnumCellFormatter() {
		this((E[]) null);
	}

	/**
	 * Constructor de un formateador de celdas de listas.
	 * 
	 * @param elements
	 *            La lista de elementos que vamos a desplegar dentro del combo de la celda.
	 */
	@SafeVarargs
	public EnumCellFormatter(E... elements) {
		this(elements, null, null);
	}

	/**
	 * Constructor de un formateador de celdas de listas.
	 * 
	 * @param elements
	 *            La lista de elementos que vamos a desplegar dentro del combo de la celda.
	 * @param horizontalAlignment
	 *            La alineación horizontal del componente de visualización.
	 * @param verticalAlignment
	 *            La alineación vertical del componente de visualización.
	 */
	public EnumCellFormatter(E[] elements, Integer horizontalAlignment, Integer verticalAlignment) {
		super(new JComboBox<E>(), horizontalAlignment, verticalAlignment);
		this.setComboBoxData(this.componentEditor, CollectionUtil.newArrayList(elements));
	}

	/**
	 * Permite cargarle al combo box los datos a partir de una colección.
	 * 
	 * @param componentEditor
	 *            El combo de edición que vamos a desplegar dentro de la celda.
	 * @param elements
	 *            El listado de los elementos que vamos a cargar dentro del combo.
	 */
	public void setComboBoxData(JComboBox<E> componentEditor, Collection<E> elements) {
		componentEditor.removeAllItems();
		if (CollectionUtil.isNotEmpty(elements)) {
			for (E e : elements) {
				componentEditor.addItem(e);
			}
		}
		componentEditor.setSelectedIndex(-1);
	}

	@Override
	public Object format(Object beanValue) {
		return beanValue != null ? beanValue.toString() : null;
	}

	@Override
	public Object parse(JComboBox<E> editComponent) {
		return editComponent.getSelectedItem();
	}

	@Override
	public void setValueToEditComponent(Object beanValue, JComboBox<E> editComponent) {
		editComponent.setSelectedItem(beanValue);
	}
}