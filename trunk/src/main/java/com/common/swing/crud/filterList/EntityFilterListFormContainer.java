package com.common.swing.crud.filterList;

import java.io.Serializable;

import com.common.swing.crud.filter.EntityFilterFormContainer;
import com.common.swing.crud.list.EntityListFormContainer;
import com.common.util.model.Persistence;

/**
 * La interfaz que define el comportamiento de un contenedor de un formulario de filtrado y de uno que despliega un listado de entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            Las clases de las entidades que vamos a filtrar y listar dentro del contenedor.
 * @param <PK>
 *            Las clases que representan los identificadores de las entidades.
 */
public interface EntityFilterListFormContainer<E extends Persistence<PK>, PK extends Serializable> extends EntityListFormContainer<E, PK>,
		EntityFilterFormContainer<E, PK> {
}