package br.com.codenation.acelera.dev.java.negocio;

import java.util.ArrayList;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.exception.RepositoryExcpetion;
import br.com.codenation.acelera.dev.java.exception.SolicitarArquivoException;
import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolicitaArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJsonBuilder;
import br.com.codenation.acelera.dev.java.repository.SolutionAnswerArquivoJsonRepository;
import br.com.codenation.acelera.dev.java.repository.SolutionAnswerArquivoJsonRepositoryImpl;

public class SolutionAnswerArquivoJsonNegocioImpl implements SolutionAnswerArquivoJsonNegocio {

	private SolicitaArquivoNegocio solicitaArquivoNegocio;

	private SolutionAnswerArquivoJsonRepository solutionAnswerArquivoJsonRepository;

	public SolutionAnswerArquivoJsonNegocioImpl() {
		this.solicitaArquivoNegocio = new SolicitaArquivoNegocioImpl();
		this.solutionAnswerArquivoJsonRepository = new SolutionAnswerArquivoJsonRepositoryImpl();
	}

	@Override
	public ArrayList<SolutionAnswerArquivoJson> recuperarTodos() throws NegocioException {
		ArrayList<SolutionAnswerArquivoJson> solutionsAnswerArquivoJsons = null;

		try {
			solutionsAnswerArquivoJsons = this.solutionAnswerArquivoJsonRepository.recuperarTodos();
		} catch (RepositoryExcpetion e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}

		return solutionsAnswerArquivoJsons;
	}

	@Override
	public SolutionAnswerArquivoJson recuperarSolutionAnswerArquivoJson(String token) throws NegocioException {
		SolutionAnswerArquivoJson solutionAnswerArquivoJson = null;

		try {
			solutionAnswerArquivoJson = this.solutionAnswerArquivoJsonRepository
					.recuperarSolutionAnswerArquivoJson(token);
		} catch (RepositoryExcpetion e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}

		return solutionAnswerArquivoJson;
	}

	@Override
	public SolutionAnswerArquivoJson processarSolicitacaoArquivo(String token) throws NegocioException {
		SolutionAnswerArquivoJson solutionAnswerArquivoJson = null;

		if (token == null || (token != null && token.isEmpty()))
			throw new NegocioException(new SolicitarArquivoException().getMessage());

		return solutionAnswerArquivoJson;
	}

	@Override
	public SolutionAnswerArquivoJson processarSolicitacaoArquivo(SolicitaArquivoJson solicitaArquivoJson)
			throws NegocioException {
		SolutionAnswerArquivoJson solutionAnswerArquivoJson = null;

		if (solicitaArquivoJson == null)
			throw new NegocioException(new SolicitarArquivoException().getMessage());

		if (!solicitaArquivoJson.isTokenValido())
			throw new NegocioException(new SolicitarArquivoException().getMessage());

		ArquivoJson arquivoJson = solicitaArquivoNegocio.salvarArquivo(solicitaArquivoJson);

		if (arquivoJson != null)
			solutionAnswerArquivoJson = SolutionAnswerArquivoJsonBuilder.umNovoSolutionAnswerArquivoJson(arquivoJson)
					.constroi();

		return solutionAnswerArquivoJson;
	}

}
