package br.com.codenation.acelera.dev.java.modelo;

public class ArquivoJson {

	private Integer numero_casas;

	private String token;

	private String cifrado;

	private String decifrado;

	private String resumo_criptografico;

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

	public boolean isTokenValido() {
		return (this.token != null && !token.isEmpty());
	}

	public boolean isNumeroCasasValido() {
		return (this.numero_casas != null && this.numero_casas > 0);
	}
	
	public boolean isCifradoValido() {
		return (this.cifrado != null && !this.cifrado.isEmpty());
	}
	
	public boolean isDecifradoValido() {
		return (this.decifrado != null && !this.decifrado.isEmpty());
	}
	
	public boolean isResumoCriptograficoValido() {
		return (this.resumo_criptografico != null && !this.resumo_criptografico.isEmpty());
	}

	public boolean isSolucionado() {
		return (isDecifradoValido() && isResumoCriptograficoValido());
	}

	@Override
	public String toString() {
		return "[numeroCasas=" + numero_casas + ", token=" + token + ", cifrado=" + cifrado
				+ ", decifrado=" + decifrado + ", resumoCriptografico=" + resumo_criptografico + "]";
	}

}
