package com.common.swing.domain.model.crud.list;

import java.io.Serializable;

import com.common.swing.domain.model.crud.FormContainer;
import com.common.util.model.Persistence;

/**
 * La interfaz que nos permite definir el comportamiento para un contenedor de formularios de listado de entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            Las clases de las entidades que vamos a listar dentro del contenedor.
 * @param <PK>
 *            Las clases que representan los identificadores de las entidades.
 */
public interface EntityListFormContainer<E extends Persistence<PK>, PK extends Serializable> extends FormContainer {

	/**
	 * La función que retornar el título que vamos a colocarle a este contenedor de formularios.
	 * 
	 * @return El título de este contenedor de formulario.
	 */
	public String getListContainerTitle();

	/**
	 * La función encargada de actualizar el formulario del listado de la entidades.
	 */
	public void updateEntityListForm();

	/**
	 * La función encargada de retornar el panel donde va a desplegarse el listado de las entidades que seleccionamos en el sistema.
	 * 
	 * @return El panel que lista las entidades que tenemos dentro del sistema.
	 */
	public EntityListForm<E, PK> getEntityListForm();
}
