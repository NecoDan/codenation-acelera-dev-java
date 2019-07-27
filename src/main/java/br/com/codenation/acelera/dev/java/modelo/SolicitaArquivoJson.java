package br.com.codenation.acelera.dev.java.modelo;

public class SolicitaArquivoJson {

	private String token;
	private String conteudoJSON;

	public SolicitaArquivoJson() {

	}

	public SolicitaArquivoJson(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getConteudoJSON() {
		return conteudoJSON;
	}

	public void setConteudoJSON(String conteudoJSON) {
		this.conteudoJSON = conteudoJSON;
	}

	/* ################## Métodos De Ação ################### */

	public boolean isTokenValido() {
		return (this.token != null || this.token != null && !token.isEmpty());
	}

	public boolean isConteudoValido() {
		return (this.conteudoJSON != null || this.conteudoJSON != null && !conteudoJSON.isEmpty());
	}

	@Override
	public String toString() {
		return "SolicitaArquivoJSON [token=" + token + ", conteudoJSON=" + conteudoJSON + "]";
	}
		
}
