package com.common.swing.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Collection;

import javax.swing.JButton;

import com.common.swing.view.component.table.BaseTable;
import com.common.swing.view.decorator.ButtonDecorator;
import com.common.swing.view.event.TableEvent;
import com.common.swing.view.listener.TableListener;

/**
 * Permite establecer una acción para una tabla.
 * 
 * @since 20/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de las entidades que vamos a cargar dentro de la tabla.
 */
public class TableAction<E extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * La tabla base.
	 */
	private BaseTable<E> table;
	/**
	 * Es escuchador de la acción.
	 */
	private TableListener<E> tableListener;
	/**
	 * El botón de la acción.
	 */
	private JButton button;
	/**
	 * El decorador del botón.
	 */
	private ButtonDecorator buttonDecorator;

	/**
	 * El constructor de una acción de una tabla que recibe la misma.
	 * 
	 * @param table
	 *            La tabla sobre la que vamos a crear la acción.
	 * @param tableListener
	 *            El escuchador de la acción.
	 * @param buttonDecorator
	 *            El decorador del botón.
	 */
	public TableAction(BaseTable<E> table, TableListener<E> tableListener, ButtonDecorator buttonDecorator) {
		this.table = table;
		this.tableListener = tableListener;
		this.buttonDecorator = buttonDecorator;
	}

	/**
	 * Permite crear un botón dado de acuerdo al escuchador y el decorador recibido.
	 * 
	 * @return El botón creado.
	 */
	public JButton createButton() {
		if (this.button == null) {
			this.button = new JButton();
			this.buttonDecorator.decorateButton(this.button);
			this.button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					tableListener.fireEvent(new TableEvent<E>(table).addAllEntity(table.getSelectedValues()));
				}
			});
		}
		return this.button;
	}

	/**
	 * Permite recuperar el botón de la acción.
	 * 
	 * @return El botón de la acción.
	 */
	public JButton getButton() {
		return button;
	}

	/**
	 * Permite definir si la acción va a estar activa de acuerdo a la entidad que tenemos seleccionada.
	 * 
	 * @param entities
	 *            Las entidades que tenemos seleccionadas dentro del la tabla.
	 * @return <code>true</code> en caso de que la acción este habilitada para las entidades, en caso contrario, retorna <code>false</code>.
	 */
	public boolean isVivibleAction(Collection<E> entities) {
		return true;
	}
}