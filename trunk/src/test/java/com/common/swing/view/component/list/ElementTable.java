package com.common.swing.view.component.list;

import java.util.HashMap;
import java.util.Map;

import com.common.swing.domain.model.Element;
import com.common.swing.view.component.table.BaseTable;
import com.common.swing.view.component.table.model.BaseTableModel;
import com.common.swing.view.component.table.model.ReadOnlyBaseTableModel;

/**
 * La tabla de elementos.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementTable extends BaseTable<Element> {

	private static final long serialVersionUID = 1L;

	private static final String[] VISIBLE_PROPERTIES = new String[] { Element.Attribute.CODE, Element.Attribute.NAME, Element.Attribute.FECHA,
			Element.Attribute.SALARIO, Element.Attribute.ACTIVO };

	private static final Map<String, String> PROPERTIES_NAME = new HashMap<String, String>();

	private static final Map<String, Integer> PROPERTIES_WIDTH = new HashMap<String, Integer>();

	static {
		PROPERTIES_NAME.put(Element.Attribute.CODE, "Cód.");
		PROPERTIES_NAME.put(Element.Attribute.NAME, "Nombre");
		PROPERTIES_NAME.put(Element.Attribute.FECHA, "Fecha Nac");
		PROPERTIES_NAME.put(Element.Attribute.SALARIO, "Salario");
		PROPERTIES_NAME.put(Element.Attribute.ACTIVO, "Activo");
		PROPERTIES_WIDTH.put(Element.Attribute.CODE, 100);
		PROPERTIES_WIDTH.put(Element.Attribute.NAME, 200);
		PROPERTIES_WIDTH.put(Element.Attribute.FECHA, 100);
		PROPERTIES_WIDTH.put(Element.Attribute.SALARIO, 100);
		PROPERTIES_WIDTH.put(Element.Attribute.ACTIVO, 75);
	}

	public ElementTable() {
		super(VISIBLE_PROPERTIES, PROPERTIES_NAME, PROPERTIES_WIDTH, true);
	}

	@Override
	protected BaseTableModel<Element> createModel(String[] visibleProperties, Map<String, String> visiblePropertiesName) {
		return new ReadOnlyBaseTableModel<Element>(visibleProperties, visiblePropertiesName) {
			private static final long serialVersionUID = 1L;

			@Override
			protected String[] getEditableProperties() {
				return new String[] { Element.Attribute.CODE, Element.Attribute.NAME, Element.Attribute.FECHA, Element.Attribute.SALARIO,
						Element.Attribute.ACTIVO };
			}
		};
	}
}