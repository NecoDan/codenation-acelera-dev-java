package br.com.codenation.acelera.dev.java.exception;

public class RepositoryExcpetion extends Exception {

	private static final long serialVersionUID = -8239148831324403152L;

	public RepositoryExcpetion(String message) {
		super(message);
	}

	public RepositoryExcpetion(String message, Throwable causa) {
		super(message, causa);
	}

	public RepositoryExcpetion(Throwable causa) {
		super(causa);
	}

}
