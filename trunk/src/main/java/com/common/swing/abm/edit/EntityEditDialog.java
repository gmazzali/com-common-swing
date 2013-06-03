package com.common.swing.abm.edit;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.common.swing.model.GenericDialogOld;
import com.common.util.model.Persistence;

/**
 * La ventana que nos permite desplegar un panel de edición de entidades para dar de alta nuevas, modificar ya almacenadas o visualizar sus atributos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            Las entidades que vamos a manipular dentro de esta ventana.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a editar.
 */
public class EntityEditDialog<E extends Persistence<PK>, PK extends Serializable> extends GenericDialogOld {

	private static final long serialVersionUID = -8399231939626318377L;

	/**
	 * El logger de la ventana.
	 */
	private static final Logger log = Logger.getLogger(EntityEditDialog.class);

	/**
	 * El panel de edición de las entidades.
	 */
	private EntityEditPanel<E, PK> entityEditPanel;

	/**
	 * El constructor de una instancia de una ventana de edición de entidades.
	 */
	public EntityEditDialog() {
		super();
		EntityEditDialog.log.trace("EntityEditDialog create");

		this.init();
	}

	/**
	 * La función encargada de inicializar los elementos de la ventana.
	 */
	private void init() {
		EntityEditDialog.log.trace("EntityEditDialog init");

		this.setTitle("EntityEditDialog");
		this.setModal(true);
		this.setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(null);
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
	 * La función encargada de setear la ventana para crear una nueva entidad y almacenarla dentro de la base de datos.
	 * 
	 * @param entity
	 *            La entidad que vamos a crear dentro de la base de datos.
	 * @return La ventana necesaria para la creación de una nueva entidad.
	 */
	public EntityEditDialog<E, PK> getNewEntityDialog(E entity) {
		// Entrada al LOG.
		EntityEditDialog.log.trace("EntityEditDialog getNewEntityEditDialog");

		if (entity != null && this.entityEditPanel != null) {
			this.setTitle(this.entityEditPanel.getNewTitle());
			this.entityEditPanel.setEntity(entity);
			this.entityEditPanel.emptyFields();
		}

		return this;
	}

	/**
	 * La función encargada de setear la ventana para la edición de una entidad que ya tenemos almacenada dentro de la base de datos.
	 * 
	 * @param entity
	 *            La entidad que vamos a modificar dentro de la base de datos.
	 * @return La ventana que vamos a utilizar para editar la entidad.
	 */
	public EntityEditDialog<E, PK> getEditEntityDialog(E entity) {
		// Entrada al LOG.
		EntityEditDialog.log.trace("EntityEditDialog getEditEntityEditDialog");

		if (entity != null && this.entityEditPanel != null) {
			this.setTitle(this.entityEditPanel.getEditTitle());
			this.entityEditPanel.setEntity(entity);
			this.entityEditPanel.loadFieldsFromEntity();
		}

		return this;
	}

	/**
	 * Función encargada de setearle el panel de edición de las entidades a este diálogo.
	 * 
	 * @param entityEditPanel
	 *            El panel de diálogo para edición de entidades.
	 */
	public void setEntityEditPanel(EntityEditPanel<E, PK> entityEditPanel) {
		// Entrada al LOG.
		EntityEditDialog.log.trace("EntityEditDialog setEntityEditPanel");

		if (entityEditPanel != null) {
			// Sacamos todos los paneles del dialogo.
			this.getContentPane().removeAll();

			// Seteamos el valor de la variable y lo cargamos al nuevo panel.
			this.entityEditPanel = entityEditPanel;
			this.entityEditPanel.setContainerDialog(this);
			this.getContentPane().add(this.entityEditPanel, BorderLayout.CENTER);

			// Redimensionamos el panel.
			this.pack();
		}
	}

	/**
	 * La función encargada de retornar el panel de edición de las entidades.
	 * 
	 * @return El panel de edición de las entidades.
	 */
	public EntityEditPanel<E, PK> getEntityEditPanel() {
		return this.entityEditPanel;
	}
}
