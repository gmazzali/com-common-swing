package com.common.swing.crud;

import com.common.util.model.filter.Filter;

/**
 * La interfaz que define un bean de busqueda para utilizar de filtro en la busqueda de las entidades y desplegarlas dentro de una tabla.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface SearchBean {

	/**
	 * La función encargada de inicializar el bean de busqueda, dejando al mismo con los valores de sus atributos vacios.
	 */
	public void initialize();

	/**
	 * La función encargada de crear el filtro que vamos a utilizar para poder recuperar solo aquellas entidades que queremos desde la base de datos.
	 * 
	 * @return El filtro con los valores cargados para utilziarlos y recuperar solo las entidades que queremos.
	 */
	public Filter createFilter();
}
