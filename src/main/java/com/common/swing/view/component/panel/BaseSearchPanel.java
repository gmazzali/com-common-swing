package com.common.swing.view.component.panel;

import javax.swing.JPanel;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.view.bean.SearchBean;

/**
 * La clase que permite definir un panel donde vamos a desplegar un filtro para la búsqueda de datos.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <B>
 *            El bean de búsqueda que vamos a manipular dentro de este panel.
 */
public abstract class BaseSearchPanel<B extends SearchBean> extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * El constructor de un panel de filtro de búsqueda.
	 */
	public BaseSearchPanel() {
		this.init();
	}

	/**
	 * Permite inicializar el panel.
	 */
	protected abstract void init();

	/**
	 * Permite vaciar el contenido del filtro de búsqueda.
	 */
	public abstract void clearFilter();

	/**
	 * Se encarga de validar y crear el filtro de busqueda de acuerdo a los criterios que tenemos cargado dentro del filtro del panel.
	 * 
	 * @return El filtro para la busqueda de entidades.
	 * @throws SwingException
	 *             En caso de que algún parámetro del filtro no cumpla las condiciones necesarias.
	 */
	public abstract B getFilter() throws SwingException;
}