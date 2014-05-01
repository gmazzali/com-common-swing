package com.common.swing.domain.model.crud.model;

import org.springframework.beans.BeanUtils;

import com.common.util.domain.model.Entity;

/**
 * La clase de pruebas.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Element extends Entity<Integer> {
	private static final long serialVersionUID = 1L;

	private String name = "";

	@Override
	public String toString() {
		return "Element [name=" + this.name + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Element target = new Element();
		Element source = this;

		BeanUtils.copyProperties(source, target);

		return target;
	}

	/**
	 * La función encargada de cargar el nombre de la entidad.
	 * 
	 * @param name
	 *            El atributo que vamos a usar.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * La función encargada de retornar el atributo.
	 * 
	 * @return El atributo.
	 */
	public String getName() {
		return this.name;
	}
}