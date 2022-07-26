package com.example.demo.exception;

/**
 * La clase {@code AdminException} es una clase de manejo de excepciones genéricas (unchecked exceptions)
 * 
 * @author borja.gonzalez
 * @version 0.0.2, 11/04/2019
 * @since 0.0.2
 *
 */
public class DemoException extends RuntimeException {

	private static final long serialVersionUID = -9150089265815202595L;

	/**
	 * Constructor de {@code FirmaException} sin mensajes de error.
	 */
	public DemoException() {
		super();
	}

	/**
	 * Constructor de {@code FirmaException} especificando un mensaje de error.
	 * 
	 * @param message el mensaje de error (el cual se guarda para recuperar luego con el método {@link #getMessage()}).
	 */
	public DemoException(String message) {
		super(message);
	}

	/**
	 * Constructor de {@code FirmaException} especificando la causa del error.
	 * 
	 * @param cause la causa (la cual se guarda para recuperar luego con el método {@link #getCause()}).
	 */
	public DemoException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor de {@code FirmaException} especificando mensaje y causa del error.
	 * 
	 * @param message el mensaje de error (el cual se guarda para recuperar luego con el método {@link #getMessage()}).
	 * @param cause   la causa (la cual se guarda para recuperar luego con el método {@link #getCause()}).
	 */
	public DemoException(String message, Throwable cause) {
		super(message, cause);
	}
}
