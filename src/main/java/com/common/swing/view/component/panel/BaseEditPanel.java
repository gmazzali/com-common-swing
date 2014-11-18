package com.common.swing.view.component.panel;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.common.swing.domain.exception.SwingException;
import com.common.swing.view.bean.EditBean;
import com.common.util.business.tool.VerifierUtil;
import com.crud.swing.view.form.util.EditType;

/**
 * La clase que permite definir un panel donde vamos a desplegar los campos para la edición o visualización de datos.
 * 
 * @since 22/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <B>
 *            El bean de edición que vamos a manipular dentro de este panel.
 */
public abstract class BaseEditPanel<B extends EditBean> extends JPanel implements BasePanel {
	private static final long serialVersionUID = 1L;

	/**
	 * El tipo de panel.
	 */
	private EditType type;
	/**
	 * El bean que vamos a editar.
	 */
	private B bean;

	/**
	 * Permite inicializar los componentes del panel de edición.
	 */
	protected void init() {
		this.removeAll();
		this.setPreferredSize(new Dimension(this.getWidthSize(), this.getHeightSize()));
		this.afterInit();
	}

	/**
	 * Se encarga de validar y crear el bean que estamos editando dentro de este panel.
	 * 
	 * @return El bean que acabamos de editar dentro del panel.
	 * @throws SwingException
	 *             En caso de que algún campo no cumpla las condiciones necesarias.
	 */
	public B getBean() throws SwingException {
		VerifierUtil.checkNotNull(this.bean, "The bean cannot be null");
		this.fromFieldToBean(this.bean);
		return this.bean;
	}

	/**
	 * Se encarga de cargar el bean dentro del panel.
	 * 
	 * @param bean
	 *            El bean que vamos a cargar dentro de la ventana.
	 * @throws SwingException
	 *             En caso de algún problema al cargar el bean al panel.
	 */
	public void setBean(B bean) throws SwingException {
		VerifierUtil.checkNotNull(bean, "The bean cannot be null");
		this.bean = bean;
		this.fromBeanToField(this.bean);
	}

	/**
	 * Permite recuperar el tipo de formulario.
	 * 
	 * @return El tipo de formulario.
	 */
	public EditType getType() {
		return type;
	}

	/**
	 * Permite cargar el tipo de panel.
	 * 
	 * @param type
	 *            El tipo de panel.
	 */
	public void setType(EditType type) {
		this.type = type;
	}

	/**
	 * Permite definir si el panel de edición va a ser de solo lectura o de edición.
	 * 
	 * @param readOnly
	 *            El valor booleano que define el estado del panel. Si se recibe <code>true</code>, el panel va a setearse para visualización
	 *            solamente, en caso que sea <code>false</code> el panel se setea para carga de datos.
	 */
	public void setReadOnly(boolean readOnly) {
		if (readOnly) {
			this.disabled();
		} else {
			this.enabled();
		}
	}

	@Override
	public void enabled() {
		this.setEnabled(true);
	}

	@Override
	public void disabled() {
		this.setEnabled(false);
	}

	/**
	 * Permite terminar de configurar el panel.
	 */
	protected abstract void afterInit();

	/**
	 * Permite cargar el bean que recibimos con los datos que tenemos dentro de los campos de edición de la ventana.
	 * 
	 * @param bean
	 *            El bean que vamos a cargar.
	 * @throws SwingException
	 *             En caso de que algún campo no cumpla las condiciones necesarias.
	 */
	protected abstract void fromFieldToBean(B bean) throws SwingException;

	/**
	 * Permite cargar los campos del panel de edición con los valores que tenemos dentro del bean.
	 * 
	 * @param bean
	 *            El bean que vamos a usar para cargar los campos.
	 * @throws SwingException
	 *             En caso de algún problema al cargar el bean al panel.
	 */
	protected abstract void fromBeanToField(B bean) throws SwingException;
}