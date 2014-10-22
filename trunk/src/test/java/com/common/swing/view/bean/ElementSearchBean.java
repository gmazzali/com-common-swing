package com.common.swing.view.bean;

import com.common.swing.view.bean.SearchBean;

/**
 * El bean de búsqueda de los elementos.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementSearchBean extends SearchBean {
	private static final long serialVersionUID = 1L;

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}