package br.com.codenation.acelera.dev.java.exception;

public class NegocioException extends Exception {
	
	private static final long serialVersionUID = -8239148831324403152L;

	public NegocioException(String message) {
		super(message);
	}

	public NegocioException(String message, Throwable causa) {
		super(message, causa);
	}

	public NegocioException(Throwable causa) {
		super(causa);
	}
}
