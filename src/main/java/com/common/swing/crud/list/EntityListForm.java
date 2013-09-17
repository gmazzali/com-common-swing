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
	 * La funci�n encargada de cargar el listado de la entidades que queremos mostrar dentro de este formulario.
	 * 
	 * @param entities
	 *            Las entidades que queremos desplegar dentro de este formulario. Si este listado es nulo, permitir� vaciar el listado que actualmente
	 *            tenemos desplegado.
	 */
	public void updateEntityList(List<E> entities);

	/**
	 * La funci�n encargada de retornar el contenedor de la ventana donde tenemos el formulario de edici�n de entidades para poder dar de alta una
	 * nueva o modificar una ya existente.
	 * 
	 * @return El contenedor del formulario de edici�n de entidades.
	 */
	public EntityEditFormContainer<E, PK> getEntityEditFormContainer();

	/**
	 * La funci�n encargada de retornar el servicio que vamos a utilizar para la entidad que tenemos en el panel.
	 * 
	 * @return El servicio para la entidad que tenemos en el panel.
	 */
	public GenericService<E, PK> getEntityService();

	/**
	 * La funci�n encargada de retornar un listado de t�tulos que permite cargar los nombres de las columnas que vamos a desplegar dentro de la
	 * ventana de listado de entidades. El orden del listado es el orden en el que van a desplegarse las mismas.
	 * 
	 * @return El listado de los t�tulos que vamos a desplegar dentro de la tabla de entidades.
	 */
	public String[] getColumnsTitles();

	/**
	 * La funci�n encargada de retornar un listado de valores enteros que permite cargar los anchos a las columnas
	 * 
	 * @return Los tama�os proporcionales de las columnas de la tabla.
	 */
	public Integer[] getColumnsWidths();

	/**
	 * La funci�n encargada de convertir una entidad de las que tenemos dentro del listado en una fila de la tabla.
	 * 
	 * @param entity
	 *            La entidad que vamos a convertir.
	 * @return La fila que vamos a insertar dentro de la tabla.
	 */
	public String[] converterEntityToRow(E entity);

	/**
	 * La funci�n encargada de retornar una nueva entidad que vamos a crear dentro del listado.
	 * 
	 * @return Una entidad nueva para almacenar.
	 */
	public E getNewEntity();

	/**
	 * La funci�n encargada de realizar un clonado de la entidad que queremos modificar para no editar la que originalmente tenemos dentro de este
	 * listado.
	 * 
	 * @param entity
	 *            La entidad que queremos clonar.
	 * @return La entidad clonada.
	 */
	public E getCloneEntity(E entity);

	/**
	 * La funci�n que retorna el mensaje del bot�n para agregar una nueva entidad al conjunto que ya tenemos dentro del sistema.
	 * 
	 * @return El mensaje del bot�n de agregado de entidades.
	 */
	public String getNewButtonCaption();

	/**
	 * La funci�n que retorna el mensaje del bot�n para editar una entidad del conjunto que ya tenemos dentro del sistema.
	 * 
	 * @return El mensaje del bot�n de editado de entidades.
	 */
	public String getModifyButtonCaption();

	/**
	 * La funci�n que retorna el mensaje del bot�n para eliminar una entidad del conjunto que ya tenemos dentro del sistema.
	 * 
	 * @return El mensaje del bot�n de borrado de entidades.
	 */
	public String getDeleteButtonCaption();

	/**
	 * La funci�n que retorna el label del bot�n para cerrar la ventana que despliega el listado.
	 * 
	 * @return El label del bot�n de cierre.
	 */
	public String getCloseButtonCaption();
}
