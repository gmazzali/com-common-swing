package com.common.swing.view.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.common.swing.view.component.table.BaseTable;
import com.common.util.business.tool.collection.CollectionUtil;

/**
 * Permite definir un evento de la tabla.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de elementos que contiene la tabla que disparo el evento.
 */
public class TableEvent<E extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * La tabla que disparo el evento.
	 */
	private BaseTable<E> table;
	/**
	 * Las entidades que estan dentro del evento.
	 */
	private Collection<E> entities;

	/**
	 * Crea un evento de la tabla.
	 * 
	 * @param table
	 *            La tabla que disparo el evento.
	 */
	public TableEvent(BaseTable<E> table) {
		this.table = table;
		this.entities = new ArrayList<E>();
	}

	/**
	 * Permite cargar una nueva entidad dentro del evento.
	 * 
	 * @param entity
	 *            La entidad que vamos a cargar.
	 * @return Este mismo objeto.
	 */
	public TableEvent<E> addEntity(E entity) {
		if (entity != null) {
			this.entities.add(entity);
		}
		return this;
	}

	/**
	 * Permite cargar una lista de entidades dentro del evento.
	 * 
	 * @param entities
	 *            La lista de entidades que vamos a cargar.
	 * @return Este mismo objeto.
	 */
	public TableEvent<E> addAllEntity(Collection<E> entities) {
		if (CollectionUtil.isNotEmpty(entities)) {
			this.entities.addAll(entities);
		}
		return this;
	}

	/**
	 * Retorna las entidades del evento.
	 * 
	 * @return Las entidades.
	 */
	public Collection<E> getEntities() {
		return entities;
	}

	/**
	 * Permite cargar las entidades del evento.
	 * 
	 * @param entities
	 *            Las entidades del evento.
	 */
	public void setEntities(Collection<E> entities) {
		this.entities = entities;
	}

	/**
	 * Retorna la tabla que disparo el evento.
	 * 
	 * @return La tabla que disparo el evento.
	 */
	public BaseTable<E> getTable() {
		return table;
	}
}