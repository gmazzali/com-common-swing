package com.commons.swing.abm.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.common.util.exception.CheckedException;
import com.common.util.service.impl.GenericServiceImpl;
import com.common.util.verifier.Verifier;

/**
 * La clase que da servicios a los elementos Element.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ElementServiceImpl extends GenericServiceImpl<Element, Integer> {

	/**
	 * El logger de la ventana.
	 */
	private static final Logger log = Logger.getLogger(ElementServiceImpl.class);
	/**
	 * El listado de los elementos.
	 */
	private static List<Element> elements = new ArrayList<>();
	/**
	 * El id.
	 */
	private Integer id = 1;

	/**
	 * @see com.common.util.service.GenericService#validate(com.commons.util.model.Persistence)
	 */
	@Override
	public void validate(Element entity) throws CheckedException {
		ElementServiceImpl.log.trace("ElementServiceImpl Validate: " + entity);

		if (!Verifier.isValidPersonName(entity.getName())) {
			throw new CheckedException("ERROR EN EL NOMBRE") {
			};
		}
	}

	/**
	 * @see com.common.util.service.impl.GenericServiceImpl#findById(java.io.Serializable)
	 */
	@Override
	public Element findById(Integer id) throws CheckedException {
		ElementServiceImpl.log.trace("ElementServiceImpl FindById: " + id);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (Element e : ElementServiceImpl.elements) {
			if (id == e.getId()) {
				return e;
			}
		}
		return null;
	}

	/**
	 * @see com.common.util.service.impl.GenericServiceImpl#findAll()
	 */
	@Override
	public List<Element> findAll() throws CheckedException {
		ElementServiceImpl.log.trace("ElementServiceImpl FindAll");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return ElementServiceImpl.elements;
	}

	/**
	 * @see com.common.util.service.impl.GenericServiceImpl#save(com.commons.util.model.Persistence)
	 */
	@Override
	public void save(Element entity) throws CheckedException {
		ElementServiceImpl.log.trace("ElementServiceImpl Save: " + entity);
		entity.setId(this.id++);
		ElementServiceImpl.elements.add(entity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see com.common.util.service.impl.GenericServiceImpl#saveOrUpdate(com.commons.util.model.Persistence)
	 */
	@Override
	public void saveOrUpdate(Element entity) throws CheckedException {
		Element delete = this.get(entity.getId());
		if (delete == null) {
			this.save(entity);
		} else {
			this.update(entity);
		}
	}

	/**
	 * @see com.common.util.service.impl.GenericServiceImpl#update(com.commons.util.model.Persistence)
	 */
	@Override
	public void update(Element entity) throws CheckedException {
		ElementServiceImpl.log.trace("ElementServiceImpl Update: " + entity);
		Element update = this.get(entity.getId());
		ElementServiceImpl.elements.remove(update);
		ElementServiceImpl.elements.add(entity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see com.common.util.service.impl.GenericServiceImpl#delete(com.commons.util.model.Persistence)
	 */
	@Override
	public void delete(Element entity) throws CheckedException {
		ElementServiceImpl.log.trace("ElementServiceImpl Delete: " + entity);
		Element delete = this.get(entity.getId());
		if (delete != null) {
			ElementServiceImpl.elements.remove(delete);
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
