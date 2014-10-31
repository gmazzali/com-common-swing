package com.common.swing.domain.model;

/**
 * La clase de pruebas.
 * 
 * @since 31/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum Sexo {

	MASCULINO("M"), FEMENINO("F");

	private String descripcion;

	private Sexo(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return this.getDescripcion();
	}

	public String getDescripcion() {
		return descripcion;
	}
}