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
	public SolicitaArquivoJson solicitarArquivo(SolicitaArquivoJson solicitaArquivoJSON) throws NegocioException;

	/**
	 * @author Daniel Santos
	 * @since 21/07/2019 salvarArquivo
	 * @param solicitaArquivoJSON
	 * @return void
	 */
	public ArquivoJson salvarArquivo(SolicitaArquivoJson solicitaArquivoJSON) throws NegocioException;

}
