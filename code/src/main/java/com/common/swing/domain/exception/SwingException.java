package com.common.swing.domain.exception;

import com.common.util.domain.exception.ViewException;
import com.common.util.domain.exception.error.ErrorDetail;
import com.common.util.domain.exception.error.Errors;

/**
 * La excepcion que va a lanzarse en caso de que ocurra un fallo con alg�n componente de SWING.
 * 
 * @since 23/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class SwingException extends ViewException {
	private static final long serialVersionUID = 1L;

	/**
	 * El constructor de una instancia de {@link SwingException} que no recibe par�metros.
	 */
	public SwingException() {
		super();
	}

	/**
	 * El constructor de una instancia de {@link SwingException} que recibe como par�metro un elemento {@link Throwable} para mantener el problema que
	 * produjo el lanzamiento de esta {@link SwingException}.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepci�n.
	 */
	public SwingException(Throwable cause) {
		super(cause);
	}

	/**
	 * El constructor de una instancia de {@link SwingException} que recibe como par�metro el conjunto de {@link Errors} que vamos a contener.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepci�n.
	 */
	public SwingException(Errors errors) {
		super(errors);
	}

	/**
	 * El constructor de una instancia de {@link SwingException} que recibe como par�metro el {@link Throwable} para mantener el problema que produjo
	 * el lanzamiento de esta {@link SwingException} y un un mensaje de {@link ErrorDetail} que vamos a crear en el momento.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepci�n.
	 * @param defaultMessage
	 *            El mensaje por omisi�n del detalle del error.
	 * @param keyMessage
	 *            La clave del mensaje del detalle del error.
	 * @param parameters
	 *            Los par�metros que requerimos para el detalle del error.
	 */
	public SwingException(Throwable cause, String defaultMessage, String keyMessage, Object... parameters) {
		super(cause, defaultMessage, keyMessage, parameters);
	}

	/**
	 * El constructor de una instancia de {@link SwingException} que recibe como par�metro un mensaje de {@link ErrorDetail} que vamos a crear en el
	 * momento.
	 * 
	 * @param defaultMessage
	 *            El mensaje por omisi�n del detalle del error.
	 * @param keyMessage
	 *            La clave del mensaje del detalle del error.
	 * @param parameters
	 *            Los par�metros que requerimos para el detalle del error.
	 */
	public SwingException(String defaultMessage, String keyMessage, Object... parameters) {
		super(defaultMessage, keyMessage, parameters);
	}
}