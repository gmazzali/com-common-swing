package com.crud.swing.view.form.search;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
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
import com.common.util.business.tool.collection.CollectionUtil;
import com.crud.swing.view.form.BaseForm;

/**
 * La clase que implementa la interfaz que nos permite definir los formularios de filtrado para la b�squeda de entidades dentro de un sistema.
 * 
 * @since 01/05/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de las entidades que vamos a recuperar.
 * @param <B>
 *            La clase que utilizamos como filtro de b�squeda.
 */
public abstract class BaseSearchForm<E extends Serializable, B extends SearchBean> extends JPanel implements BaseForm {
	private static final long serialVersionUID = 1L;

	/**
	 * El panel de filtro de b�squeda.
	 */
	private BaseSearchPanel<B> searchPanel;
	/**
	 * El listado de las acciones del filtro de b�squeda.
	 */
	private Collection<SearchAction<E>> searchActions;
	/**
	 * Todos los callback de actualizaci�n del filtro de b�squeda.
	 */
	private Collection<CallbackFilter<E>> callbackFilters;
	/**
	 * El elemento de exclusividad de la busqueda.
	 */
	private Object searchMutex = new Object();

	/**
	 * Permite crear un panel de filtrado.
	 */
	public BaseSearchForm() {
		this.callbackFilters = new ArrayList<CallbackFilter<E>>();
	}

	/**
	 * Inicializa el panel de filtrado.
	 */
	@PostConstruct
	protected void init() {
		this.setLayout(new BorderLayout());

		this.searchPanel = this.createSearchPanel();
		this.add(searchPanel, BorderLayout.CENTER);

		this.searchActions = this.getDefaultFilterActions();
		Collection<SearchAction<E>> temporal = this.getFilterActions();
		if (CollectionUtil.isNotEmpty(temporal)) {
			this.searchActions.addAll(temporal);
		}
		if (CollectionUtil.isNotEmpty(this.searchActions)) {
			this.initFilterActionPanel(this);
		}

		this.afterInit();
	}

	@Override
	public void setEnabled(boolean enabled) {
		for (SearchAction<E> filterAction : this.searchActions) {
			filterAction.getButton().setEnabled(enabled);
		}
	}

	@Override
	public void enabled() {
		this.searchPanel.enabled();
		this.setEnabled(true);
	}

	@Override
	public void disabled() {
		this.searchPanel.disabled();
		this.setEnabled(false);
	}

	/**
	 * Permite vaciar el contenido del filtro de b�squeda.
	 */
	public void emptyFields() {
		// Actualizamos los botones.
		new Thread() {
			public void run() {
				synchronized (searchMutex) {
					for (SearchAction<E> searchAction : searchActions) {
						searchAction.getButton().setVisible(searchAction.isVisibleAction());
						searchAction.getButton().setEnabled(searchAction.isEnabledAction());
					}
				}
			}
		}.start();
		this.searchPanel.emptyFields();
		synchronized (searchMutex) {
			for (CallbackFilter<E> callbackFilter : callbackFilters) {
				callbackFilter.updateEntities(new ArrayList<E>());
			}
		}
	}

	/**
	 * Permite cargar el panel de las acciones del filtro de b�squeda.
	 * 
	 * @param panel
	 *            El panel donde va a cargarse el panel de acciones del filtro de b�squeda.
	 */
	protected void initFilterActionPanel(JPanel panel) {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		for (SearchAction<E> filterAction : this.searchActions) {
			buttonPanel.add(filterAction.createButton());
		}
		panel.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Permite crear las acciones del filtro que tenemos por omisi�n. Estas son la de busqueda y la de limpiar el filtro de b�squeda.
	 * 
	 * @return Las acciones por omisi�n del filtro de b�squeda.
	 */
	@SuppressWarnings("unchecked")
	protected Collection<SearchAction<E>> getDefaultFilterActions() {
		SearchListener<E> searchFilterListener = new SearchListener<E>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(SearchEvent<E> filterEvent) {
				executeSearch();
			}
		};
		SearchListener<E> clearFilterFilterListener = new SearchListener<E>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fireEvent(SearchEvent<E> filterEvent) {
				emptyFields();
			}
		};

