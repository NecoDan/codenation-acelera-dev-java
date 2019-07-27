package br.com.codenation.acelera.dev.java.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MethodPost extends MethodHttps {

	/*
	 * ############################### Constantes
	 */

	private final static String METODO = "POST";
	private final static String AGENT = "User-Agent";
	private final static String USER_LANGUAGE = "Accept-Language";
	private final static String LANGUAGE = "en-US,en;q=0.5";
	private final static String USER_AGENT = "Mozilla/5.0";

	/*
	 * ############################## Constructors
	 */

	public MethodPost(String url, String parametros) {
		super(url, parametros);
	}

	/*
	 * ########################### Métodos de Ação
	 */

	@Override
	public void conectar() {
		try {
			if (this.getUrl() != null && !this.getUrl().isEmpty()) {
				URL objUrl;
				objUrl = new URL(this.getUrl());
				this.setConexao((HttpURLConnection) objUrl.openConnection());

				this.getConexao().setRequestMethod(METODO);
				this.getConexao().setRequestProperty(AGENT, USER_AGENT);
				this.getConexao().setRequestProperty(USER_LANGUAGE, LANGUAGE);

				this.gerarRequest(true);
				this.setCodigoResponse(this.getConexao().getResponseCode());

				this.imprimeRequisicao();
				this.carregaDadosResposta();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void gerarRequest(Boolean setarSaidaDados) {
		this.getConexao().setDoOutput(setarSaidaDados);
		DataOutputStream dados = null;

		try {
			dados = new DataOutputStream(this.getConexao().getOutputStream());
			dados.writeBytes(this.getParametros());
			dados.flush();
			dados.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void carregaDadosResposta() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(this.getConexao().getInputStream()));

			String inputLine;

			while ((inputLine = entrada.readLine()) != null) {
				this.getResponse().append(inputLine);
			}

			entrada.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void imprimeRequisicao() {
		if (this.getUrl() != null && !this.getUrl().isEmpty() && this.getCodigoResponse() != null) {
			System.out.println("\nSending 'POST' request to URL : " + this.getUrl());
			System.out.println("Post parameters : " + this.getParametros());
			System.out.println("Response Code : " + this.getCodigoResponse());
		}
	}

	@Override
	public String resposta() {
		if (this.getResponse() != null)
			return this.getResponse().toString();

		return " ";
	}
	
}
