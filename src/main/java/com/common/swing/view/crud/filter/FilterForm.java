package com.common.swing.view.crud.filter;

import java.io.Serializable;
import java.util.Collection;

import com.common.swing.view.crud.BaseForm;
import com.common.util.business.service.BaseService;
import com.common.util.domain.model.Persistence;

/**
 * La interfaz que nos permite definir los formularios de filtrado para la búsqueda de entidades dentro de un sistema.
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
public interface FilterForm<E extends Persistence<PK>, PK extends Serializable> extends BaseForm {

	/**
	 * Se encarga de retornar el servicio que vamos a utilizar para la entidad que tenemos en el panel.
	 * 
	 * @return El servicio para la entidad que tenemos en el panel.
	 */
	public BaseService<E, PK> getService();

	/**
	 * Se encarga de realizar la búsqueda de las entidades que tenemos dentro del sistema de acuerdo a los criterios seleccionados dentro del filtro.
	 * 
	 * @return La colección de los elementos que recuperamos dentro del sistema.
	 */
	public Collection<E> filter();
}