package com.common.swing.crud.model;

import org.springframework.beans.BeanUtils;

import com.common.util.model.Entity;

/**
 * La clase de pruebas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Element extends Entity<Integer> {

	/**
	 * Elemento.
	 */
	private String name = "";

	@Override
	public String toString() {
		return super.toString() + "[name=" + this.name + "]";
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
