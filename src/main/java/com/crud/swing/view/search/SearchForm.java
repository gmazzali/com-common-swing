package com.crud.swing.view.search;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JPanel;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.view.action.SearchAction;
import com.common.swing.view.bean.SearchBean;
import com.common.swing.view.callback.CallbackFilter;
import com.common.swing.view.component.panel.BaseSearchPanel;
import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.SearchEvent;
import com.common.swing.view.listener.SearchListener;
import com.common.swing.view.notification.Notificaction;
import com.common.util.business.service.BaseService;
import com.common.util.business.tool.collection.CollectionUtil;
import com.common.util.domain.model.Persistence;
import com.common.util.persistence.filter.BaseFilter;
import com.crud.swing.view.BaseForm;

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
public abstract class SearchForm<E extends Persistence<PK>, PK extends Serializable> extends JPanel implements BaseForm {
	private static final long serialVersionUID = 1L;

	/**
	 * El panel de filtro de búsqueda.
	 */
	private BaseSearchPanel<? extends SearchBean> baseSearchPanel;
	/**
	 * El liestado de las acciones del filtro de búsqueda.
	 */
	private Collection<SearchAction<E>> searchActions;
	/**
	 * Todos los callback de actualización del filtro de búsqueda.
	 */
	private Collection<CallbackFilter<E>> callbackFilters;
	/**
	 * El elemento de exclusividad de la busqueda.
	 */
	private Object searchMutex = new Object();

	/**
	 * Permite crear un panel de filtrado.
	 */
	public SearchForm() {
		this.callbackFilters = new ArrayList<CallbackFilter<E>>();
		this.init();
	}

	/**
	 * Inicializa el panel de filtrado.
	 */
	private void init() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(this.getWidthSize(), this.getHeightSize()));

		this.baseSearchPanel = this.createSearchPanel();
		this.add(baseSearchPanel, BorderLayout.CENTER);

		this.searchActions = this.getdefaultFilterActions();
		Collection<? extends SearchAction<E>> temporal = this.getFilterActions();
		if (CollectionUtil.isNotEmpty(temporal)) {
			this.searchActions.addAll(temporal);
		}
		if (CollectionUtil.isNotEmpty(this.searchActions)) {
			this.initFilterActionPanel(this);
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.baseSearchPanel.setEnabled(enabled);
		for (SearchAction<E> filterAction : this.searchActions) {
			filterAction.getButton().setEnabled(enabled);
		}
	}

	@Override
	public void enabled() {
		this.setEnabled(true);
	}

	@Override
	public void disabled() {
		this.setEnabled(false);
	}

	@Override
	public void emptyFields() {
		if (this.baseSearchPanel != null) {
			this.baseSearchPanel.clearFilter();
		}
		synchronized (searchMutex) {
			for (CallbackFilter<E> callbackFilter : callbackFilters) {
				callbackFilter.updateEntities(new ArrayList<E>());
			}
		}
	}

	/**
	 * Permite cargar el panel de las acciones del filtro de búsqueda.
	 * 
	 * @param panel
	 *            El panel donde va a cargarse el panel de acciones del filtro de búsqueda.
	 */
	protected void initFilterActionPanel(JPanel panel) {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		for (SearchAction<E> filterAction : this.searchActions) {
			buttonPanel.add(filterAction.createButton());
		}
		panel.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Permite crear las acciones del filtro que tenemos por omisión. Estas son la de busqueda y la de limpiar el filtro de búsqueda.
	 * 
	 * @return Las acciones por omisión del filtro de búsqueda.
	 */
	@SuppressWarnings("unchecked")
	protected Collection<SearchAction<E>> getdefaultFilterActions() {
		SearchListener<E> searchFilterListener = new SearchListener<E>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(SearchEvent<E> filterEvent) {
				search();
			}
		};
		SearchListener<E> clearFilterFilterListener = new SearchListener<E>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(SearchEvent<E> filterEvent) {
				emptyFields();
			}
		};

		return CollectionUtil.newArrayList(new SearchAction<E>(searchFilterListener, this.getSearchButtonDecorator()), new SearchAction<E>(
				clearFilterFilterListener, this.getCleanFilterButtonDecorator()));
	}

	/**
	 * Permite realizar la búsqueda y cargarlas dentro de los callback.
	 */
	protected void search() {
		new Thread() {
			@Override
			public void run() {
				synchronized (searchMutex) {
					try {
						disabled();
						Collection<E> entities = getService().findByFilter(createFilter(baseSearchPanel.getFilter()));
						for (CallbackFilter<E> callbackFilter : callbackFilters) {
							callbackFilter.updateEntities(entities);
						}
					} catch (SwingException e) {
						Notificaction.showErrorMessage(SearchForm.this, e.getErrors());
					} finally {
						enabled();
					}
				}
			}
		}.start();
	}

	/**
	 * Permite limpiar la lista de callbacks de filtro a los que tenemos.
	 */
	public void clearCallbackFilters() {
		this.callbackFilters.clear();
	}

	/**
	 * Permite agregar un nuevo callback de filtro a los que tenemos.
	 * 
	 * @param callbackFilter
	 *            El nuevo callback que va a dispararse en caso de una nueva busqueda en el filtro.
	 */
	public void addCallbackFilter(CallbackFilter<E> callbackFilter) {
		if (callbackFilter != null) {
			this.callbackFilters.add(callbackFilter);
		}
	}

	/**
	 * Permite crear el panel donde se van a desplegar los campos de filtrado.
	 * 
	 * @return El panel de filtrado.
	 */
	protected abstract BaseSearchPanel<? extends SearchBean> createSearchPanel();

	/**
	 * Retorna el decorador del botón de busqueda.
	 * 
	 * @return El decorador del botón de busqueda.
	 */
	protected abstract ButtonDecorator getSearchButtonDecorator();

	/**
	 * Retorna el decorador del botón de limpieza de filtro.
	 * 
	 * @return El decorador del botón de limpieza de filtro.
	 */
	protected abstract ButtonDecorator getCleanFilterButtonDecorator();

	/**
	 * Permite crear acciones de filtro propias.
	 * 
	 * @return Las nuevas acciones de filtro que vamos a agregar a este filtro.
	 */
	protected abstract Collection<SearchAction<E>> getFilterActions();

	/**
	 * Se encarga de retornar el servicio que vamos a utilizar para buscar las entidades con este filtro.
	 * 
	 * @return El servicio para la busqueda de entidades.
	 */
	protected abstract BaseService<E, PK> getService();

	/**
	 * Se encarga de crear el filtro de búsqueda a partir del filtro recibido del panel.
	 * 
	 * @param filter
	 *            El filtro que recibimos del panel.
	 * @return El filtro para la búsqueda.
	 */
	protected abstract BaseFilter<E, PK> createFilter(SearchBean filter);
}