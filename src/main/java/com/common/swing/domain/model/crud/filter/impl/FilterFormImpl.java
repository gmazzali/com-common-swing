package com.common.swing.domain.model.crud.filter.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.common.swing.domain.model.crud.filter.FilterForm;
import com.common.util.domain.model.Persistence;
import com.common.util.persistence.filter.BaseFilter;

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
public abstract class FilterFormImpl<E extends Persistence<PK>, PK extends Serializable> extends JPanel implements FilterForm<E, PK> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(FilterFormImpl.class);

	@Override
	public Collection<E> filter() {
		BaseFilter<PK> filter = this.createFilter();
		log.debug(filter);

		return this.getService().findByFilter(filter);
	}

	/**
	 * Se encarga de crear el filtro de busqueda de acuerdo a los criterios que tenemos cargado dentro del filtro del panel.
	 * 
	 * @return El filtro para la busqueda de entidades.
	 */
	protected abstract BaseFilter<PK> createFilter();
}