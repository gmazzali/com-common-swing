package com.common.swing.crud;

/**
 * La interfaz que define el comportamiento com�n para los formularios de entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface Form {

	/**
	 * La funci�n encargada de retornar el container que mantiene este formulario.
	 * 
	 * @return El container que contiene este formulario.
	 */
	public FormContainer getFormContainer();

	/**
	 * La funci�n encargada de retornar el titulo que vamos a colocarle a este formulario.
	 * 
	 * @return El titulo que vamos a cargarle a este formulario.
	 */
	public String getFormTitle();

	/**
	 * La funci�n que nos permite habilitar o deshabilitar un formulario.
	 * 
	 * @param enabled
	 *            El valor booleano que nos permite saber si debemos habilitar o deshabilitar el formulario.
	 */
	public void setEnabled(boolean enabled);

	/**
	 * La funci�n encargada de reiniciar los campos de b�squeda para comenzar con una b�squeda desde el comienzo.
	 */
	public void emptyFields();

	/**
	 * La funci�n encargada de retornar el alto del formulario.
	 * 
	 * @return El alto del formulario que tenemos (en pixeles).
	 */
	public Integer getHeightSize();

	/**
	 * La funci�n encargada de retornar el ancho del formulario.
	 * 
	 * @return El ancho del formulario que tenemos (en pixeles).
	 */
	public Integer getWidthSize();

	/**
	 * La funci�n que retorna un valor booleano que nos permite saber si el formulario, una vez que guarda la entidad dentro de la base de datos, va a
	 * cerrar la ventana contenedora de este formulario o no.
	 * 
	 * @return TRUE en caso de que sea necesario cerrar el contenedor del formulario de edici�n de entidades, en caso de que no sea necesario cerrarlo
	 *         se retorna FALSE.
	 */
	public Boolean isContainerCloseable();
}
