package com.common.swing.domain.model.crud.filter.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.JPanel;

import com.common.swing.domain.model.crud.filter.FilterForm;
import com.common.util.domain.model.Persistence;

/**
 * La clase que implementa la interfaz que nos permite definir los formularios de filtrado para la búsqueda de entidades dentro de un sistema.
 * 
 * @since 01/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de las entidades que vamos a filtrar.
 * @param <PK>
 *            La clase que utilizamos para identificar las entidades filtradas.
 */
public abstract class FilterFormImpl<E extends Persistence<PK>, PK extends Serializable> extends JPanel implements FilterForm<E> {
	private static final long serialVersionUID = 1L;

	// private static final Logger log = Logger.getLogger(FilterFormImpl.class);

	@Override
	public Collection<E> filter() {
		// TODO Hacer lo del filtrado.
		return null;
	}
}