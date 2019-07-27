package br.com.codenation.acelera.dev.java.modelo;

public class ArquivoJson {

	private Integer numero_casas;

	private String token;

	private String cifrado;

	private String decifrado;

	private String resumo_criptografico;

	private Boolean importado = Boolean.FALSE;

	private Boolean importadoSalvo = Boolean.FALSE;

	public ArquivoJson(Integer numeroCasas, String token, String cifrado, String decifrado,
			String resumoCriptografico) {
		this.numero_casas = numeroCasas;
		this.token = token;
		this.cifrado = cifrado;
		this.decifrado = decifrado;
		this.resumo_criptografico = resumoCriptografico;
	}

	public ArquivoJson() {
	}

	public Integer getNumeroCasas() {
		return numero_casas;
	}

	public void setNumeroCasas(Integer numeroCasas) {
		this.numero_casas = numeroCasas;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCifrado() {
		return cifrado;
	}

	public void setCifrado(String cifrado) {
		this.cifrado = cifrado;
	}

	public String getDecifrado() {
		return decifrado;
	}

	public void setDecifrado(String decifrado) {
		this.decifrado = decifrado;
	}

	public String getResumoCriptografico() {
		return resumo_criptografico;
	}

	public void setResumoCriptografico(String resumoCriptografico) {
		this.resumo_criptografico = resumoCriptografico;
	}

	/* ################## Outros Getters & Setters ################### */

	public Boolean isImportado() {
		return importado;
	}

	public Boolean isImportadoSalvo() {
		return importadoSalvo;
	}

	/* ############################ Métodos De Ação ################### */
	
	public void importado() {
		this.importado = Boolean.TRUE;
	}

	public void importadoSalvo() {
		this.importadoSalvo = Boolean.TRUE;
	}

	public boolean isTokenValido() {
		return (this.token != null || this.token != null && !token.isEmpty());
	}

	public boolean isNumeroCasasValido() {
		return (this.numero_casas > 0);
	}

	public boolean isSolucionado() {
		return (this.decifrado != null && !this.decifrado.isEmpty() && this.resumo_criptografico != null
				&& !this.resumo_criptografico.isEmpty());
	}

	@Override
	public String toString() {
		return "ArquivoJson [numeroCasas=" + numero_casas + ", token=" + token + ", cifrado=" + cifrado
				+ ", decifrado=" + decifrado + ", resumoCriptografico=" + resumo_criptografico + "]";
	}

}
