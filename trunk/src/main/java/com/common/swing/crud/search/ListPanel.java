package com.common.swing.crud.search;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

import com.common.swing.abm.edit.EntityEditPanel;
import com.common.swing.abm.list.EntityListDialog;
import com.common.swing.crud.form.GenericFormDialog;
import com.common.swing.listener.FormCloseListener.WindowCloseState;
import com.common.swing.util.SwingResources;
import com.common.util.model.Persistence;
import com.common.util.service.GenericService;

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
 *            La clase que va a hacer de clave primaria de las entidades que vamos a manipular.
 */
public abstract class ListPanel<E extends Persistence<PK>, PK extends Serializable> extends JPanel {

	private static final long serialVersionUID = -931801998574479785L;

	/**
	 * El logger de la ventana.
	 */
	private static final Logger log = Logger.getLogger(ListPanel.class);

	/**
	 * El listado de los elementos que vamos a tener dentro de esta ventana.
	 */
	protected List<E> entityList;
	/**
	 * El servicio que nos permite manipular la entidad.
	 */
	protected GenericService<E, PK> entityService;

	/**
	 * La ventana donde va a desplegarse este panel.
	 */
	protected EntityListDialog<E, PK> entityListDialog;

	/**
	 * La ventana dialogo que nos permite visualizar, crear o editar entidades.
	 */
	protected GenericFormDialog<E, PK> formDialog;

	/**
	 * El campo de busqueda para filtrado de la tabla.
	 */
	protected JTextField searchField;
	/**
	 * La imagen que vamos a desplegar cuando estemos haciendo un acceso a la base de datos.
	 */
	protected JLabel progressLabel;
	/**
	 * Los botones para agregar, modificar, eliminar las entidades, el de cierre y sus dimensiones.
	 */
	protected JButton newButton;
	protected JButton modifyButton;
	protected JButton deleteButton;
	protected JButton closeButton;
	protected static Integer buttonWidth = 120;
	protected static Integer buttonHeight = 40;
	/**
	 * El panel de scroll, la tabla, el ordenador de filas, el modelo de la tabla y sus dimensiones.
	 */
	protected JScrollPane scrollPane;
	protected JTable table;
	protected DefaultTableModel tableModel;
	protected String[] columnsTitles;
	protected Integer[] columnsWidths;
	protected Integer[] columnsFilter;
	protected TableRowSorter<TableModel> tableSorter;
	protected RowFilter<Object, Integer> tableRowFilter;
	protected static Integer tableWidth = 400;
	protected static Integer tableHeight = 200;

	/**
	 * Constructor de la ventana de listado.
	 */
	public ListPanel() {
		this.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		// Entrada al LOG.
		ListPanel.log.trace("EntityListPanel create");

		this.initializeComponents();
		this.initializeVisualComponents();
		this.setSize();
		this.setTableModel();
	}

	/**
	 * El metodo encargado de inicializar todos los componentes que vamos a utilizar dentro de este panel.
	 */
	private void initializeComponents() {
		this.formDialog = new GenericFormDialog<E, PK>();
		// this.formDialog.setParent(this);
		this.columnsTitles = this.getColumnsTitles();
		this.columnsWidths = this.getColumnsWidths();
		this.columnsFilter = this.getColumnsFilter();
	}

