package br.com.codenation.acelera.dev.java.negocio;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolicitaArquivoJson;

public interface SolicitaArquivoNegocio {

	/**
	 * @author Daniel Santos
	 * @since 21/07/2019
	 * @param solicitaArquivoJSON
	 * @return void
	 */
	public String solicitarArquivo(String token) throws NegocioException;

	/**
	 * @author Daniel Santos
	 * @since 21/07/2019
	 * @param solicitaArquivoJSON
	 * @return void
	 */
	public SolicitaArquivoJson solicitarArquivo(SolicitaArquivoJson solicitaArquivoJson) throws NegocioException;

	/**
	 * @author Daniel Santos
	 * @since 21/07/2019
	 * @category salvarArquivo
	 * @param solicitaArquivoJson
	 * @return uma instância de {@link ArquivoJson}
	 */
	public ArquivoJson salvarArquivo(SolicitaArquivoJson solicitaArquivoJson) throws NegocioException;

	/**
	 * @author Daniel Santos
	 * @since 27/07/2019
	 * @category atualizarArquivo
	 * @param solicitaArquivoJson uma instância de {@linkf SolicitaArquivoJson}
	 * @return void
	 */
	public void atualizarArquivo(ArquivoJson ArquivoJson) throws NegocioException;

}
