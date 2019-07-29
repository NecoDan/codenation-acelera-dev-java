package br.com.codenation.acelera.dev.java.negocio;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;

public class ArquivoNegocioImpl implements ArquivoNegocio {

	private static final String DIRETORIO_PADRAO_ARQUIVOS = System.getProperty("user.home") + "/file-json-desafio/";
	
	private static final String NOME_PADRAO_ARQUIVO_JSON = "answer.json";

	public ArquivoNegocioImpl() {
	}

	@Override
	public void salvarArquivoDisco(ArquivoJson arquivoJson) throws NegocioException {
		this.validar(arquivoJson);
		this.salvarJsonArquivo(arquivoJson);
	}
	
	private void validar(ArquivoJson arquivoJson) throws NegocioException {
		if (arquivoJson == null)
			throw new NegocioException("Arquivo de resposta inválido e/ou inexistente.");

		if (!arquivoJson.isTokenValido())
			throw new NegocioException("Token do arquivo de resposta inválido e/ou inexistente.");

		if (!arquivoJson.isNumeroCasasValido())
			throw new NegocioException(
					"Não é possível decifrar o texto criptografado sem a quantidade de casas: inválida e/ou inexistente.");
	}
	
	private void salvarJsonArquivo(ArquivoJson arquivoJson) throws NegocioException{
		Gson gSonObject = new Gson();
		String conteudoArquivo = gSonObject.toJson(arquivoJson);

		File arquivoDiretorioPadrao = this.criarDiretorioPadraoArquivoToken();
		File arquivoDiretorioComToken = new File(
				arquivoDiretorioPadrao.getAbsolutePath() + "/" + arquivoJson.getToken());
		arquivoDiretorioComToken.mkdir();

		try {
			FileWriter arquivo = new FileWriter(
					arquivoDiretorioComToken.getAbsolutePath() + "/" + NOME_PADRAO_ARQUIVO_JSON);
			arquivo.write(conteudoArquivo);
			arquivo.flush();
			arquivo.close();
		} catch (IOException e) {
			throw new NegocioException("Não foi possível criar o arquivo JSON de importação.");
		}
	}
	
	@Override
	public void atualizarArquivoDisco(ArquivoJson arquivoJson) throws NegocioException {
		this.validar(arquivoJson);

		File fileArquivoDiretorio = this.isDiretorioPadraoValido();

		if (fileArquivoDiretorio.listFiles().length <= 0)
			throw new NegocioException("Diretório padrão inválido e/ou inexistente contendo os arquivos JSON's.");

		File fileArquivoDiretorioJSON = this.procurar(fileArquivoDiretorio, Boolean.TRUE, arquivoJson.getToken());

		if (fileArquivoDiretorioJSON == null)
			throw new NegocioException("Diretório padrão inválido e/ou inexistente contendo os arquivos JSON's.");

		File fileArquivoJSON = this.procurar(fileArquivoDiretorioJSON, Boolean.FALSE, NOME_PADRAO_ARQUIVO_JSON);

		if (fileArquivoDiretorioJSON.exists() && fileArquivoJSON != null) {
			fileArquivoJSON.delete();
			fileArquivoDiretorioJSON.delete();
		} else if (fileArquivoDiretorioJSON.exists() && fileArquivoJSON == null) {
			fileArquivoDiretorioJSON.delete();
		} else {
			throw new NegocioException("Não foi possível encontrar o arquivo JSON.");
		}

		this.salvarJsonArquivo(arquivoJson);
	}
	
	@Override
	public File isDiretorioPadraoValido() throws NegocioException {
		File arquivoDiretorio = new File(DIRETORIO_PADRAO_ARQUIVOS);

		if (!arquivoDiretorio.exists())
			arquivoDiretorio = this.criarDiretorioPadraoArquivoToken();

		boolean isDiretorioValido = (arquivoDiretorio.exists() && arquivoDiretorio.isDirectory()
				&& arquivoDiretorio.canRead());

		if (!isDiretorioValido)
			throw new NegocioException("Diretório padrão inválido e/ou inexistente contendo os arquivos JSON's.");

		return arquivoDiretorio;
	}
	
