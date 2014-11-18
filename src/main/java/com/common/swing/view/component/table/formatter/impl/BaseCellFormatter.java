package com.common.swing.view.component.table.formatter.impl;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.common.swing.view.bean.RowBean;
import com.common.swing.view.component.table.BaseTable;
import com.common.swing.view.component.table.formatter.CellFormatter;
import com.common.swing.view.component.table.model.BaseTableModel;

/**
 * La clase base para los formateadores de las celdas, ya sea para visualización o edición de las mismas.
 * 
 * @see LongCellFormatter
 * @see BigDecimalCellFormatter
 * @see DateCellFormatter
 * @see StringCellFormatter
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <C>
 *            La clase de componente que vamos a usar para editar la celda.
 */
public abstract class BaseCellFormatter<C extends Component> extends AbstractCellEditor implements CellFormatter {
	private static final long serialVersionUID = 1L;

	/**
	 * El componente de visualización.
	 */
	protected JLabel componentViewer;
	/**
	 * El componente para editar.
	 */
	protected C componentEditor;

	/**
	 * Constructor de un formateador de celda que recibe el campo con el que va a editarse la misma.
	 * 
	 * @param editor
	 *            El componente de edición.
	 */
	public BaseCellFormatter(C editor) {
		this(editor, null, null);
	}

	/**
	 * Constructor de un formateador de celda que recibe el campo con el que va a editarse la misma y la orientación en la visualización del valor.
	 * 
	 * @param editor
	 *            El componente de edición.
	 * @param horizontalAlignment
	 *            La alineación horizontal del componente de visualización.
	 * @param verticalAlignment
	 *            La alineación vertical del componente de visualización.
	 */
	public BaseCellFormatter(C editor, Integer horizontalAlignment, Integer verticalAlignment) {
		this.componentEditor = editor;
		this.componentViewer = new DefaultTableCellRenderer();
		if (horizontalAlignment == null) {
			horizontalAlignment = SwingConstants.LEFT;
		}
		if (verticalAlignment == null) {
			verticalAlignment = SwingConstants.CENTER;
		}
		this.componentViewer.setHorizontalAlignment(horizontalAlignment);
		this.componentViewer.setVerticalAlignment(verticalAlignment);
	}

	/**
	 * Permite definir que la celda va a poder editarse solo mediante 2 clicks del mouse.
	 */
	@Override
	public boolean isCellEditable(EventObject anEvent) {
		if (anEvent instanceof MouseEvent) {
			return ((MouseEvent) anEvent).getClickCount() >= this.getClickCount();
		}
		return false;
	}

	/**
	 * Retorna la cantidad de clicks que deben hacerse en la celda para activar la edición.
	 * 
	 * @return La cantidad de click sobre la celda para activar la edición.
	 */
	protected int getClickCount() {
		return CLICK_COUNT;
	}

	/**
	 * Permite recuperar el valor editado previamente convertido al tipo correcto.
	 * 
	 * @return El valor convertido obtenido desde el campo de edición.
	 */
	@Override
	public Object getCellEditorValue() {
		return this.parse(this.componentEditor);
	}

	/**
	 * Crea el render para visualización del contenido de la celda.
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (table instanceof BaseTable<?>) {
			RowBean rowBean = ((BaseTableModel<?>) table.getModel()).getRow(table.convertRowIndexToModel(row));
			if (rowBean != null) {
				this.afterEdit(rowBean, this.componentEditor, this.componentViewer);
				table.repaint();
			}
		}
		return ((DefaultTableCellRenderer) this.componentViewer).getTableCellRendererComponent(table, this.format(value), isSelected, hasFocus, row,
				column);
	}

	/**
	 * Crea el campo de edición y lo carga para poder editar.
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (table instanceof BaseTable<?>) {
			this.beforeEdit(((BaseTableModel<?>) table.getModel()).getRow(table.convertRowIndexToModel(row)), this.componentEditor,
					this.componentViewer);
		}
		this.setValueToEditComponent(value, this.componentEditor);
		return this.componentEditor;
	}

	/**
	 * Permite actualizar el componente de acuerdo al bean de la fila. Este método va a ejecutarse antes de cargar la celda con el componente de
	 * edición.
	 * 
	 * @param rowBean
	 *            El bean de la fila que estamos editando.
	 * @param componentEditor
	 *            El componente de edición.
	 * @param componentViewer
	 *            El componente de visualización.
	 */
	protected void beforeEdit(RowBean rowBean, C componentEditor, JLabel componentViewer) {
	}

	/**
	 * Permite actualizar el bean de acuerdo al contenido del componente. Este método va a ejecutarse al momento de finalizar la edición de la celda.
	 * 
	 * @param rowBean
	 *            El bean de la fila que estamos terminando de editar.
	 * @param componentEditor
	 *            El componente de edición.
	 * @param componentViewer
	 *            El componente de visualización.
	 */
	protected void afterEdit(RowBean rowBean, C componentEditor, JLabel componentViewer) {
	}

	/**
	 * Permite recuperar el componente de visualización.
	 * 
	 * @return El componente de visualización.
	 */
	public Component getComponentViewer() {
		return componentViewer;
	}

	/**
	 * Permite recuperar el componente de edición.
	 * 
	 * @return El componente de edición.
	 */
	public C getComponentEditor() {
		return componentEditor;
	}

	/**
	 * Permite formatear el objeto que corresponde a la celda. Previamente ya se valida que el mismo no sea <code>null</code>.
	 * 
	 * <pre>
	 * BEAN - format - CELL VIEW
	 * </pre>
	 * 
	 * @param beanValue
	 *            El valor que vamos a formatear. Puede ser <code>null</code>.
	 * @return El objeto que corresponde a lo que vamos a desplegar en la celda.
	 */
	public abstract Object format(Object beanValue);

	/**
	 * Permite parsear el valor ingreado en la celda al valor de la variable.
	 * 
	 * <pre>
	 * CELL EDIT - parse - BEAN
	 * </pre>
	 * 
	 * @param editComponent
	 *            El componente de la celda donde estamos editando.
	 * @return El valor que tenemos dentro del campo de edición. La clase de retorno debe coincidir con la variable del bean a la que vamos a la que
	 *         va a asignarse.
	 */
	public abstract Object parse(C editComponent);

	/**
	 * Permite cargar dentro del campo de edición el valor de la variable.
	 * 
	 * <pre>
	 * BEAN - setValue - CELL EDIT
	 * </pre>
	 * 
	 * @param beanValue
	 *            El valor de la variable.
	 * @param editComponent
	 *            El componente de edición.
	 */
	public abstract void setValueToEditComponent(Object beanValue, C editComponent);
}