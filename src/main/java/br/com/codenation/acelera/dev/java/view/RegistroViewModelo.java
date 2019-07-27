package br.com.codenation.acelera.dev.java.view;

import br.com.codenation.acelera.dev.java.modelo.SolicitaArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;

public class RegistroViewModelo {

	private SolicitaArquivoJson solicitaArquivoJSON;
	private SolutionAnswerArquivoJson solutionAnswerArquivoJson;

	public RegistroViewModelo() {
		this.solicitaArquivoJSON = new SolicitaArquivoJson();
		this.solutionAnswerArquivoJson = new SolutionAnswerArquivoJson();
	}

	public SolicitaArquivoJson getSolicitaArquivoJSON() {
		return solicitaArquivoJSON;
	}

	public void setSolicitaArquivoJSON(SolicitaArquivoJson solicitaArquivoJSON) {
		this.solicitaArquivoJSON = solicitaArquivoJSON;
	}

	public SolutionAnswerArquivoJson getSolutionAnswerArquivoJson() {
		return solutionAnswerArquivoJson;
	}

	public void setSolutionAnswerArquivoJson(SolutionAnswerArquivoJson solutionAnswerArquivoJson) {
		this.solutionAnswerArquivoJson = solutionAnswerArquivoJson;
	}
}
