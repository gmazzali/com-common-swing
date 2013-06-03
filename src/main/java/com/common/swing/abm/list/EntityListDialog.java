package com.common.swing.abm.list;

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
 * La clase que nos permite definir un dialogo para mostrar un listado de las entidades que vamos a tener dentro del sistema para poder controlarlas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de las entidades que vamos a manipular.
 * @param <PK>
 *            La clase que va a hacer de clave primaria de las entidades que vamos a manipular.
 */
public class EntityListDialog<E extends Persistence<PK>, PK extends Serializable> extends GenericDialogOld {

	private static final long serialVersionUID = 8662949359810446241L;

	/**
	 * El logger de la ventana.
	 */
	private static final Logger log = Logger.getLogger(EntityListDialog.class);

	/**
	 * El panel que despliega el listado de las entidades.
	 */
	protected EntityListPanel<E, PK> entityListPanel;

	/**
	 * El cosntructor de un dialogo con un panel en blanco.
	 */
	public EntityListDialog() {
		super();
		EntityListDialog.log.trace("EntityListDialog create");

		this.init();
	}

	/**
	 * La función encargada de inicializar los elementos de la ventana.
	 */
	private void init() {
		EntityListDialog.log.trace("EntityListDialog init");

		this.setTitle("EntityListDialog");
		this.setModal(true);
		this.setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(400, 100));
		this.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel label = new JLabel("EMPTY PANEL");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 37));
		label.setBounds(10, 11, 380, 78);
		panel.add(label);

		this.pack();
	}

	/**
	 * La función encargada de setear el panel donde van a desplegarse el listado de las entidades.
	 * 
	 * @param entityListPanel
	 *            El panel del listado de las entidades.
	 */
	public void setEntityListPanel(EntityListPanel<E, PK> entityListPanel) {
		// Entrada al LOG.
		EntityListDialog.log.trace("EntityListDialog setEntityListPanel");

		if (entityListPanel != null) {

			// Sacamos todos los paneles.
			this.getContentPane().removeAll();

			// Agregamos el nuevo panel.
			this.entityListPanel = entityListPanel;
			this.entityListPanel.setContainerDialog(this);
			this.getContentPane().add(this.entityListPanel, BorderLayout.CENTER);

			// Redimensionamos el panel.
			this.pack();
		} else {
			EntityListDialog.log.info("EntityListDialog setEntityListPanel entityListPanel null");
		}
	}

	/**
	 * @see com.common.swing.model.GenericDialogOld#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		if (this.entityListPanel != null) {
			this.entityListPanel.setVisible(visible);
		}
		super.setVisible(visible);
	}

	/**
	 * La función encargada de retornar el panel que contiene el listado de las entidades.
	 * 
	 * @return El panel que contiene el listado de las entidades.
	 */
	public EntityListPanel<E, PK> getEntityListPanel() {
		return this.entityListPanel;
	}
}
