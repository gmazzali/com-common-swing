package com.common.swing.domain.model.crud.view;

import java.io.Serializable;

import com.common.swing.domain.model.crud.Form;
import com.common.util.domain.model.Persistence;

/**
 * La interfaz que nos permite definir un formulario para la visualización de un elemento.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que establece las entidades que vamos a visualizar dentro de este formulario.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a visualizar.
 */
public interface EntityViewForm<E extends Persistence<PK>, PK extends Serializable> extends Form<E> {

	/**
	 * Se encarga de crear un formulario para la visualización de una entidad.
	 * 
	 * @param entity
	 *            La entidad que vamos a visualizar dentro de esta ventana.
	 * @return El formulario configurada para visualizar una entidad.
	 */
	public EntityViewForm<E, PK> createViewForm(E entity);

	/**
	 * Se encarga de cargar los campos de la ventana de acuerdo a los valores de los atributos de la entidad que queremos visualizar.
	 */
	public void fromEntityToFields();
}