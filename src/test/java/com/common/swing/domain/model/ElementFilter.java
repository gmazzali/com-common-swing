package com.common.swing.domain.model;

import com.common.util.persistence.filter.BaseFilter;

/**
 * La clase de pruebas del filtro.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementFilter extends BaseFilter<Element, Long> {
	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}