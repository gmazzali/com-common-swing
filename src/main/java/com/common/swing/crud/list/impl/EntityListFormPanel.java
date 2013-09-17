package com.common.swing.crud.list.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

import com.common.swing.crud.edit.EntityEditFormContainer;
import com.common.swing.crud.list.EntityListForm;
import com.common.swing.crud.list.EntityListFormContainer;
import com.common.swing.icon.IconResources;
import com.common.util.exception.CheckedException;
import com.common.util.model.Persistence;
import com.common.util.model.filter.Filter;

/**
 * La clase que define el panel que despliega el listado de los elementos que vamos a desplegar para poder dar de altas uno nuevo, modificar o
 * eliminar elementos que se desplegaron.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que define el tipo de entidad que vamos a manipular dentro de este panel del listado.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que tenemos listada dentro de la tabla.
 */
public abstract class EntityListFormPanel<E extends Persistence<PK>, PK extends Serializable> extends JPanel implements EntityListForm<E, PK> {

	private static final long serialVersionUID = -931801998574479785L;

	/**
	 * La constante de los tamaños de los elementos.
	 */
	protected static final Integer SEPARATOR = 10;
	protected static final Integer MIN_TABLE_WIDTH = 400;
	protected static final Integer MIN_TABLE_HEIGHT = 200;
	protected static final Integer MIN_BUTTON_WIDTH = 30;
	protected static final Integer MIN_BUTTON_HEIGHT = 30;

	/**
	 * El logger de la ventana.
	 */
	private static final Logger log = Logger.getLogger(EntityListFormPanel.class);

	/**
	 * El listado de los elementos que vamos a tener dentro de esta ventana y el filtro que ocupamos para recuperarlas.
	 */
	protected List<E> entities;
	protected Filter filter;

	/**
	 * El panel de scroll, la tabla, el ordenador de filas, el modelo de la tabla y sus dimensiones.
	 */
	protected JScrollPane scrollPane;
	protected JTable table;
	protected static Integer tableWidth = EntityListFormPanel.MIN_TABLE_WIDTH;
	protected static Integer tableHeight = EntityListFormPanel.MIN_TABLE_HEIGHT;

	/**
	 * Los botones para agregar, modificar, eliminar las entidades, el de cierre y sus dimensiones.
	 */
	protected JButton newButton;
	protected JButton modifyButton;
	protected JButton deleteButton;
	protected JButton closeButton;
	protected static Integer buttonWidth = EntityListFormPanel.MIN_BUTTON_WIDTH;
	protected static Integer buttonHeight = EntityListFormPanel.MIN_BUTTON_HEIGHT;

	/**
	 * La imagen de progreso de las acciones.
	 */
	protected JLabel progressLabel;

	/**
	 * Constructor de la ventana de listado.
	 */
	public EntityListFormPanel() {
		super();
		EntityListFormPanel.log.trace("EntityListPanel create");

		this.setBorder(new LineBorder(new Color(0, 0, 0), 2));

		this.init();
		this.setSize();
		this.initTable();
	}

