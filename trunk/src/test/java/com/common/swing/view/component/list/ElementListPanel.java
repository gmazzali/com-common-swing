package com.common.swing.view.component.list;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.common.swing.domain.model.Element;
import com.common.swing.domain.model.Sexo;
import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.panel.BaseListPanel;
import com.common.swing.view.component.table.BaseTable;
import com.common.swing.view.component.table.formatter.CellFormatter;
import com.common.swing.view.component.table.formatter.impl.BigDecimalCellFormatter;
import com.common.swing.view.component.table.formatter.impl.BooleanComboCellFormatter;
import com.common.swing.view.component.table.formatter.impl.DateCellFormatter;
import com.common.swing.view.component.table.formatter.impl.EnumCellFormatter;
import com.common.swing.view.component.table.formatter.impl.LongCellFormatter;
import com.common.swing.view.component.table.formatter.impl.StringCellFormatter;
import com.common.util.business.tool.collection.CollectionUtil;

/**
 * El panel de la tabla de elementos.
 * 
 * @since 27/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ElementListPanel extends BaseListPanel<Element> {
	private static final long serialVersionUID = 1L;

	public ElementListPanel() {
		this.init();
	}

	@Override
	protected void afterInit() {
	}

	@Override
	public Integer getHeightSize() {
		return 300;
	}

	@Override
	public Integer getWidthSize() {
		return 700;
	}

	@Override
	protected BaseTable<Element> createTable() {
		return new ElementTable();
	}

	@Override
	protected void loadHeaderRenderers(BaseTable<Element> table) {
	}

	@Override
	protected void loadCellFormatter(BaseTable<Element> table) {
		final CellFormatter codeFormatter = new LongCellFormatter();
		final CellFormatter nameFormatter = new StringCellFormatter();
		final CellFormatter fechaFormatter = new DateCellFormatter("dd/MM/yyyy");
		final CellFormatter sexoFormatter = new EnumCellFormatter<Sexo>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void beforeEdit(RowBean rowBean, JComboBox<Sexo> componentEditor, JLabel componentViewer) {
				Element element = (Element) rowBean;
				if (element.getFecha() != null) {
					componentEditor.setEnabled(true);
					if (element.isActivo()) {
						this.setComboBoxData(componentEditor, CollectionUtil.newArrayList(Sexo.MASCULINO));
					} else {
						this.setComboBoxData(componentEditor, CollectionUtil.newArrayList(Sexo.MASCULINO, Sexo.FEMENINO));
					}
				} else {
					this.setComboBoxData(componentEditor, null);
					componentEditor.setEnabled(false);
				}
			}

			@Override
			protected void afterEdit(RowBean rowBean, JComboBox<Sexo> componentEditor, JLabel componentViewer) {
				Element element = (Element) rowBean;
				if (element.isActivo() && element.getSexo() != null) {
					element.setSexo(Sexo.MASCULINO);
				}
			}
		};
		final CellFormatter salarioFormatter = new BigDecimalCellFormatter();
		final CellFormatter activoFormatter = new BooleanComboCellFormatter();

		table.addCellFormatter(Element.Attribute.CODE, codeFormatter);
		table.addCellFormatter(Element.Attribute.NAME, nameFormatter);
		table.addCellFormatter(Element.Attribute.FECHA, fechaFormatter);
		table.addCellFormatter(Element.Attribute.SEXO, sexoFormatter);
		table.addCellFormatter(Element.Attribute.SALARIO, salarioFormatter);
		table.addCellFormatter(Element.Attribute.ACTIVO, activoFormatter);
	}
}