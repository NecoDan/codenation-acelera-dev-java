package br.com.codenation.acelera.dev.java.negocio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.exception.SolicitarArquivoException;
import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolicitaArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJsonBuilder;

public class SolutionAnswerArquivoJsonNegocioImpl implements SolutionAnswerArquivoJsonNegocio {

	private ArquivoNegocio arquivoNegocio;

	private SolicitaArquivoNegocio solicitaArquivoNegocio;

	public SolutionAnswerArquivoJsonNegocioImpl() {
		this.arquivoNegocio = new ArquivoNegocioImpl();
		this.solicitaArquivoNegocio = new SolicitaArquivoNegocioImpl();
	}

	@Override
	public ArrayList<SolutionAnswerArquivoJson> recuperarTodos() throws NegocioException {
		ArrayList<SolutionAnswerArquivoJson> solutionsAnswerArquivoJsons = null;

		try {
			File arquivoDiretorio = arquivoNegocio.isDiretorioPadraoValido();

			if (arquivoDiretorio.listFiles().length <= 0)
				return null;

			solutionsAnswerArquivoJsons = new ArrayList<SolutionAnswerArquivoJson>();

			for (File file : arquivoDiretorio.listFiles()) {
				String strFileNameToken = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("/") + 1);

				if (strFileNameToken != null && !strFileNameToken.isEmpty()) {
					solutionsAnswerArquivoJsons.add(SolutionAnswerArquivoJsonBuilder
							.umNovoSolutionAnswerArquivoJsonAPartir(strFileNameToken)
							.contendoOArquivo(arquivoNegocio.recuperarRespostaArquivoJsonExistente(strFileNameToken))
							.constroi());
				}
			}
		} catch (NegocioException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}

		return solutionsAnswerArquivoJsons;
	}

	@Override
	public List<SolutionAnswerArquivoJson> recuperarListaSolutionAnswerArquivoJson(String token)
			throws NegocioException {
		return this.recuperarTodos();
	}

	@Override
	public SolutionAnswerArquivoJson recuperarSolutionAnswerArquivoJson(String token) throws NegocioException {
		return null;
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