	/**
	 * La función encargada de inicializar las diferentes partes del panel que vamos a ocupar para mostrar el listado de las entidades.
	 */
	private void init() {
		EntityListFormPanel.log.trace("initializeVisualComponents");

		this.setBackground(Color.WHITE);
		this.setLayout(null);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setDoubleBuffered(true);
		this.scrollPane.setBounds(10, 11, 362, 206);
		this.add(this.scrollPane);

		this.table = new JTable();
		this.table.setFont(new Font("Arial", Font.PLAIN, 11));
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					EntityListFormPanel.this.actionModifyEntity();
				}
			}
		});
		this.scrollPane.setViewportView(this.table);

		this.newButton = new JButton();
		this.newButton.setToolTipText(this.getNewButtonCaption());
		this.newButton.setIcon(IconResources.Action.ADD_ICON);
		this.newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EntityListFormPanel.this.actionNewEntity();
			}
		});
		this.add(this.newButton);

		this.modifyButton = new JButton();
		this.modifyButton.setToolTipText(this.getModifyButtonCaption());
		this.modifyButton.setIcon(IconResources.Action.MODIFY_ICON);
		this.modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EntityListFormPanel.this.actionModifyEntity();
			}
		});
		this.add(this.modifyButton);

		this.deleteButton = new JButton();
		this.deleteButton.setToolTipText(this.getDeleteButtonCaption());
		this.deleteButton.setIcon(IconResources.Action.DELETE_ICON);
		this.deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EntityListFormPanel.this.actionDeleteEntity();
			}
		});
		this.add(this.deleteButton);

		this.progressLabel = new JLabel();
		this.progressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.progressLabel.setBounds(382, 132, 30, 44);
		this.add(this.progressLabel);

		this.closeButton = new JButton();
		this.closeButton.setIcon(IconResources.Action.RETURN_ICON);
		this.closeButton.setToolTipText(this.getCloseButtonCaption());
		this.closeButton.setVisible(this.isContainerCloseable());
		this.closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EntityListFormPanel.this.getFormContainer().close();
			}
		});
		this.add(this.closeButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.table.setEnabled(enabled);

		this.newButton.setEnabled(enabled);
		this.modifyButton.setEnabled(enabled);
		this.deleteButton.setEnabled(enabled);
		this.closeButton.setEnabled(enabled);
	}

	/**
	 * La función encargada de inicializar el formato de la tabla que vamos a ocupar para desplegar el listado de las entidades.
	 */
	private void initTable() {
		this.initTableModel();
		this.initTableRowSorter();
	}

	/**
	 * La función encargada de crear el modelo de la tabla.
	 */
	private void initTableModel() {
		EntityListFormPanel.log.trace("setTableModel");

		DefaultTableModel tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = -890706019332687145L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Seteamos los nombre de las columnas.
		for (int i = 0; i < this.getColumnsTitles().length; i++) {
			tableModel.addColumn(this.getColumnsTitles()[i]);
		}

		// Seteamos el modelo a la tabla.
		this.table.setModel(tableModel);

		// Seteamos los anchos de las columnas.
		for (int i = 0; i < this.getColumnsWidths().length; i++) {
			this.table.getColumnModel().getColumn(i).setPreferredWidth(this.getColumnsWidths()[i]);
		}
	}

	/**
	 * La función encargada de cargar el ordenador de las filas de la tabla.
	 */
	private void initTableRowSorter() {
		// Seteamos el ordenador de las filas de al tabla.
		DefaultTableModel tableModel = (DefaultTableModel) this.table.getModel();
		TableRowSorter<TableModel> tableSorter = new TableRowSorter<TableModel>(tableModel);
		this.table.setRowSorter(tableSorter);
	}

	/**
	 * La función encargada de permitir definir el tamaño que vamos a tener en la tabla de listado de entidades.
	 * 
	 * @param tableWidth
	 *            El ancho de la nueva tabla, no puede ser menor a los 400px.
	 * @param tableHeight
	 *            El alto de la nueva tabla, no puede ser menor a los 200px.
	 */
	public void setTableSize(Integer tableWidth, Integer tableHeight) {
		EntityListFormPanel.log.trace("setTableSize");

		// Verificamos el ancho de la tabla.
		if (tableWidth != null && tableWidth >= EntityListFormPanel.MIN_TABLE_WIDTH) {
			EntityListFormPanel.tableWidth = tableWidth;
		} else {
			EntityListFormPanel.tableWidth = EntityListFormPanel.MIN_TABLE_WIDTH;
		}

		// Verificamos la altura de la tabla.
		if (tableHeight != null && tableHeight >= EntityListFormPanel.MIN_TABLE_HEIGHT) {
			EntityListFormPanel.tableHeight = tableHeight;
		} else {
			EntityListFormPanel.tableHeight = EntityListFormPanel.MIN_TABLE_HEIGHT;
		}

		// Seteamos las posiciones.
		this.setSize();
	}

	/**
	 * La función encargada de permitir definir el tamaño de los botones que vamos a tener dentro de esta ventana.
	 * 
	 * @param buttonWidth
	 *            El ancho de los botones de la ventana, no puede ser menor a los 100px.
	 * @param buttonHeight
	 *            El alto de los botones de la ventana, no puede ser menor a los 40px.
	 */
	public void setButtonSize(Integer buttonWidth, Integer buttonHeight) {
		EntityListFormPanel.log.trace("setButtonSize");

		// Verificamos el ancho de los botones.
		if (buttonWidth != null && buttonWidth >= EntityListFormPanel.MIN_BUTTON_WIDTH) {
			EntityListFormPanel.buttonWidth = buttonWidth;
		} else {
			EntityListFormPanel.buttonWidth = EntityListFormPanel.MIN_BUTTON_WIDTH;
		}

		// Verificamos la altura de los botones.
		if (buttonHeight != null && buttonHeight >= EntityListFormPanel.MIN_BUTTON_HEIGHT) {
			EntityListFormPanel.buttonHeight = buttonHeight;
		} else {
			EntityListFormPanel.buttonHeight = EntityListFormPanel.MIN_BUTTON_HEIGHT;
		}

		// Seteamos las posiciones.
		this.setSize();
	}

	/**
	 * La función encargada de cargar los tamaños y las posiciones de los diferentes elementos que tenemos dentro de esta ventana de listado de
	 * entidades.
	 */
	private void setSize() {
		EntityListFormPanel.log.trace("setSize");

		// Creamos el rectángulo principal de la ventana.
		Rectangle panel = new Rectangle(0, 0, EntityListFormPanel.SEPARATOR + EntityListFormPanel.tableWidth + EntityListFormPanel.SEPARATOR
				+ EntityListFormPanel.buttonWidth + EntityListFormPanel.SEPARATOR, EntityListFormPanel.SEPARATOR + EntityListFormPanel.tableHeight
				+ EntityListFormPanel.SEPARATOR);
		this.setPreferredSize(panel.getSize());

		// Ubicamos la tabla.
		this.scrollPane.setBounds(EntityListFormPanel.SEPARATOR, EntityListFormPanel.SEPARATOR, EntityListFormPanel.tableWidth,
				EntityListFormPanel.tableHeight);

		// Ubicamos los botones.
		this.newButton.setBounds(EntityListFormPanel.SEPARATOR + EntityListFormPanel.tableWidth + EntityListFormPanel.SEPARATOR,
				EntityListFormPanel.SEPARATOR, EntityListFormPanel.buttonWidth, EntityListFormPanel.buttonHeight);
		this.modifyButton.setBounds(EntityListFormPanel.SEPARATOR + EntityListFormPanel.tableWidth + EntityListFormPanel.SEPARATOR,
				EntityListFormPanel.SEPARATOR + EntityListFormPanel.buttonHeight + EntityListFormPanel.SEPARATOR, EntityListFormPanel.buttonWidth,
				EntityListFormPanel.buttonHeight);
		this.deleteButton.setBounds(EntityListFormPanel.SEPARATOR + EntityListFormPanel.tableWidth + EntityListFormPanel.SEPARATOR,
				EntityListFormPanel.SEPARATOR + EntityListFormPanel.buttonHeight + EntityListFormPanel.SEPARATOR + EntityListFormPanel.buttonHeight
						+ EntityListFormPanel.SEPARATOR, EntityListFormPanel.buttonWidth, EntityListFormPanel.buttonHeight);

		this.closeButton
				.setBounds(EntityListFormPanel.SEPARATOR + EntityListFormPanel.tableWidth + EntityListFormPanel.SEPARATOR, panel.height
						- EntityListFormPanel.SEPARATOR - EntityListFormPanel.buttonHeight, EntityListFormPanel.buttonWidth,
						EntityListFormPanel.buttonHeight);

		this.progressLabel.setBounds(EntityListFormPanel.SEPARATOR + EntityListFormPanel.tableWidth + EntityListFormPanel.SEPARATOR,
				panel.height - EntityListFormPanel.SEPARATOR - EntityListFormPanel.buttonHeight - EntityListFormPanel.SEPARATOR
						- EntityListFormPanel.buttonHeight, EntityListFormPanel.buttonWidth, EntityListFormPanel.buttonHeight);
	}

	@Override
	public void updateEntityList(final List<E> entities) {

		// Vaciamos el contenido de la tabla.
		DefaultTableModel tableModel = (DefaultTableModel) EntityListFormPanel.this.table.getModel();
		tableModel.getDataVector().clear();

		// Tomamos el listado de las entidades.
		this.entities = entities;

		// Creamos el proceso para cargar los datos dentro de la tabla.
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					// Antes de procesar.
					EntityListFormPanel.this.beforeProccess();

					// la volvemos a cargar.
					EntityListFormPanel.this.loadListToTable();
				} catch (Exception e) {
					EntityListFormPanel.log.error("initList failed", e);
					JOptionPane.showMessageDialog(EntityListFormPanel.this, e.getMessage(), "Falla", JOptionPane.ERROR_MESSAGE);
				} finally {
					EntityListFormPanel.this.afterProccess();
				}
			}
		};
		thread.start();
	}

	/**
	 * La función a ejecutarse antes de comenzar un proceso en segundo plano.
	 */
	private void beforeProccess() {
		EntityListFormPanel.this.setEnabled(false);
		IconResources.PROGRESS_LIST_ICON.setImageObserver(EntityListFormPanel.this.progressLabel);
		EntityListFormPanel.this.progressLabel.setIcon(IconResources.PROGRESS_LIST_ICON);
	}

	/**
	 * La función a ejecutarse después de comenzar un proceso en segundo plano.
	 */
	private void afterProccess() {
		EntityListFormPanel.this.setEnabled(true);
		EntityListFormPanel.this.progressLabel.setIcon(null);
	}

	/**
	 * La función encargada de cargar dentro de la tabla el listado de los elementos que vamos a desplegar.
	 */
	protected void loadListToTable() {
		EntityListFormPanel.log.trace("loadListToTable");

		// Solo si el listado no es nulo.
		if (this.entities != null) {
			DefaultTableModel tableModel = (DefaultTableModel) this.table.getModel();

			for (E entity : this.entities) {
				tableModel.addRow(this.converterEntityToRow(entity));
			}
		} else {
			EntityListFormPanel.log.warn("loadListToTable entities null");
		}
	}

	/**
	 * La función encargada de crear una nueva entidad a partir de la ventana de alta de las entidades.
	 */
	@SuppressWarnings("unchecked")
	private void actionNewEntity() {
		EntityListFormPanel.log.trace("newEntity");

		// Abrimos una ventana de edición para crear una entidad nueva.
		if (this.getEntityEditFormContainer() != null) {
			EntityEditFormContainer<E, PK> entityFormContainer = this.getEntityEditFormContainer().createNewForm();
			((JDialog) entityFormContainer).setLocationRelativeTo(this);
			entityFormContainer.open();

			((EntityListFormContainer<E, PK>) this.getFormContainer()).updateEntityListForm();
		} else {
			EntityListFormPanel.log.info("Entity create window don't define");
		}
	}

	/**
	 * La función encargada de desplegar la ventana de edición para editar la entidad que tenemos seleccionada dentro de la tabla desplegada.
	 */
	@SuppressWarnings("unchecked")
	private void actionModifyEntity() {
		EntityListFormPanel.log.trace("modifyEntity");

		// Vemos si hay algún elemento seleccionado para editar.
		if (this.table.getSelectedRow() != -1) {
			// Recuperamos la entidad.
			E entity = this.getCloneEntity(this.entities.get(this.table.convertRowIndexToModel(this.table.getSelectedRow())));

			if (entity != null) {
				EntityListFormPanel.log.info("MODIFY -> " + entity.toString());
			}

			// Abrimos una ventana de edición para modificar la entidad.
			if (this.getEntityEditFormContainer() != null) {
				EntityEditFormContainer<E, PK> entityFormContainer = this.getEntityEditFormContainer().createEditForm(entity);
				((JDialog) entityFormContainer).setLocationRelativeTo(this);
				entityFormContainer.open();

				((EntityListFormContainer<E, PK>) this.getFormContainer()).updateEntityListForm();

			} else {
				EntityListFormPanel.log.info("Entity edit window don't define");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento para modificarlo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * La función encargada de eliminar la entidad que tenemos seleccionada en el listado que desplegamos.
	 */
	@SuppressWarnings("unchecked")
	private void actionDeleteEntity() {
		EntityListFormPanel.log.trace("actionDeleteEntity");

		// Vemos si hay algún elemento seleccionado para borrar.
		if (this.table.getSelectedRow() != -1) {
			// Recuperamos la entidad.
			E entity = this.entities.get(this.table.convertRowIndexToModel(this.table.getSelectedRow()));

			// Preguntamos si queremos eliminar el elemento.
			if (JOptionPane.showConfirmDialog(this, "Desea borrar el elemento \"" + entity.toString() + "\"?", "Confirmación",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				// Borramos la entidad.
				this.deleteEntity(entity);

				// Volvemos a cargar la tabla.
				((EntityListFormContainer<E, PK>) this.getFormContainer()).updateEntityListForm();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento para eliminarlo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * La función que crea un proceso de borrado en segundo plano.
	 * 
	 * @param entity
	 *            La entidad que vamos a borrar de la base de datos.
	 */
	private void deleteEntity(final E entity) {
		EntityListFormPanel.log.trace("deleteEntity -> " + entity.toString());

		Thread deleteThread = new Thread() {
			@Override
			public void run() {
				try {
					EntityListFormPanel.this.beforeProccess();
					EntityListFormPanel.this.getEntityService().delete(entity);
				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(EntityListFormPanel.this, "Falla en el borrado de la entidad.", "Error",
							JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				} finally {
					EntityListFormPanel.this.afterProccess();
				}
			}
		};
		deleteThread.start();
		try {
			// Esperamos el proceso para seguir.
			deleteThread.join();
		} catch (InterruptedException e) {
			EntityListFormPanel.log.error("deleteEntity interrupted", e);
		}
	}
}
