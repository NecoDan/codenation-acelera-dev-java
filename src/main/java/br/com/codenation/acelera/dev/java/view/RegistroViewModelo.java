package br.com.codenation.acelera.dev.java.view;

import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;

import br.com.codenation.acelera.dev.java.modelo.SolicitaArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;

public class RegistroViewModelo {

	private SolicitaArquivoJson solicitaArquivoJSON;
	private SolutionAnswerArquivoJson solutionAnswerArquivoJson;
	private String typeFromOuter;

	public RegistroViewModelo() {
		this.solicitaArquivoJSON = new SolicitaArquivoJson();
		this.solutionAnswerArquivoJson = new SolutionAnswerArquivoJson();
	}
	
    @Init
    public void init(@ExecutionArgParam("type") String type) {
        this.typeFromOuter = type;	
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
	
	public String getTypeFromOuter() {
		return this.typeFromOuter;
	}
	
}
