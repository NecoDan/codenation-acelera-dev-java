package br.com.codenation.acelera.dev.java.relatorio;

import java.util.ArrayList;
import java.util.List;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;
import br.com.codenation.acelera.dev.java.negocio.ArquivoNegocio;
import br.com.codenation.acelera.dev.java.negocio.ArquivoNegocioImpl;

public class DtoSolutionAnswerJson {

	private List<String> titulosLabelHeader = new ArrayList<String>();
	private List<SolutionAnswerArquivoJson> solutionsAnswerArquivoJson = new ArrayList<SolutionAnswerArquivoJson>();

	public DtoSolutionAnswerJson() {
		try {
			this.inicializar();
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	private void carregarHeaderTitulo() {
		titulosLabelHeader.add("");
		titulosLabelHeader.add("");
		titulosLabelHeader.add("");
		titulosLabelHeader.add("");
		titulosLabelHeader.add("");
	}

	private void inicializar() throws NegocioException {
		this.carregarHeaderTitulo();
		ArquivoNegocio arquivoNegocio = new ArquivoNegocioImpl();
		
		try {
			this.solutionsAnswerArquivoJson = arquivoNegocio.recuperarArquivosJson();
		} catch (NegocioException e) {
			throw new NegocioException(e.getMessage());
		}

		//		for (int i = 0; i < 10; i++) {
		//			SolutionAnswerArquivoJson solutionAnswerArquivoJson = new SolutionAnswerArquivoJson(arquivoJson);
		//			solutionAnswerArquivoJson.setToken(arquivoJson.getToken());
		//
		//			solutionsAnswerArquivoJson.add(solutionAnswerArquivoJson);
		//		}
	}

	public List<String> getTitulos() {
		return this.titulosLabelHeader;
	}

	public List<SolutionAnswerArquivoJson> getSolutionAnswerArquivoJson() {
		return this.solutionsAnswerArquivoJson;
	}
}
