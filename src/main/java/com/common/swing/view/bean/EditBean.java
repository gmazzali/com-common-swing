package com.common.swing.view.bean;

import com.common.util.domain.model.entity.Persistence;

/**
 * Permite definir un bean para un panel de edición.
 * 
 * @since 23/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface EditBean<P extends Persistence<?>> extends BaseBean {

	/**
	 * Permite inicializar todos los campos que vamos a desplegar en el formulario de edición.
	 */
	public void initialize();

	/**
	 * Permite recuperar la entidad que corresponde a este bean de edición.
	 * 
	 * @return La entidad que corresponde al bean de edición.
	 */
	public P getEntity();
}