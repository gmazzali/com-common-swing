package com.common.swing.crud.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.Serializable;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.common.swing.listener.FormCloseListener;
import com.common.util.model.Persistence;

/**
 * La clase que define una ventana de formulario para las entidades que vamos a manipular dentro de un sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            Las entidades que vamos a manipular dentro de esta ventana.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a editar.
 */
public class GenericFormDialog<E extends Persistence<PK>, PK extends Serializable> extends JDialog implements FormCloseListener {

	private static final long serialVersionUID = 3101270933375284068L;

	/**
	 * El logger de la ventana.
	 */
	private static final Logger log = Logger.getLogger(GenericFormDialog.class);

	/**
	 * El estado en el que se cierra una ventana.
	 */
	protected WindowCloseState closeStateListener;
	/**
	 * El panel de edición de las entidades.
	 */
	protected GenericFormPanel<E, PK> formPanel;

	/**
	 * El constructor por omisión de una ventana de dialogo.
	 */
	public GenericFormDialog() {
		super();
		GenericFormDialog.log.trace("create");

		this.closeStateListener = WindowCloseState.UNDEFINED;
		this.init();
	}

	/**
	 * La función encargada de inicializar los elementos de la ventana.
	 */
	private void init() {
		GenericFormDialog.log.trace("init");

		this.setTitle("FormDialog");
		this.setModal(true);
		this.setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 100));
		this.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel label = new JLabel("EMPTY PANEL");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 37));
		label.setBounds(10, 10, 380, 79);
		panel.add(label);

		this.pack();
	}

	/**
	 * La función encargada de marcar la ventana como confirmada para su cierre.
	 */
	public void commit() {
		this.closeStateListener = WindowCloseState.COMMIT;
		this.dispose();
	}

	/**
	 * La función encargada de marcar la ventana como cancelada para su cierre.
	 */
	public void reject() {
		this.closeStateListener = WindowCloseState.REJECT;
		this.dispose();
	}

	/**
	 * La función encargada de cargar la ventana para crear una nueva entidad y almacenarla dentro de la base de datos.
	 * 
	 * @param entity
	 *            La entidad que vamos a crear dentro de la base de datos.
	 * @return La ventana necesaria para la creación de una nueva entidad.
	 */
	public GenericFormDialog<E, PK> createNewEntityDialog(E entity) {
		GenericFormDialog.log.trace("createNewEntityDialog");

		if (entity != null && this.formPanel != null) {
			this.setTitle(this.formPanel.getNewTitle());
			this.formPanel.setEntity(entity);
			this.formPanel.emptyFields();
		}

		return this;
	}

	/**
	 * La función encargada de cargar la ventana para la edición de una entidad que ya tenemos almacenada dentro de la base de datos.
	 * 
	 * @param entity
	 *            La entidad que vamos a modificar dentro de la base de datos.
	 * @return La ventana que vamos a utilizar para editar la entidad.
	 */
	public GenericFormDialog<E, PK> createEditEntityDialog(E entity) {
		GenericFormDialog.log.trace("createEditEntityDialog");

		if (entity != null && this.formPanel != null) {
			this.setTitle(this.formPanel.getEditTitle());
			this.formPanel.setEntity(entity);
			this.formPanel.loadFieldsFromEntity();
		}

		return this;
	}

	/**
	 * Función encargada de cargarle el panel de edición de las entidades a este diálogo.
	 * 
	 * @param formPanel
	 *            El panel de diálogo para edición de entidades.
	 */
	public void setFormPanel(GenericFormPanel<E, PK> formPanel) {
		GenericFormDialog.log.trace("setEntityEditPanel");

		if (formPanel != null) {
			// Sacamos todos los paneles del dialogo.
			this.getContentPane().removeAll();

			// Cargamos el valor de la variable y lo cargamos al nuevo panel.
			this.formPanel = formPanel;
			this.formPanel.setFormDialog(this);
			this.getContentPane().add(this.formPanel, BorderLayout.CENTER);

			// Redimensionamos el panel.
			this.pack();
		}
	}

	/**
	 * @see com.common.swing.form.model.FormCloseListener#getCloseState()
	 */
	@Override
	public WindowCloseState getCloseState() {
		return this.closeStateListener;
	}

	/**
	 * La función encargada de retornar el panel de edición de las entidades.
	 * 
	 * @return El panel de edición de las entidades.
	 */
	public GenericFormPanel<E, PK> getFormPanel() {
		return this.formPanel;
	}
}