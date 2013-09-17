package com.common.swing.crud.filterList;

import java.io.Serializable;

import com.common.swing.crud.filter.EntityFilterForm;
import com.common.swing.crud.list.EntityListForm;
import com.common.util.model.Persistence;

/**
 * La interfaz que define el comportamiento de un formulario de filtrado de entidades y de despliegue de listado.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            Las clases de las entidades que vamos a filtrar y listar dentro del formulario.
 * @param <PK>
 *            Las clases que representan los identificadores de las entidades.
 */
public interface EntityFilterListForm<E extends Persistence<PK>, PK extends Serializable> extends EntityFilterForm<E, PK>, EntityListForm<E, PK> {
}