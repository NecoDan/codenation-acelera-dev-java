package br.com.codenation.acelera.dev.java.exception;

public class SolicitarArquivoException extends Exception {

	private static final long serialVersionUID = -8239148831324403152L;

	public SolicitarArquivoException() {
		super("Inválido! Para solicitar arquivo é necessário informar um token de acesso.");
	}

	public SolicitarArquivoException(String mensagem) {
		super(mensagem);
	}
}