		return CollectionUtil.newArrayList(new SearchAction<E>(searchFilterListener, this.getSearchButtonDecorator()) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabledAction() {
				return isEnabledSearchButton();
			};

			@Override
			public boolean isVisibleAction() {
				return isVisibleSearchButton();
			}
		}, new SearchAction<E>(clearFilterFilterListener, this.getCleanFilterButtonDecorator()) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabledAction() {
				return isEnabledCleanFilterButton();
			};

			@Override
			public boolean isVisibleAction() {
				return isVisibleCleanFilterButton();
			}
		});
	}

	/**
	 * Permite saber si va a habilitarse o no el bot�n de b�squeda.
	 * 
	 * @return <code>true</code> en caso que querramos el bot�n de b�squeda habilitado, en caso contrario, retorna <code>false</code>.
	 */
	protected boolean isEnabledSearchButton() {
		return true;
	}

	/**
	 * Permite saber si va a habilitarse o no el bot�n de limpieza de filtro.
	 * 
	 * @return <code>true</code> en caso que querramos el bot�n de limpieza de filtro habilitado, en caso contrario, retorna <code>false</code>.
	 */
	protected boolean isEnabledCleanFilterButton() {
		return true;
	}

	/**
	 * Permite saber si va a visualizarse o no el bot�n de b�squeda.
	 * 
	 * @return <code>true</code> en caso que querramos el bot�n de b�squeda visualizado, en caso contrario, retorna <code>false</code>.
	 */
	protected boolean isVisibleSearchButton() {
		return true;
	}

	/**
	 * Permite saber si va a visualizarse o no el bot�n de limpieza de filtro.
	 * 
	 * @return <code>true</code> en caso que querramos el bot�n de limpieza de filtro visualizado, en caso contrario, retorna <code>false</code>.
	 */
	protected boolean isVisibleCleanFilterButton() {
		return true;
	}

	/**
	 * Permite realizar la b�squeda y cargarlas dentro de los callback.
	 */
	protected void executeSearch() {
		new Thread() {
			@Override
			public void run() {
				synchronized (searchMutex) {
					try {
						disabled();
						Collection<E> entities = search(searchPanel.getFilter());
						for (CallbackFilter<E> callbackFilter : callbackFilters) {
							callbackFilter.updateEntities(entities);
						}
					} catch (SwingException e) {
						Notificaction.showErrorMessage(BaseSearchForm.this, e.getErrors());
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
	 * Permite terminar de configurar el formulario.
	 */
	protected abstract void afterInit();

	/**
	 * Permite crear el panel donde se van a desplegar los campos de filtrado.
	 * 
	 * @return El panel de filtrado.
	 */
	protected abstract BaseSearchPanel<B> createSearchPanel();

	/**
	 * Retorna el decorador del bot�n de busqueda.
	 * 
	 * @return El decorador del bot�n de busqueda.
	 */
	protected abstract ButtonDecorator getSearchButtonDecorator();

	/**
	 * Retorna el decorador del bot�n de limpieza de filtro.
	 * 
	 * @return El decorador del bot�n de limpieza de filtro.
	 */
	protected abstract ButtonDecorator getCleanFilterButtonDecorator();

	/**
	 * Permite crear acciones de filtro propias.
	 * 
	 * @return Las nuevas acciones de filtro que vamos a agregar a este filtro.
	 */
	protected abstract Collection<SearchAction<E>> getFilterActions();

	/**
	 * Se encarga de buscar las entidades que corresponden con el filtro creado con los datos recibidos.
	 * 
	 * @param filter
	 *            El filtro que recibimos del panel.
	 * @return El listado de las entidades que recuperamos con el filtro.
	 */
	protected abstract Collection<E> search(SearchBean filter);
}