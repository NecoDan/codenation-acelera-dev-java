package br.com.codenation.acelera.dev.java.modelo;

public enum StatusSolutionJson {

	// ######################################### Atributos

	NAO_INFORMADO, IMPORTADO, CIFRADO, DECIFRADO, FINALIZADO, ENVIADO;

	private static final String DESC_NAO_INFORMADO = "NÃO INFORMADO";

	private static final String DESC_IMPORTADO = "IMPORTADO";

	private static final String DESC_CIFRADO = "CIFRADO";

	private static final String DESC_DECIFRADO = "DECIFRADO";

	private static final String DESC_FINALIZADO = "FINALIZADO";

	private static final String DESC_ENVIADO = "ENVIADO";

	// ######################################### Métodos De Ação

	public boolean isNaoInformado() {
		switch (this) {
		case NAO_INFORMADO:
			return true;
		default:
			return false;
		}
	}

	public boolean isImportado() {
		switch (this) {
		case IMPORTADO:
			return true;
		default:
			return false;
		}
	}

	public boolean isCifrado() {
		switch (this) {
		case CIFRADO:
			return true;
		default:
			return false;
		}
	}

	public boolean isDecifrado() {
		switch (this) {
		case DECIFRADO:
			return true;
		default:
			return false;
		}
	}

	public boolean isFinalizado() {
		switch (this) {
		case FINALIZADO:
			return true;
		default:
			return false;
		}
	}

	public boolean isEnviado() {
		switch (this) {
		case ENVIADO:
			return true;
		default:
			return false;
		}
	}

	public static StatusSolutionJson valueOfCodigo(Integer codigo) {
		if (codigo == null)
			return null;

		for (StatusSolutionJson statusSolutionJson : StatusSolutionJson.values()) {
			if (statusSolutionJson.ordinal() == codigo)
				return statusSolutionJson;
		}
		return null;
	}

	public static StatusSolutionJson valueOfCodigo(Long codigo) {
		if (codigo == null)
			return null;

		for (StatusSolutionJson statusSolutionJson : StatusSolutionJson.values()) {
			if (statusSolutionJson.ordinal() == codigo)
				return statusSolutionJson;
		}
		return null;
	}

	public static StatusSolutionJson valueOfCodigo(int codigo) {
		for (StatusSolutionJson statusSolutionJson : StatusSolutionJson.values()) {
			if (statusSolutionJson.ordinal() == codigo)
				return statusSolutionJson;
		}
		return null;
	}

	public String toString() {
		String strStatus = "";
		strStatus = StatusSolutionJson.toString(this);
		return strStatus;
	}

	public static String toString(StatusSolutionJson statusSolutionJson) {
		switch (statusSolutionJson) {
		case NAO_INFORMADO:
			return DESC_NAO_INFORMADO;
		case IMPORTADO:
			return DESC_IMPORTADO;
		case CIFRADO:
			return DESC_CIFRADO;
		case DECIFRADO:
			return DESC_DECIFRADO;
		case FINALIZADO:
			return DESC_FINALIZADO;
		case ENVIADO:
			return DESC_ENVIADO;
		default:
			return "";
		}
	}
}
