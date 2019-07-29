package br.com.codenation.acelera.dev.java.modelo;

public class SolutionAnswerArquivoJson {

	private String token;
	private ArquivoJson arquivoJson;
	private StatusSolutionJson statusSolutionJson;
	private Integer score;
	
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
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	/* ############################ Métodos De Ação ########################### */

	public String toStringStatusSolution() {
		if (this.statusSolutionJson == null || arquivoJson == null) {
			this.processarStatusSolucao();
			return this.toStringStatusSolution();
		}

		return this.statusSolutionJson.toString();
	}

	public boolean isTokenValido() {
		return (this.token != null || this.token != null && !token.isEmpty());
	}

	public boolean isValidoEImportado() {
		return (isArquivoJsonExists() && this.arquivoJson.isTokenValido() && this.arquivoJson.isNumeroCasasValido()
				&& this.arquivoJson.isCifradoValido());
	}

	public boolean isSolucionado() {
		return (isFinalizado() && this.arquivoJson.isSolucionado());
	}

	public boolean isImportado() {
		return (isArquivoJsonExists() && this.statusSolutionJson != null && this.statusSolutionJson.isImportado());
	}

	public boolean isCifrado() {
		return (!isImportado() && this.statusSolutionJson.isCifrado());
	}

	public boolean isDecifrado() {
		return (!isCifrado() && this.statusSolutionJson.isDecifrado());
	}

	public boolean isFinalizado() {
		return (!isDecifrado() && this.statusSolutionJson.isFinalizado());
	}

	public boolean isArquivoJsonExists() {
		return (this.arquivoJson != null);
	}

	public void gerarConteudoDecifrado(String valorDecifrado) {
		if (isArquivoJsonExists())
			this.arquivoJson.setDecifrado(valorDecifrado);
	}

	public void gerarConteudoResumoCriptografado(String valorResumoCriptografico) {
		if (isArquivoJsonExists())
			this.arquivoJson.setResumoCriptografico(valorResumoCriptografico);
	}

	public void processarStatusSolucao() {
		if (this.statusSolutionJson == null)
			this.statusSolutionJson = StatusSolutionJson.NAO_INFORMADO;

		if (this.statusSolutionJson.isFinalizado()) {
			this.statusSolutionJson = StatusSolutionJson.ENVIADO;
			return;
		}

		if (this.statusSolutionJson != null && !isArquivoJsonExists()) {
			this.statusSolutionJson = StatusSolutionJson.NAO_INFORMADO;
			return;
		}

		if (isSolucionado()) {
			this.statusSolutionJson = StatusSolutionJson.FINALIZADO;
			return;
		}

		if (isArquivoJsonExists() && isValidoEImportado())
			this.statusSolutionJson = StatusSolutionJson.IMPORTADO;
		
		if (isArquivoJsonExists() && isValidoEImportado() && isImportado())
			this.statusSolutionJson = StatusSolutionJson.CIFRADO;

		if (isArquivoJsonExists() && isValidoEImportado() && isCifrado())
			this.statusSolutionJson = StatusSolutionJson.CIFRADO;

		if (isArquivoJsonExists() && this.arquivoJson.isDecifradoValido())
			this.statusSolutionJson = StatusSolutionJson.DECIFRADO;

		if (isArquivoJsonExists() && this.arquivoJson.isResumoCriptograficoValido()) {
			this.statusSolutionJson = StatusSolutionJson.FINALIZADO;
		}
	}
}
