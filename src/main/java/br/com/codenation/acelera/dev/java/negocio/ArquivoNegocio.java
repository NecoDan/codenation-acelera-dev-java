package br.com.codenation.acelera.dev.java.negocio;

import java.io.File;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;

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
	 * @since 27/07/2019 
	 * @category atualizarArquivoDisco
	 * @param arquivoJson uma instância de {@link ArquivoJson}
	 */
	public void atualizarArquivoDisco(ArquivoJson arquivoJson) throws NegocioException;

	/**
	 * @author Daniel Santos
	 * @since 22/07/2019 recuperarRespostaArquivoJsonExistente
	 * @param respostaArquivoJson uma instância não nula e válida de
	 *                            {@link String}
	 * @return uma instância de {@link ArquivoJson}
	 */
	public ArquivoJson recuperarRespostaArquivoJsonExistente(String token) throws NegocioException;
	
	/**
	 * @author Daniel Santos
	 * @since 27/07/2019 isDiretorioPadraoValido
	 * @return uma instância de {@link File}
	 */
	public File isDiretorioPadraoValido() throws NegocioException;
	
	/**
	 * @author Daniel Santos
	 * @since 28/07/2019 recuperarArquivoDefaultAnswer
	 * @return uma instância de {@link File}
	 */
	public File recuperarArquivoDefaultAnswer(String token) throws NegocioException;
	
}