	@Override
	public File recuperarArquivoDefaultAnswer(String token) throws NegocioException {
		File arquivoJSON = null;		
		File arquivoDiretorio = this.isDiretorioPadraoValido();

		if (arquivoDiretorio.listFiles().length <= 0)
			return null;

		File arquivoDiretorioJSON = this.procurar(arquivoDiretorio, Boolean.TRUE, token);

		if (arquivoDiretorioJSON == null)
			throw new NegocioException("Diretório padrão inválido e/ou inexistente contendo os arquivos JSON's.");

		arquivoJSON = this.procurar(arquivoDiretorioJSON, Boolean.FALSE, NOME_PADRAO_ARQUIVO_JSON);
		return arquivoJSON;
	}

	@Override
	public ArquivoJson recuperarRespostaArquivoJsonExistente(String token) throws NegocioException {
		File arquivoDiretorio = this.isDiretorioPadraoValido();

		if (arquivoDiretorio.listFiles().length <= 0)
			return null;

		File arquivoDiretorioJSON = this.procurar(arquivoDiretorio, Boolean.TRUE, token);

		if (arquivoDiretorioJSON == null)
			throw new NegocioException("Diretório padrão inválido e/ou inexistente contendo os arquivos JSON's.");

		File arquivoJSON = this.procurar(arquivoDiretorioJSON, Boolean.FALSE, NOME_PADRAO_ARQUIVO_JSON);

		if (arquivoJSON == null)
			throw new NegocioException("Não foi possível encontrar o arquivo JSON.");

		List<ArquivoJson> arquivosJson = new ArrayList<ArquivoJson>();
		JSONObject jSonObject;
		JSONParser jSonParser = new JSONParser();

		try {
			jSonObject = (JSONObject) jSonParser.parse(new FileReader(arquivoJSON.getAbsoluteFile()));

			ArquivoJson arquivoFileJson = new ArquivoJson();

			Long strNumeroCasas = (Long) jSonObject.get("numero_casas");
			arquivoFileJson.setNumeroCasas(Integer.valueOf(strNumeroCasas.intValue()));
			arquivoFileJson.setToken((String) jSonObject.get("token"));
			arquivoFileJson.setCifrado((String) jSonObject.get("cifrado"));
			arquivoFileJson.setDecifrado((String) jSonObject.get("decifrado"));
			arquivoFileJson.setResumoCriptografico((String) jSonObject.get("resumo_criptografico"));

			arquivosJson.add(arquivoFileJson);
		} catch (IOException | ParseException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}

		Optional<ArquivoJson> optionalArquivoJson = null;
		Optional<ArquivoJson> res = arquivosJson.stream().parallel().filter(i -> i.getToken().equals(token)).findAny();

		if (res != null && res.isPresent())
			optionalArquivoJson = res;

		return optionalArquivoJson.get();
	}

	private File criarDiretorioPadraoArquivoToken() {
		File arquivoDiretorioPadrao = new File(DIRETORIO_PADRAO_ARQUIVOS);
		arquivoDiretorioPadrao.mkdir();
		return arquivoDiretorioPadrao;
	}

	private File procurar(File arquivo, Boolean buscaSomenteDiretorio, String nomeArquivoBuscar) {
		File arquivoBusca = null;
		boolean validoParaBusca = (arquivo.isDirectory() && arquivo.canRead());

		if (validoParaBusca && buscaSomenteDiretorio) {
			for (File file : arquivo.listFiles()) {
				if (file.isDirectory() && file.getAbsolutePath().contains(nomeArquivoBuscar))
					arquivoBusca = file;
			}

			return arquivoBusca;
		}

		if (validoParaBusca) {
			for (File file : arquivo.listFiles()) {
				if (file.exists() && file.getAbsolutePath().contains(nomeArquivoBuscar)
						&& nomeArquivoBuscar.equals(file.getName().toLowerCase()))
					arquivoBusca = file;
			}
		}

		return arquivoBusca;
	}
}
