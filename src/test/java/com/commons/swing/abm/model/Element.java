package com.commons.swing.abm.model;

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
	private String name;

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "[name=" + this.name + "]";
	}

	/**
	 * La función encargada de setear el nombre de la entidad.
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
