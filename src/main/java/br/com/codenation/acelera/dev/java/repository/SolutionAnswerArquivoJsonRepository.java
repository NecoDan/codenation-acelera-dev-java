package br.com.codenation.acelera.dev.java.repository;

import java.io.File;
import java.util.ArrayList;

import br.com.codenation.acelera.dev.java.exception.RepositoryExcpetion;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;

public interface SolutionAnswerArquivoJsonRepository {

	/**
	 * @author Daniel Santos
	 * @since 09/08/2019 recuperarSolutionAnswerArquivoJson
	 * @param token uma instância não nula e válida de {@link String}
	 * @return {@link SolutionAnswerArquivoJson} uma instância de
	 *         {@link SolutionAnswerArquivoJson}
	 */
	public SolutionAnswerArquivoJson recuperarSolutionAnswerArquivoJson(String token) throws RepositoryExcpetion;

	/**
	 * @author Daniel Santos
	 * @since 09/08/2019 recuperarRespostaArquivoJsonExistente
	 * @return {@link ArrayList} uma lista de instâncias de
	 *         {@link SolutionAnswerArquivoJson}
	 */
	public ArrayList<SolutionAnswerArquivoJson> recuperarTodos() throws RepositoryExcpetion;

	/**
	 * @author Daniel Santos
	 * @since 09/08/2019 recuperarArquivos
	 * @return {@link ArrayList} uma lista de instâncias de {@link File}
	 */
	public ArrayList<File> recuperarArquivos() throws RepositoryExcpetion;
}
