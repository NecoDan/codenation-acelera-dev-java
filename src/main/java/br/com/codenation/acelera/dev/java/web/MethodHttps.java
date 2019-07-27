package br.com.codenation.acelera.dev.java.web;

import java.net.HttpURLConnection;

public abstract class MethodHttps {

	/*
	 * ############################### Atributos
	 */

	private String url;
	private String parametros;
	private HttpURLConnection conexao;
	private StringBuffer response;
	private Integer codigoResponse;

	/*
	 * ############################## Constructors
	 */

	public MethodHttps() {
		this.response = new StringBuffer();
	}

	public MethodHttps(String url) {
		this.url = url;
		this.response = new StringBuffer();
	}

	public MethodHttps(String url, String parametros) {
		this.url = url;
		this.parametros = parametros;
		this.response = new StringBuffer();
	}

	/*
	 * ########################### Getters & Setters
	 */

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public StringBuffer getResponse() {
		return response;
	}

	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	public HttpURLConnection getConexao() {
		return conexao;
	}

	public void setConexao(HttpURLConnection conexao) {
		this.conexao = conexao;
	}

	public Integer getCodigoResponse() {
		return codigoResponse;
	}

	public void setCodigoResponse(Integer codigoResponse) {
		this.codigoResponse = codigoResponse;
	}

	/*
	 * ########################### Métodos de Ação
	 */

	public abstract void conectar();

	public abstract String resposta();

	public abstract void imprimeRequisicao();

	public abstract void carregaDadosResposta();
	
	public abstract void gerarRequest(Boolean setarSaidaDados);

}
