package com.common.swing.component;

import java.util.Collection;

import javax.swing.JList;

public class ExtendedJList<E> extends JList<E> {

	private static final long serialVersionUID = -729398773815975401L;

	protected Collection<E> elements;

	public void addElement(E element) {
		this.elements.add(element);
	}
}
