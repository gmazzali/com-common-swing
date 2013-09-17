package com.common.swing.crud.list;

import java.io.Serializable;
import java.util.List;

import com.common.swing.crud.Form;
import com.common.swing.crud.edit.EntityEditFormContainer;
import com.common.util.model.Persistence;
import com.common.util.service.GenericService;

/**
 * La interfaz que nos permite definir un formulario donde vamos a desplegar un listado de entidades que queremos mostrar.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de las entidades que vamos a mostrar en el listado.
 * @param <PK>
 *            La clase que utilizamos para identificar las entidades a mostrar.
 */
public interface EntityListForm<E extends Persistence<PK>, PK extends Serializable> extends Form {

	/**
	 * La función encargada de cargar el listado de la entidades que queremos mostrar dentro de este formulario.
	 * 
	 * @param entities
	 *            Las entidades que queremos desplegar dentro de este formulario. Si este listado es nulo, permitirá vaciar el listado que actualmente
	 *            tenemos desplegado.
	 */
	public void updateEntityList(List<E> entities);

	/**
	 * La función encargada de retornar el contenedor de la ventana donde tenemos el formulario de edición de entidades para poder dar de alta una
	 * nueva o modificar una ya existente.
	 * 
	 * @return El contenedor del formulario de edición de entidades.
	 */
	public EntityEditFormContainer<E, PK> getEntityEditFormContainer();

	/**
	 * La función encargada de retornar el servicio que vamos a utilizar para la entidad que tenemos en el panel.
	 * 
	 * @return El servicio para la entidad que tenemos en el panel.
	 */
	public GenericService<E, PK> getEntityService();

	/**
	 * La función encargada de retornar un listado de títulos que permite cargar los nombres de las columnas que vamos a desplegar dentro de la
	 * ventana de listado de entidades. El orden del listado es el orden en el que van a desplegarse las mismas.
	 * 
	 * @return El listado de los títulos que vamos a desplegar dentro de la tabla de entidades.
	 */
	public String[] getColumnsTitles();

	/**
	 * La función encargada de retornar un listado de valores enteros que permite cargar los anchos a las columnas
	 * 
	 * @return Los tamaños proporcionales de las columnas de la tabla.
	 */
	public Integer[] getColumnsWidths();

	/**
	 * La función encargada de convertir una entidad de las que tenemos dentro del listado en una fila de la tabla.
	 * 
	 * @param entity
	 *            La entidad que vamos a convertir.
	 * @return La fila que vamos a insertar dentro de la tabla.
	 */
	public String[] converterEntityToRow(E entity);

	/**
	 * La función encargada de retornar una nueva entidad que vamos a crear dentro del listado.
	 * 
	 * @return Una entidad nueva para almacenar.
	 */
	public E getNewEntity();

	/**
	 * La función encargada de realizar un clonado de la entidad que queremos modificar para no editar la que originalmente tenemos dentro de este
	 * listado.
	 * 
	 * @param entity
	 *            La entidad que queremos clonar.
	 * @return La entidad clonada.
	 */
	public E getCloneEntity(E entity);

	/**
	 * La función que retorna el mensaje del botón para agregar una nueva entidad al conjunto que ya tenemos dentro del sistema.
	 * 
	 * @return El mensaje del botón de agregado de entidades.
	 */
	public String getNewButtonCaption();

	/**
	 * La función que retorna el mensaje del botón para editar una entidad del conjunto que ya tenemos dentro del sistema.
	 * 
	 * @return El mensaje del botón de editado de entidades.
	 */
	public String getModifyButtonCaption();

	/**
	 * La función que retorna el mensaje del botón para eliminar una entidad del conjunto que ya tenemos dentro del sistema.
	 * 
	 * @return El mensaje del botón de borrado de entidades.
	 */
	public String getDeleteButtonCaption();

	/**
	 * La función que retorna el label del botón para cerrar la ventana que despliega el listado.
	 * 
	 * @return El label del botón de cierre.
	 */
	public String getCloseButtonCaption();
}
