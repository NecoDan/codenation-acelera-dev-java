package br.com.codenation.acelera.dev.java.exception;

public class ControllerException extends Exception {

	private static final long serialVersionUID = -8239148831324403152L;

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(String message, Throwable causa) {
		super(message, causa);
	}

	public ControllerException(Throwable causa) {
		super(causa);
	}
}
