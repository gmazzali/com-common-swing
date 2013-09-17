package com.common.swing.crud.filter;

import java.io.Serializable;

import com.common.swing.crud.Form;
import com.common.util.model.Persistence;

/**
 * La interfaz que nos permite definir los formularios de búsqueda de entidades para poder cargarlas dentro de un listado de ellas recuperadas desde
 * la base de datos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de las entidades que vamos a recuperar desde la base de datos.
 * @param <PK>
 *            La clase que utilizamos para identificar las entidades a recuperar.
 */
public interface EntityFilterForm<E extends Persistence<PK>, PK extends Serializable> extends Form {

	/**
	 * La función encargada de realizar la búsqueda de las entidades filtradas por los criterios cargados en este elemento y los carga dentro de un
	 * listado de entidades para luego desplegarse.
	 */
	public void search();
}