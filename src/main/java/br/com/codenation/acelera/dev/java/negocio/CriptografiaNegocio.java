package br.com.codenation.acelera.dev.java.negocio;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;

public interface CriptografiaNegocio {

	/**
	 * @author Daniel Santos
	 * @since 28/07/2019
	 * @category efetuarDecifracaoCriptografia
	 * @param solutionAnswerArquivoJson uma instância válida e não nula de
	 *                                  {@link SolutionAnswerArquivoJson}
	 * @return void
	 */
	public Boolean efetuarDecifracaoCriptografia(SolutionAnswerArquivoJson solutionAnswerArquivoJson)
			throws NegocioException;

	/**
	 * @author Daniel Santos
	 * @since 28/07/2019
	 * @category efetuarHashResumoCriptografado
	 * @param solutionAnswerArquivoJson uma instância válida e não nula de
	 *                                  {@link SolutionAnswerArquivoJson}
	 * @return void
	 */
	public Boolean efetuarHashResumoCriptografado(SolutionAnswerArquivoJson solutionAnswerArquivoJson)
			throws NegocioException;

	/**
	 * @author Daniel Santos
	 * @since 28/07/2019
	 * @category enviarSolucaoAnswerResposta
	 * @param solutionAnswerArquivoJson uma instância válida e não nula de
	 *                                  {@link SolutionAnswerArquivoJson}
	 * @return void
	 */
	public Boolean enviarSolucaoAnswerResposta(SolutionAnswerArquivoJson solutionAnswerArquivoJson)
			throws NegocioException;

}
