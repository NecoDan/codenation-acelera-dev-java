package br.com.codenation.acelera.dev.java.negocio;

import java.util.ArrayList;
import java.util.List;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolicitaArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;

public interface SolutionAnswerArquivoJsonNegocio {

	/**
	 * @author Daniel Santos
	 * @since 23/07/2019 recuperarSolutionAnswerArquivoJson
	 * @param token uma instância não nula e válida de {@link String}
	 * @return {@link SolutionAnswerArquivoJson} uma instância de
	 *         {@link SolutionAnswerArquivoJson}
	 */
	public SolutionAnswerArquivoJson recuperarSolutionAnswerArquivoJson(String token) throws NegocioException;

	/**
	 * @author Daniel Santos
	 * @since 27/07/2019 processarSolicitacaoArquivo
	 * @param token uma instância não nula e válida de {@link String}
	 * @return {@link SolutionAnswerArquivoJson} uma instância de
	 *         {@link SolutionAnswerArquivoJson}
	 */
	public SolutionAnswerArquivoJson processarSolicitacaoArquivo(String token) throws NegocioException;
	
	/**
	 * @author Daniel Santos
	 * @since 27/07/2019 processarSolicitacaoArquivo
	 * @param solicitaArquivoJson uma instância não nula e válida de {@link SolicitaArquivoJson}
	 * @return {@link SolutionAnswerArquivoJson} uma instância de
	 *         {@link SolutionAnswerArquivoJson}
	 */
	public SolutionAnswerArquivoJson processarSolicitacaoArquivo(SolicitaArquivoJson solicitaArquivoJson) throws NegocioException;

	/**
	 * @author Daniel Santos
	 * @since 23/07/2019 recuperarRespostaArquivoJsonExistente
	 * @return {@link ArrayList} uma lista de instâncias de
	 *         {@link SolutionAnswerArquivoJson}
	 */
	public ArrayList<SolutionAnswerArquivoJson> recuperarTodos() throws NegocioException;

	/**
	 * @author Daniel Santos
	 * @since 23/07/2019 recuperarListaSolutionAnswerArquivoJson
	 * @param token uma instância não nula e válida de {@link ArquivoJson}
	 * @return {@link List} uma lista de instâncias de
	 *         {@link SolutionAnswerArquivoJson}
	 */
	public List<SolutionAnswerArquivoJson> recuperarListaSolutionAnswerArquivoJson(String token)
			throws NegocioException;

}
