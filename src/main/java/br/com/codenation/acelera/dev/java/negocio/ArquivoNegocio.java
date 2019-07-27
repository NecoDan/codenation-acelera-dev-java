package br.com.codenation.acelera.dev.java.negocio;

import java.util.ArrayList;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;

public interface ArquivoNegocio {

	/**
	 * @author Daniel Santos
	 * @since 22/07/2019 salvarArquivoDisco
	 * @param token
	 * @return uma instância não nula de {@link ArquivoJson}
	 */
	public void salvarArquivoDisco(ArquivoJson respostaArquivoJson) throws NegocioException;

	/**
	 * @author Daniel Santos
	 * @since 22/07/2019 recuperarRespostaArquivoJsonExistente
	 * @param respostaArquivoJson uma instância não nula e válida de
	 *                            {@link ArquivoJson}
	 * @return void
	 */
	public ArquivoJson recuperarRespostaArquivoJsonExistente(String token) throws NegocioException;
	
	/**
	 * @author Daniel Santos
	 * @since 22/07/2019 recuperarRespostaArquivoJsonExistente
	 * @param respostaArquivoJson uma instância não nula e válida de
	 *                            {@link ArquivoJson}
	 * @return void
	 */
	public ArrayList<SolutionAnswerArquivoJson> recuperarArquivosJson() throws NegocioException;
}
