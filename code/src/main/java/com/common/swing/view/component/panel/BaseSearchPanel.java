package com.common.swing.view.component.panel;

import java.awt.Dimension;

import javax.annotation.PostConstruct;
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
public abstract class BaseSearchPanel<B extends SearchBean> extends JPanel implements BasePanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Permite inicializar los componentes del panel.
	 */
	@PostConstruct
	protected void init() {
		this.removeAll();
		this.setPreferredSize(new Dimension(this.getWidthSize(), this.getHeightSize()));
		this.afterInit();
	}

	/**
	 * Permite vaciar el contenido del filtro de búsqueda.
	 */
	public void clearFilter() {
		this.emptyFields();
	}

	/**
	 * Permite terminar de configurar el panel.
	 */
	protected abstract void afterInit();

	/**
	 * Se encarga de validar y crear el filtro de busqueda de acuerdo a los criterios que tenemos cargado dentro del filtro del panel.
	 * 
	 * @return El filtro para la busqueda de entidades.
	 * @throws SwingException
	 *             En caso de que algún parámetro del filtro no cumpla las condiciones necesarias.
	 */
	public abstract B getFilter() throws SwingException;
}