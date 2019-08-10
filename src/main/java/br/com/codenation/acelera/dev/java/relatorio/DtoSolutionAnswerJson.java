package br.com.codenation.acelera.dev.java.relatorio;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Sessions;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;
import br.com.codenation.acelera.dev.java.negocio.SolutionAnswerArquivoJsonNegocio;
import br.com.codenation.acelera.dev.java.negocio.SolutionAnswerArquivoJsonNegocioImpl;

public class DtoSolutionAnswerJson {

	private List<String> titulosLabelHeader;	
	private List<SolutionAnswerArquivoJson> solutionsAnswerArquivoJson; 	
	private String token;

	public DtoSolutionAnswerJson() {
		try {
			this.solutionsAnswerArquivoJson = new ArrayList<SolutionAnswerArquivoJson>();
			this.titulosLabelHeader = new ArrayList<String>();
			this.token = (String) Sessions.getCurrent().getAttribute("token");
			
			this.inicializar();
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	private void inicializar() throws NegocioException {
		try {			
			SolutionAnswerArquivoJsonNegocio solutionAnswerArquivoJsonNegocio = new SolutionAnswerArquivoJsonNegocioImpl();			
			
			if(this.token != null && !this.token.isEmpty()) {
				this.solutionsAnswerArquivoJson
						.add(solutionAnswerArquivoJsonNegocio.recuperarSolutionAnswerArquivoJson(this.token));
				return;
			}
		
			this.solutionsAnswerArquivoJson = solutionAnswerArquivoJsonNegocio.recuperarTodos();
		} catch (NegocioException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	////////////////// Getters & Setters
	
	public List<String> getTitulos() {
		return this.titulosLabelHeader;
	}

	public List<SolutionAnswerArquivoJson> getSolutionAnswerArquivoJson() {
		return this.solutionsAnswerArquivoJson;
	}
}
