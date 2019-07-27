package br.com.codenation.acelera.dev.java.modelo;

import java.math.BigDecimal;

public class SolutionAnswerArquivoJson {

	private String token;
	private ArquivoJson arquivoJson;
	private StatusSolutionJson statusSolutionJson;

	public SolutionAnswerArquivoJson() {
	}

	public SolutionAnswerArquivoJson(ArquivoJson arquivoJson) {
		this.arquivoJson = arquivoJson;
	}

	public SolutionAnswerArquivoJson(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ArquivoJson getArquivoJson() {
		return arquivoJson;
	}
	
	public void setArquivoJson(ArquivoJson arquivoJson) {
		this.arquivoJson = arquivoJson;
	}

	public StatusSolutionJson getStatusSolutionJson() {
		return statusSolutionJson;
	}

	public void setStatusSolutionJson(StatusSolutionJson statusSolutionJson) {
		this.statusSolutionJson = statusSolutionJson;
	}
	
	/* ############################ Métodos De Ação ################### */
	
	public String toStringStatusSolution() {
		String strStatus = "";
		
		if (this.statusSolutionJson == null || arquivoJson == null)
			return strStatus = StatusSolutionJson.toString(StatusSolutionJson.NAO_INFORMADO);	
		
		
		return strStatus;
	}

	public boolean isTokenValido() {
		return (this.token != null || this.token != null && !token.isEmpty());
	}

	public void processarStatusSolucao() {
		boolean existsArquivoJson = (this.arquivoJson != null);

		if (this.statusSolutionJson.isFinalizado())
			return;

		if (this.statusSolutionJson == null || (this.statusSolutionJson != null && !existsArquivoJson)) {
			this.statusSolutionJson = StatusSolutionJson.NAO_INFORMADO;
			return;
		}

		if (existsArquivoJson && !this.arquivoJson.isSolucionado() && !this.arquivoJson.getToken().isEmpty()
				&& this.arquivoJson.getNumeroCasas() != null && this.arquivoJson.getNumeroCasas().intValue() > 0
				&& this.arquivoJson.getDecifrado().isEmpty() && this.arquivoJson.getResumoCriptografico().isEmpty())
			this.statusSolutionJson = StatusSolutionJson.IMPORTADO;

		if (existsArquivoJson && !this.arquivoJson.isSolucionado() && this.arquivoJson.getDecifrado().isEmpty())
			this.statusSolutionJson = StatusSolutionJson.DECIFRADO;

		if (existsArquivoJson && !this.arquivoJson.isSolucionado()
				&& !this.arquivoJson.getResumoCriptografico().isEmpty())
			this.statusSolutionJson = StatusSolutionJson.FINALIZADO;
	}

}
