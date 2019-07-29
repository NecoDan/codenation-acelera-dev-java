package br.com.codenation.acelera.dev.java.negocio;

import com.google.gson.Gson;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.exception.SolicitarArquivoException;
import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolicitaArquivoJson;
import br.com.codenation.acelera.dev.java.web.MethodGet;

public class SolicitaArquivoNegocioImpl implements SolicitaArquivoNegocio {

	private static final String URL_SOLICITACAO_ARQUIVO_CODENATION = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=";

	private ArquivoNegocio arquivoNegocio;

	public SolicitaArquivoNegocioImpl() {
		arquivoNegocio = new ArquivoNegocioImpl();
	}

	@Override
	public String solicitarArquivo(String token) throws NegocioException {
		String conteudoJSON;

		if (token == null || (token != null && token.isEmpty()))
			throw new NegocioException(new SolicitarArquivoException().getMessage());

		String urlGET = URL_SOLICITACAO_ARQUIVO_CODENATION + token;

		MethodGet metodoHttpGET = new MethodGet(urlGET);
		metodoHttpGET.conectar();

		conteudoJSON = metodoHttpGET.resposta();
		return conteudoJSON;
	}

	@Override
	public SolicitaArquivoJson solicitarArquivo(SolicitaArquivoJson solicitaArquivoJson) throws NegocioException {
		if (solicitaArquivoJson == null)
			throw new NegocioException(new SolicitarArquivoException().getMessage());

		if (!solicitaArquivoJson.isTokenValido())
			throw new NegocioException(new SolicitarArquivoException().getMessage());

		solicitaArquivoJson.setConteudoJSON(this.solicitarArquivo(solicitaArquivoJson.getToken()));
		return solicitaArquivoJson;
	}

	@Override
	public ArquivoJson salvarArquivo(SolicitaArquivoJson solicitaArquivoJson) throws NegocioException {
		ArquivoJson arquivoJson = null;

		if (solicitaArquivoJson == null)
			throw new NegocioException(new SolicitarArquivoException().getMessage());

		if (!solicitaArquivoJson.isTokenValido())
			throw new NegocioException(new SolicitarArquivoException().getMessage());

		try {
			arquivoJson = this.arquivoNegocio.recuperarRespostaArquivoJsonExistente(solicitaArquivoJson.getToken());
		} catch (NegocioException e) {
			throw new NegocioException(e.getMessage());
		}

		if (arquivoJson != null)
			return arquivoJson;

		if (arquivoJson == null) {
			solicitaArquivoJson = this.solicitarArquivo(solicitaArquivoJson);

			if (!solicitaArquivoJson.isConteudoValido())
				throw new NegocioException(new SolicitarArquivoException().getMessage());

			Gson gson = new Gson();
			arquivoJson = gson.fromJson(solicitaArquivoJson.getConteudoJSON(), ArquivoJson.class);

			this.arquivoNegocio.salvarArquivoDisco(arquivoJson);
		}

		return arquivoJson;
	}

	@Override
	public void atualizarArquivo(ArquivoJson arquivoJson) throws NegocioException {
		this.arquivoNegocio.atualizarArquivoDisco(arquivoJson);
	}
	
	//	List<Telefone> fones = this.telefones.stream().filter(f -> f.getNumero().equals(telefone.getNumero())).collect(Collectors.toList());
	//
	//	telefones.stream().forEach(i -> { i.setContato(this);
	//		this.telefones.add(i);
	//	});
}
