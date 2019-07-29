package br.com.codenation.acelera.dev.java.negocio;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;
import br.com.codenation.acelera.dev.java.util.GeradorCriptografiaJulioCesar;
import br.com.codenation.acelera.dev.java.util.MultipartUtility;

public class CriptografiaNegocioImpl implements CriptografiaNegocio {

	private final static String URL_ENVIO = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=";

	private SolicitaArquivoNegocio solicitaArquivoNegocio = new SolicitaArquivoNegocioImpl();

	public CriptografiaNegocioImpl() {
		this.solicitaArquivoNegocio = new SolicitaArquivoNegocioImpl();
	}

	@Override
	public Boolean efetuarDecifracaoCriptografia(SolutionAnswerArquivoJson solutionAnswerArquivoJson)
			throws NegocioException {
		this.isParamsSolutionAnswerArquivoJsonValido(solutionAnswerArquivoJson);

		GeradorCriptografiaJulioCesar geradorCriptografiaJulioCesar = new GeradorCriptografiaJulioCesar(
				solutionAnswerArquivoJson.getArquivoJson().getNumeroCasas());

		String mensagemDecifrada = geradorCriptografiaJulioCesar
				.criptografa(solutionAnswerArquivoJson.getArquivoJson().getCifrado(), false);

		if (mensagemDecifrada == null || (mensagemDecifrada != null && mensagemDecifrada.isEmpty()))
			return Boolean.FALSE;

		solutionAnswerArquivoJson.gerarConteudoDecifrado(mensagemDecifrada);
		solutionAnswerArquivoJson.processarStatusSolucao();

		this.solicitaArquivoNegocio.atualizarArquivo(solutionAnswerArquivoJson.getArquivoJson());
		return Boolean.TRUE;
	}

	private void isParamsSolutionAnswerArquivoJsonValido(SolutionAnswerArquivoJson solutionAnswerArquivoJson)
			throws NegocioException {
		if (solutionAnswerArquivoJson == null)
			throw new NegocioException(
					"Não foi possível efetuar, nenhum arquivo de resposta JSON encontrado e/ou importado.");

		if (solutionAnswerArquivoJson.getArquivoJson() == null)
			throw new NegocioException("Não foi possível efetuar, nenhum arquivo de resposta JSON encontrado.");

		if (!solutionAnswerArquivoJson.isValidoEImportado())
			throw new NegocioException(
					"Não foi possível efetuar, nenhum arquivo de resposta JSON encontrado e/ou importado.");
	}

	@Override
	public Boolean efetuarHashResumoCriptografado(SolutionAnswerArquivoJson solutionAnswerArquivoJson)
			throws NegocioException {
		this.isParamsSolutionAnswerArquivoJsonValido(solutionAnswerArquivoJson);

		if (solutionAnswerArquivoJson.isFinalizado() || !solutionAnswerArquivoJson.isDecifrado())
			return Boolean.FALSE;

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.reset();
			messageDigest.update(solutionAnswerArquivoJson.getArquivoJson().getDecifrado().getBytes("utf8"));

			String sha1ResumoCriptografado = String.format("%040x", new BigInteger(1, messageDigest.digest()));

			if (sha1ResumoCriptografado != null && !sha1ResumoCriptografado.isEmpty()) {
				solutionAnswerArquivoJson.gerarConteudoResumoCriptografado(sha1ResumoCriptografado);
				solutionAnswerArquivoJson.processarStatusSolucao();

				this.solicitaArquivoNegocio.atualizarArquivo(solutionAnswerArquivoJson.getArquivoJson());
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Boolean.FALSE;
	}

	@Override
	public Boolean enviarSolucaoAnswerResposta(SolutionAnswerArquivoJson solutionAnswerArquivoJson)
			throws NegocioException {
		this.isParamsSolutionAnswerArquivoJsonValido(solutionAnswerArquivoJson);

		if (!solutionAnswerArquivoJson.isFinalizado())
			return Boolean.FALSE;

		if (solutionAnswerArquivoJson.getToken() == null
				|| (solutionAnswerArquivoJson.getToken() != null && solutionAnswerArquivoJson.getToken().isEmpty())) {

			if (solutionAnswerArquivoJson.getArquivoJson() == null
					|| (solutionAnswerArquivoJson.getArquivoJson() == null
							&& !solutionAnswerArquivoJson.getArquivoJson().isTokenValido()))
				throw new NegocioException("");
		}

		String urlPOST = URL_ENVIO + solutionAnswerArquivoJson.getToken();
		String token = solutionAnswerArquivoJson.getToken();
		
		ArquivoNegocio arquivoNegocio = new ArquivoNegocioImpl();		
        File uploadFile = arquivoNegocio.recuperarArquivoDefaultAnswer(token); 
        
		if (uploadFile == null)
			throw new NegocioException("Arquivo não encontrado e/ou inválido.");
        
        try {
        	MultipartUtility multipart = new MultipartUtility();  
        	return multipart.enviarNovo(urlPOST, uploadFile, null);                    	
        } catch (IOException e) {
    		throw new NegocioException("Erro ao enviar ao arquivo: " + e.getMessage(), e.getCause());
        } 
	}
}
