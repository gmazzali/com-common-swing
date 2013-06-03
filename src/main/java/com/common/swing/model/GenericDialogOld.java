package com.common.swing.model;

import javax.swing.JComponent;
import javax.swing.JDialog;

import com.common.swing.listener.FormCloseListener;

/**
 * La clase que nos va a permitir crear una ventana genérica para poder ubicarlas dentro de la pantalla de acuerdo al padre de esta.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class GenericDialogOld extends JDialog implements FormCloseListener {

	private static final long serialVersionUID = 3101270933375284068L;
	
	/**
	 * La ventana padre de esta.
	 */
	protected JComponent parent;
	/**
	 * El estado en el que se cierra una ventana.
	 */
	protected WindowCloseState closeStateListener;

	/**
	 * El constructor por omisión de una ventana de dialogo.
	 */
	public GenericDialogOld() {
		super();
		this.closeStateListener = WindowCloseState.UNDEFINED;
	}

	/**
	 * La función encargada de setear la ventana padre de esta.
	 * 
	 * @param entityListPanel
	 *            La ventana padre de esta.
	 */
	public void setParent(JComponent entityListPanel) {
		this.parent = entityListPanel;
	}

	/**
	 * La función encargada de retornar la ventana padre de esta.
	 * 
	 * @return La ventana padre de esta.
	 */
	@Override
	public JComponent getParent() {
		return this.parent;
	}

	/**
	 * La función encargada de setear la ventana como cancelada para su cierre.
	 */
	public void cancel() {
		this.closeStateListener = WindowCloseState.REJECT;
		this.dispose();
	}

	/**
	 * La función encargada de setear la ventana como confirmada para su cierre.
	 */
	public void confirm() {
		this.closeStateListener = WindowCloseState.COMMIT;
		this.dispose();
	}

	/**
	 * La función encargada de setear el estado de visibilidad de esta ventana, a la vez que la posiciona centrada sobre la ventana padre de esta.
	 * 
	 * @param b
	 *            El valor booleano que nos indica el estado de visibilidad que queremos para esta ventana.
	 * 
	 * @see java.awt.Dialog#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean b) {
		this.setLocationRelativeTo(this.parent);
		super.setVisible(b);
	}

	/**
	 * @see com.common.swing.listener.FormCloseListener#getCloseState()
	 */
	@Override
	public WindowCloseState getCloseState() {
		return this.closeStateListener;
	}
}
