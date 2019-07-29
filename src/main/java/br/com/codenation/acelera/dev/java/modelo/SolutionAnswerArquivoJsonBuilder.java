package br.com.codenation.acelera.dev.java.modelo;

public class SolutionAnswerArquivoJsonBuilder {

	private SolutionAnswerArquivoJson solutionAnswerArquivoJson;

	private SolutionAnswerArquivoJsonBuilder() {
		this.solutionAnswerArquivoJson = new SolutionAnswerArquivoJson();
		this.iniciadoPeloStatusNaoInformado();
	}

	private SolutionAnswerArquivoJsonBuilder(ArquivoJson arquivoJson) {
		this.solutionAnswerArquivoJson = new SolutionAnswerArquivoJson(arquivoJson);
		this.iniciadoPeloStatusNaoInformado();
	}
	
	private SolutionAnswerArquivoJsonBuilder(String token) {
		this.solutionAnswerArquivoJson = new SolutionAnswerArquivoJson(token);
		this.iniciadoPeloStatusNaoInformado();
	}

	public static final SolutionAnswerArquivoJsonBuilder umNovoSolutionAnswerArquivoJson() {
		SolutionAnswerArquivoJsonBuilder solutionBuilder = new SolutionAnswerArquivoJsonBuilder();
		return solutionBuilder;
	}
	
	public static final SolutionAnswerArquivoJsonBuilder umNovoSolutionAnswerArquivoJsonAPartir(String token) {
		SolutionAnswerArquivoJsonBuilder solutionBuilder = new SolutionAnswerArquivoJsonBuilder(token);
		return solutionBuilder;
	}

	public static final SolutionAnswerArquivoJsonBuilder umNovoSolutionAnswerArquivoJson(ArquivoJson arquivoJson) {
		SolutionAnswerArquivoJsonBuilder solutionBuilder = new SolutionAnswerArquivoJsonBuilder(arquivoJson);
		return solutionBuilder;
	}

	public SolutionAnswerArquivoJsonBuilder comORespectivoToken(String token) {
		this.solutionAnswerArquivoJson.setToken(token);
		return this;
	}
	
	public SolutionAnswerArquivoJsonBuilder contendoOArquivo(ArquivoJson arquivoJson) {
		this.solutionAnswerArquivoJson.setArquivoJson(arquivoJson);
		return this;
	}

	public SolutionAnswerArquivoJsonBuilder carregadoPeloStatus(StatusSolutionJson statusSolutionJson) {
		this.solutionAnswerArquivoJson.setStatusSolutionJson(statusSolutionJson);
		return this;
	}

	public SolutionAnswerArquivoJsonBuilder iniciadoPeloStatusNaoInformado() {
		this.solutionAnswerArquivoJson.setStatusSolutionJson(StatusSolutionJson.NAO_INFORMADO);
		return this;
	}

	public SolutionAnswerArquivoJsonBuilder iniciadoPeloStatusCifrado() {
		this.solutionAnswerArquivoJson.setStatusSolutionJson(StatusSolutionJson.CIFRADO);
		return this;
	}

	public SolutionAnswerArquivoJsonBuilder iniciadoPeloStatusDecifrado() {
		this.solutionAnswerArquivoJson.setStatusSolutionJson(StatusSolutionJson.DECIFRADO);
		return this;
	}

	public SolutionAnswerArquivoJsonBuilder iniciadoPeloStatusFinalizado() {
		this.solutionAnswerArquivoJson.setStatusSolutionJson(StatusSolutionJson.FINALIZADO);
		return this;
	}

	public SolutionAnswerArquivoJsonBuilder enviadoFinalizado() {
		this.solutionAnswerArquivoJson.setStatusSolutionJson(StatusSolutionJson.ENVIADO);
		return this;
	}

	public SolutionAnswerArquivoJson constroi() {
		this.solutionAnswerArquivoJson.processarStatusSolucao();
		return this.solutionAnswerArquivoJson;
	}
}
