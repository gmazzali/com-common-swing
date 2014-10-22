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
 * Permite establecer una acci�n para una tabla.
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
	 * Es escuchador de la acci�n.
	 */
	private TableListener<E> tableListener;
	/**
	 * El bot�n de la acci�n.
	 */
	private JButton button;
	/**
	 * El decorador del bot�n.
	 */
	private ButtonDecorator buttonDecorator;

	/**
	 * El constructor de una acci�n de una tabla que recibe la misma.
	 * 
	 * @param table
	 *            La tabla sobre la que vamos a crear la acci�n.
	 * @param tableListener
	 *            El escuchador de la acci�n.
	 * @param buttonDecorator
	 *            El decorador del bot�n.
	 */
	public TableAction(BaseTable<E> table, TableListener<E> tableListener, ButtonDecorator buttonDecorator) {
		this.table = table;
		this.tableListener = tableListener;
		this.buttonDecorator = buttonDecorator;
	}

	/**
	 * Permite crear un bot�n dado de acuerdo al escuchador y el decorador recibido.
	 * 
	 * @return El bot�n creado.
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
	 * Permite recuperar el bot�n de la acci�n.
	 * 
	 * @return El bot�n de la acci�n.
	 */
	public JButton getButton() {
		return button;
	}

	/**
	 * Permite definir si la acci�n va a estar activa de acuerdo a la entidad que tenemos seleccionada.
	 * 
	 * @param entities
	 *            Las entidades que tenemos seleccionadas dentro del la tabla.
	 * @return <code>true</code> en caso de que la acci�n este habilitada para las entidades, en caso contrario, retorna <code>false</code>.
	 */
	public boolean isVivibleAction(Collection<E> entities) {
		return true;
	}
}