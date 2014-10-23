package com.common.swing.domain.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.common.util.business.service.impl.BaseServiceImpl;
import com.common.util.domain.exception.UncheckedException;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.order.Orders;

/**
 * La clase que da servicios a los elementos Element.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementServiceImpl extends BaseServiceImpl<Element, Integer> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ElementServiceImpl.class);

	/**
	 * El listado de los elementos.
	 */
	private static List<Element> elements = new ArrayList<Element>();
	/**
	 * El id.
	 */
	private Integer id = 1;

	@Override
	public void validate(Element entity) throws UncheckedException {
	}

	@Override
	public Element findById(Integer id) throws UncheckedException {
		ElementServiceImpl.log.trace("Find by id: " + id);
		for (Element e : ElementServiceImpl.elements) {
			if (id == e.getId()) {
				return e;
			}
		}
		return null;
	}

	@Override
	public void save(Element entity) throws UncheckedException {
		ElementServiceImpl.log.trace("save: " + entity);
		entity.setId(this.id++);
		ElementServiceImpl.elements.add(entity);
	}

	@Override
	public void saveOrUpdate(Element entity) throws UncheckedException {
		ElementServiceImpl.log.trace("save or update: " + entity);
		Element delete = this.get(entity.getId());
		if (delete == null) {
			this.save(entity);
		} else {
			this.update(entity);
		}
	}

	@Override
	public void update(Element entity) throws UncheckedException {
		ElementServiceImpl.log.trace("update: " + entity);
		Element update = this.get(entity.getId());
		ElementServiceImpl.elements.remove(update);
		ElementServiceImpl.elements.add(entity);
	}

	@Override
	public void delete(Element entity) throws UncheckedException {
		ElementServiceImpl.log.trace("delete: " + entity);
		Element delete = this.get(entity.getId());
		if (delete != null) {
			ElementServiceImpl.elements.remove(delete);
		}
	}

	@Override
	public List<Element> findAll(Orders orders) {
		return elements;
	}

	@Override
	public List<Element> findAll() {
		return elements;
	}

	@Override
	public List<Element> findByFilter(BaseFilter<Element, Integer> filter) {
		return elements;
	}

	/**
	 * La función encargada de buscar el elemento dentro del listado.
	 * 
	 * @param id
	 *            El identificador del elemento.
	 * @return El elemento de ese id.
	 */
	private Element get(Integer id) {
		for (Element e : ElementServiceImpl.elements) {
			if (id == e.getId()) {
				return e;
			}
		}
		return null;
	}
}