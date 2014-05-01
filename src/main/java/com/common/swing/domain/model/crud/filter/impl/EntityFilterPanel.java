package com.common.swing.domain.model.crud.filter.impl;

import java.io.Serializable;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.common.swing.domain.model.crud.filter.EntityFilterForm;
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
public abstract class EntityFilterPanel<E extends Persistence<PK>, PK extends Serializable> extends JPanel implements EntityFilterForm<E> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(EntityFilterPanel.class);
}