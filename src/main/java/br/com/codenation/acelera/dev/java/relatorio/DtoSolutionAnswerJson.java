package br.com.codenation.acelera.dev.java.relatorio;

import java.util.ArrayList;
import java.util.List;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;
import br.com.codenation.acelera.dev.java.negocio.SolutionAnswerArquivoJsonNegocio;
import br.com.codenation.acelera.dev.java.negocio.SolutionAnswerArquivoJsonNegocioImpl;

public class DtoSolutionAnswerJson {

	private List<String> titulosLabelHeader = new ArrayList<String>();
	
	private List<SolutionAnswerArquivoJson> solutionsAnswerArquivoJson = new ArrayList<SolutionAnswerArquivoJson>();
	
	private String token;

	public DtoSolutionAnswerJson(String token) {
		try {
			this.token = token;
			this.inicializar();
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	private void inicializar() throws NegocioException {
		SolutionAnswerArquivoJsonNegocio solutionAnswerArquivoJsonNegocio = new SolutionAnswerArquivoJsonNegocioImpl();

		try {
			if(this.token != null && !this.token.isEmpty())
				System.out.println("Token: " + this.token);
		
			this.solutionsAnswerArquivoJson = solutionAnswerArquivoJsonNegocio.recuperarTodos();
		} catch (NegocioException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	public List<String> getTitulos() {
		return this.titulosLabelHeader;
	}

	public List<SolutionAnswerArquivoJson> getSolutionAnswerArquivoJson() {
		return this.solutionsAnswerArquivoJson;
	}
}