	/**
	 * La función encargada de inicializar las diferentes partes del panel que vamos a ocupar para mostrar el listado de las entidades.
	 */
	private void initializeVisualComponents() {
		ListPanel.log.trace("init");

		this.setBounds(100, 100, 550, 252);

		this.setBackground(Color.WHITE);
		this.setLayout(null);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setDoubleBuffered(true);
		this.scrollPane.setBounds(10, 10, ListPanel.tableWidth, ListPanel.tableHeight);
		this.add(this.scrollPane);

		this.table = new JTable();
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					ListPanel.this.modifyEntity();
				}
			}
		});
		this.table.setFont(new Font("Arial", Font.PLAIN, 11));
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.table.setBounds(new Rectangle(0, 0, ListPanel.tableWidth, ListPanel.tableHeight));
		this.scrollPane.setViewportView(this.table);

		this.searchField = new JTextField();
		this.searchField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				ListPanel.this.searchField.selectAll();
			}
		});
		this.searchField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ListPanel.this.filterRows();
			}
		});
		this.searchField.setBounds(10, 221, 400, 20);
		this.searchField.setColumns(10);
		this.add(this.searchField);

		this.newButton = new JButton(this.getNewButtonLabel());
		this.newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListPanel.this.newEntity();
			}
		});
		this.newButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.newButton.setBounds(420, 11, ListPanel.buttonWidth, ListPanel.buttonHeight);
		this.newButton.setIcon(SwingResources.ADD_ICON);
		this.add(this.newButton);

		this.modifyButton = new JButton(this.getModifyButtonLabel());
		this.modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListPanel.this.modifyEntity();
			}
		});
		this.modifyButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.modifyButton.setBounds(420, 62, ListPanel.buttonWidth, ListPanel.buttonHeight);
		this.modifyButton.setIcon(SwingResources.MODIFY_ICON);
		this.add(this.modifyButton);

		this.deleteButton = new JButton(this.getDeleteButtonLabel());
		this.deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListPanel.this.deleteEntity();
			}
		});
		this.deleteButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.deleteButton.setBounds(420, 113, ListPanel.buttonWidth, ListPanel.buttonHeight);
		this.deleteButton.setIcon(SwingResources.DELETE_ICON);
		this.add(this.deleteButton);

		this.progressLabel = new JLabel("");
		this.progressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.progressLabel.setBounds(420, 164, 120, 32);
		this.add(this.progressLabel);

		this.closeButton = new JButton(this.getCloseButtonLabel());
		this.closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListPanel.this.closeWindow();
			}
		});
		this.closeButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.closeButton.setBounds(420, 201, ListPanel.buttonWidth, ListPanel.buttonHeight);
		this.closeButton.setIcon(SwingResources.RETURN_ICON);
		this.closeButton.setVisible(this.isCloseBottonVisible());
		this.add(this.closeButton);
	}

	/**
	 * La función encargada de crear el modelo de la tabla y cargarla dentro de la tabla que vamos a utilizar, como asi también, setearle los anchos a
	 * cada una de las columnas de la tabla.
	 */
	private void setTableModel() {
		ListPanel.log.trace("setTableModel");

		this.tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = -890706019332687145L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Seteamos los nombre de las columnas.
		for (int i = 0; i < this.columnsTitles.length; i++) {
			this.tableModel.addColumn(this.columnsTitles[i]);
		}

		// Seteamos el modelo a la tabla.
		this.table.setModel(this.tableModel);

		// Seteamos el ordenador de las filas de al tabla.
		this.tableSorter = new TableRowSorter<TableModel>(this.tableModel);
		this.table.setRowSorter(this.tableSorter);

		// Seteamos los anchos de las columnas.
		for (int i = 0; i < this.columnsWidths.length; i++) {
			this.table.getColumnModel().getColumn(i).setPreferredWidth(this.columnsWidths[i]);
		}
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
		ListPanel.log.trace("setTableSize");

		// Verificamos el ancho de la tabla.
		if (tableWidth != null && tableWidth >= 400) {
			ListPanel.tableWidth = tableWidth;
		} else {
			ListPanel.tableWidth = 400;
		}

		// Verificamos la latura de la tabla.
		if (tableHeight != null && tableHeight >= 200) {
			ListPanel.tableHeight = tableHeight;
		} else {
			ListPanel.tableHeight = 200;
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
		ListPanel.log.trace("setButtonSize");

		// Verificamos el ancho de la tabla.
		if (buttonWidth != null && buttonWidth >= 100) {
			ListPanel.buttonWidth = buttonWidth;
		} else {
			ListPanel.buttonWidth = 100;
		}

		// Verificamos la latura de la tabla.
		if (buttonHeight != null && buttonHeight >= 40) {
			ListPanel.buttonHeight = buttonHeight;
		} else {
			ListPanel.buttonHeight = 40;
		}

		// Seteamos las posiciones.
		this.setSize();
	}

	/**
	 * La función ecnargada de setear los tamaños y las posiciones de los diferentes elementos que tenemos dentro de esta ventana de listado de
	 * entidades.
	 */
	private void setSize() {
		ListPanel.log.trace("setSize");

		// Creamos el rectangulo principal de la ventana.
		Rectangle panel = new Rectangle(0, 0, 10 + ListPanel.tableWidth + 10 + ListPanel.buttonWidth + 10, 10 + ListPanel.tableHeight + 10 + 25 + 10);

		this.setPreferredSize(new Dimension(panel.width, panel.height));

		this.scrollPane.setBounds(10, 10, ListPanel.tableWidth, ListPanel.tableHeight);
		this.table.setBounds(0, 0, ListPanel.tableWidth, ListPanel.tableHeight);
		this.searchField.setBounds(10, 10 + ListPanel.tableHeight + 10, ListPanel.tableWidth, 25);

		this.newButton.setBounds(10 + ListPanel.tableWidth + 10, 10, ListPanel.buttonWidth, ListPanel.buttonHeight);
		this.modifyButton.setBounds(10 + ListPanel.tableWidth + 10, 10 + ListPanel.buttonHeight + 10, ListPanel.buttonWidth, ListPanel.buttonHeight);
		this.deleteButton.setBounds(10 + ListPanel.tableWidth + 10, 10 + ListPanel.buttonHeight + 10 + ListPanel.buttonHeight + 10,
				ListPanel.buttonWidth, ListPanel.buttonHeight);
		this.closeButton.setBounds(10 + ListPanel.tableWidth + 10, panel.height - 10 - ListPanel.buttonHeight, ListPanel.buttonWidth,
				ListPanel.buttonHeight);

		this.progressLabel.setBounds(10 + ListPanel.tableWidth + 10, panel.height - 10 - ListPanel.buttonHeight - 10 - ListPanel.buttonHeight,
				ListPanel.buttonWidth, ListPanel.buttonHeight);
	}

	/**
	 * La función encargada de leer desde la base de datos los elementos que vamos a desplegar y cargarlos dentro de la tabla.
	 */
	public void fillList() {
		// Entrada al LOG.
		ListPanel.log.trace("EntityListPanel fillList");

		// Creamos el proceso para recuperar las entidades desde la base de datos y cargarlas dentro de la tabla.
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					// Ejecutamos la función antes de cargar el listado.
					ListPanel.this.beforeLoadEntityList();

					// Antes de vaciar, quitamos el ordenador de la tabla.
					ListPanel.this.tableSorter.setRowFilter(null);

					// Vaciamos el contenido de la tabla.
					ListPanel.this.tableModel.getDataVector().clear();

					// Leemos los datos desde la base de datos y la cargamos dentro de la tabla.
					if (ListPanel.this.entityService != null) {
						ListPanel.this.entityList = ListPanel.this.entityService.findAll();
					} else {
						ListPanel.this.entityList = new ArrayList<E>();
					}

					// Cargamos el listado dentro de la tabla.
					ListPanel.this.loadListToTable();

					// Volvemos a setear el filtro de las filas.
					ListPanel.this.tableSorter.setRowFilter(ListPanel.this.tableRowFilter);

				} catch (Exception e) {
					e.printStackTrace();
					// Entrada al LOG.
					ListPanel.log.error("initList failed", e);
					JOptionPane.showMessageDialog(null, e.getMessage(), "Falla", JOptionPane.ERROR_MESSAGE);
				} finally {
					// Ejecutamos la función despues de cargar la lista.
					ListPanel.this.afterLoadEntityList();
				}
			}
		};
		thread.start();
	}

	/**
	 * Función encargada de realizar el filtrado de las filas de la tabla de acuerdo a la linea que esta cargada dentro de la tabla de clientes de
	 * acuerdo a cualquiera de los campos de las columnas.
	 */
	private void filterRows() {
		this.tableRowFilter = new RowFilter<Object, Integer>() {
			@Override
			public boolean include(Entry<? extends Object, ? extends Integer> entry) {
				// Solo si tenemos columnas para filtrar.
				if (ListPanel.this.columnsFilter != null) {

					for (int i = 0; i < entry.getValueCount(); i++) {
						// Solo para las columnas que queremos filtrar.
						if (ListPanel.this.filterColumn(i)) {
							if (entry.getStringValue(i).startsWith(ListPanel.this.searchField.getText())) {
								return true;
							}
						}
					}
				}
				return false;
			}
		};
		this.tableSorter.setRowFilter(this.tableRowFilter);
	}

	/**
	 * Función encargada de filtrar la las columnas sobre las que van a realizarse el filtrado.
	 * 
	 * @param index
	 *            El índice de la columna que va a controlarse si va a filtrarse o no.
	 * @return TRUE en caso de que la columna sea una de filtrado, en caso de que no sea filtrable, retornamos FALSE.
	 */
	private Boolean filterColumn(Integer index) {
		for (int i = 0; i < this.columnsFilter.length; i++) {
			if (index.equals(this.columnsFilter[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * La función que va a ejecutarse antes de cargar el listado de entidades dentro de la tabla.
	 * 
	 * @see ListPanel#afterLoadEntityList()
	 */
	protected void beforeLoadEntityList() {
		SwingResources.PROGRESS_LIST_ICON.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(SwingResources.PROGRESS_LIST_ICON);

		this.newButton.setEnabled(false);
		this.modifyButton.setEnabled(false);
		this.deleteButton.setEnabled(false);
		this.searchField.setEnabled(false);
	}

	/**
	 * La función que va a ejecutarse despues de la carga del listado de entidades dentro de la tabla.
	 * 
	 * @see ListPanel#beforeLoadEntityList()
	 */
	protected void afterLoadEntityList() {
		this.progressLabel.setIcon(null);

		this.newButton.setEnabled(true);
		this.modifyButton.setEnabled(true);
		this.deleteButton.setEnabled(true);
		this.searchField.setEnabled(true);
	}

	/**
	 * La función encargada de retornar el listado de los indices de las columnas que vamos a utilizar para filtrar.
	 * 
	 * @return El listado de los índices para filtrado.
	 */
	protected abstract Integer[] getColumnsFilter();

	/**
	 * La función encargada de retornar el titulo de la ventana donde vamos a desplegar este panel.
	 * 
	 * @return El titulo del panel.
	 */
	protected abstract String getListTitle();

	/**
	 * La función encargada de retornar si vamos a querer que se despliegue el boton de salir de la ventana que contiene el panel.
	 * 
	 * @return TRUE en caso de que querramos que el boton de "salir" se muestre en el panel, en caso de que no lo necesitemos, retornamos FALSE.
	 */
	protected abstract Boolean isCloseBottonVisible();

	/**
	 * La función encargada de retornar un listado de Strings que permite setear los nombres de las columnas que vamos a desplegar dentro de la
	 * ventana de listado de entidades. El orden del listado es el orden en el que van a desplegarse las mismas.
	 * 
	 * @return El listado de los nombre que vamos a desplegar dentro de la tabla de entidades.
	 */
	protected abstract String[] getColumnsTitles();

	/**
	 * La función encargada de retonar un listado de valores enteros que permite setear los anchos a las columnas
	 * 
	 * @return Los tamaños proporcionales de las columnas de la tabla.
	 */
	protected abstract Integer[] getColumnsWidths();

	/**
	 * La función encargada de cargar dentro de la tabla el listado de los elementos que vamos a desplegar.
	 */
	protected abstract void loadListToTable();

	/**
	 * La función encargada de retornar una nueva entidad que vamos a crear dentro del sistema.
	 * 
	 * @return Una entidad nueva para almacenar.
	 */
	protected abstract E getNewEntity();

	/**
	 * La función encargada de realizar un clonado de la entidad que queremos modificar para no editar la que originalmente tenemos dentro de este
	 * listado.
	 * 
	 * @param entity
	 *            La entidad que queremos clonar.
	 * @return La entidad cloonada.
	 */
	protected abstract E getCloneEntity(E entity);

	/**
	 * La función encargada de crear una nueva entidad a partir de la ventana de alta de las entidades.
	 */
	private void newEntity() {
		// Entrada al LOG.
		ListPanel.log.trace("EntityListPanel newEntity");

		// Abrimos una ventana de edición para crear una entidad nueva.
		if (this.formDialog != null) {
			this.formDialog = this.formDialog.getNewEntityDialog(this.getNewEntity());
			this.formDialog.setVisible(true);

			// Una vez que terminamos con la creación de la entidad y se acepto, volvemos a cargar todas desde la base de datos.
			if (this.formDialog.getCloseState() == WindowCloseState.COMMIT) {
				ListPanel.log.info("CREATE -> " + this.formDialog.getFormPanel().getEntity().toString());

				this.fillList();
			}
		} else {
			ListPanel.log.info("Entity create window don't define");
		}
	}

	/**
	 * La función encargada de desplegar la ventana de edición para editar la entidad que tenemos seleccionada dentro de la tabla desplegada.
	 */
	private void modifyEntity() {
		ListPanel.log.trace("modifyEntity");

		// Vemos si hay algun elemento seleccionado para editar.
		if (this.table.getSelectedRow() != -1) {
			// Recuperamos la entidad.
			E entity = this.getCloneEntity(this.entityList.get(this.table.convertRowIndexToModel(this.table.getSelectedRow())));

			if (entity != null) {
				ListPanel.log.info("MODIFY -> " + entity.toString());
			}

			// Abrimos una ventana de edición para modificar la entidad.
			if (this.formDialog != null) {
				this.formDialog = this.formDialog.getEditEntityDialog(entity);
				this.formDialog.setVisible(true);

				// Una vez que terminamos con la creación de la entidad y se acepto, volvemos a cargar todas desde la base de datos.
				if (this.formDialog.getCloseState() == WindowCloseState.COMMIT) {
					this.fillList();
				}
			} else {
				ListPanel.log.info("Entity edit window don't define");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento para modificarlo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * La función encargada de eliminar la entidad que tenemos seleccionada en el listado que desplegamos.
	 */
	private void deleteEntity() {
		ListPanel.log.trace("deleteEntity");

		// Vemos si hay algun elemento seleccionado para borrar.
		if (this.table.getSelectedRow() != -1) {
			// Recuperamos la entidad.
			E entity = this.entityList.get(this.table.convertRowIndexToModel(this.table.getSelectedRow()));

			// Preguntamos si queremos eliminar el elemento.
			if (JOptionPane.showConfirmDialog(this, "Desea borrar el elemento \"" + entity.toString() + "\"?", "Confirmación",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				ListPanel.log.trace("DELETE -> " + entity.toString());

				try {
					if (this.entityService != null) {
						this.entityService.delete(entity);
					}
					// Volvemos a cargar la tabla.
					this.fillList();

				} catch (Exception e) {
					ListPanel.log.error("delete entity failed ", e);
					JOptionPane.showMessageDialog(null, e.getMessage(), "Falla", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento para eliminarlo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * La función encargada de cerrar la ventana del listado.
	 */
	private void closeWindow() {
		ListPanel.log.trace("closeWindow");

		// Si hay un dialogo, es una ventana externa, sino la ocultamos nomas.
		if (this.entityListDialog != null) {
			this.entityListDialog.dispose();
		} else {
			this.getParent().setVisible(false);
		}
	}

	/**
	 * @see com.common.swing.model.GenericDialogOld#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		this.fillList();
		super.setVisible(visible);
	}

	/**
	 * Función encargada de setear el dialogo donde va a desplegarse este panel con el listado.
	 * 
	 * @param entityListDialog
	 *            El dialogo donde va a desplegarse este panel.
	 */
	public void setContainerDialog(EntityListDialog<E, PK> entityListDialog) {
		ListPanel.log.trace("setContainerDialog");

		// Si el dialogo contenedor no es nulo, lo seteamos.
		if (entityListDialog != null) {
			this.entityListDialog = entityListDialog;
			this.entityListDialog.setTitle(this.getListTitle());
		} else {
			ListPanel.log.info("setContainerDialog entityListDialog null");
		}
	}

	/**
	 * La función encargada de setear el servicio que vamos a ocupar para manipular las entidades.
	 * 
	 * @param entityService
	 *            El servicio para manipular las entidades.
	 */
	public void setEntityService(GenericService<E, PK> entityService) {
		ListPanel.log.trace("setEntityService");

		if (entityService != null) {
			this.entityService = entityService;
		} else {
			ListPanel.log.info("setEntityService entityService null");
		}
	}

	/**
	 * Función encargada de setearle el panel de edición de las entidades a este diálogo.
	 * 
	 * @param entityEditPanel
	 *            El panel de diálogo para edición de entidades.
	 */
	public void setEntityEditPanel(EntityEditPanel<E, PK> entityEditPanel) {
		ListPanel.log.trace("setEntityEditPanel");

		if (entityEditPanel != null) {
			if (this.formDialog != null) {
				// this.formDialog.setFormPanel(entityEditPanel);
			} else {
				ListPanel.log.info("EntityListPanel setEntityEditPanel entityEditDialog null");
			}
		} else {
			ListPanel.log.info("EntityListPanel setEntityEditPanel entityEditPanel null");
		}
	}

	/**
	 * La función que retorna el label del botón para agregar una nueva entidad al conjunto que ya tenemos dentro del sistema.
	 * 
	 * @return El label del botón de agregado de entidades.
	 */
	protected String getNewButtonLabel() {
		return "AGREGAR";
	}

	/**
	 * La función que retorna el label del botón para editar una entidad del conjunto que ya tenemos dentro del sistema.
	 * 
	 * @return El label del botón de editado de entidades.
	 */
	protected String getModifyButtonLabel() {
		return "EDITAR";
	}

	/**
	 * La función que retorna el label del botón para eliminar una entidad del conjunto que ya tenemos dentro del sistema.
	 * 
	 * @return El label del botón de borrado de entidades.
	 */
	protected String getDeleteButtonLabel() {
		return "BORRAR";
	}

	/**
	 * La función que retorna el label del botón para cerrar la ventana que despliega el listado.
	 * 
	 * @return El label del botón de cierre.
	 */
	protected String getCloseButtonLabel() {
		return "SALIR";
	}
}
