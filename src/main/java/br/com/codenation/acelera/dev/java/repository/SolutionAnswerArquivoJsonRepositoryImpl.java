package br.com.codenation.acelera.dev.java.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.exception.RepositoryExcpetion;
import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJsonBuilder;
import br.com.codenation.acelera.dev.java.negocio.ArquivoNegocio;
import br.com.codenation.acelera.dev.java.negocio.ArquivoNegocioImpl;

public class SolutionAnswerArquivoJsonRepositoryImpl implements SolutionAnswerArquivoJsonRepository{
	
	private ArquivoNegocio arquivoNegocio;
	
	public SolutionAnswerArquivoJsonRepositoryImpl() {
		this.arquivoNegocio = new ArquivoNegocioImpl();
	}
	
	@Override
	public SolutionAnswerArquivoJson recuperarSolutionAnswerArquivoJson(String token) throws RepositoryExcpetion {
		SolutionAnswerArquivoJson solutionAnswerArquivoJson = null;
		
		try {
			ArrayList<File> arquivos = this.recuperarArquivos();

			for (File arquivo : arquivos) 
				solutionAnswerArquivoJson = this.extrairSolutionAnswerArquivoJsonAPartir(arquivo);
			
		} catch (RepositoryExcpetion e) {
			throw new RepositoryExcpetion(e.getMessage(), e.getCause());
		}

		return solutionAnswerArquivoJson;
	}

	@Override
	public ArrayList<SolutionAnswerArquivoJson> recuperarTodos() throws RepositoryExcpetion {
		ArrayList<SolutionAnswerArquivoJson> solutionsAnswerArquivoJsons = null;

		try {
			ArrayList<File> arquivos = this.recuperarArquivos();
			solutionsAnswerArquivoJsons = new ArrayList<SolutionAnswerArquivoJson>();

			for (File arquivo : arquivos)
				solutionsAnswerArquivoJsons.add(this.extrairSolutionAnswerArquivoJsonAPartir(arquivo));

		} catch (RepositoryExcpetion e) {
			throw new RepositoryExcpetion(e.getMessage(), e.getCause());
		}

		return solutionsAnswerArquivoJsons;
	}

	@Override
	public ArrayList<File> recuperarArquivos() throws RepositoryExcpetion { 
		ArrayList<File> arquivos = null;

		File arquivoDiretorio;
		try {
			arquivoDiretorio = arquivoNegocio.isDiretorioPadraoValido();

			if (arquivoDiretorio.listFiles().length <= 0)
				throw new RepositoryExcpetion(
						"Não foram encontrados arquivos ou base de arquivos para realizar a busca.");

			arquivos = (ArrayList<File>) Arrays.asList(arquivoDiretorio.listFiles());
		} catch (NegocioException e) {
			throw new RepositoryExcpetion("Não foram encontrados arquivos ou base de arquivos para realizar a busca.");
		}

		return arquivos;
	}
	
	private SolutionAnswerArquivoJson extrairSolutionAnswerArquivoJsonAPartir(File arquivo) throws RepositoryExcpetion{
		SolutionAnswerArquivoJson solutionAnswerArquivoJson = null;

		try {
			String strFileNameToken = arquivo.getAbsolutePath()
					.substring(arquivo.getAbsolutePath().lastIndexOf("/") + 1);
			
			boolean localizado = (strFileNameToken != null && !strFileNameToken.isEmpty());

			if (localizado) {
				ArquivoJson conteudoArquivoJson = arquivoNegocio
						.recuperarRespostaArquivoJsonExistente(strFileNameToken);

				solutionAnswerArquivoJson = SolutionAnswerArquivoJsonBuilder
						.umNovoSolutionAnswerArquivoJsonAPartir(strFileNameToken).contendoOArquivo(conteudoArquivoJson)
						.constroi();
			}
		} catch (NegocioException e) {
			throw new RepositoryExcpetion(e.getMessage(), e.getCause());
		}

		return solutionAnswerArquivoJson;
	}


}
