package br.com.codenation.acelera.dev.java.relatorio;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;
import br.com.codenation.acelera.dev.java.negocio.CriptografiaNegocio;
import br.com.codenation.acelera.dev.java.negocio.CriptografiaNegocioImpl;

public class SimpleRendererSolutionAnswerJson implements RowRenderer<SolutionAnswerArquivoJson> {

	@Override
	public void render(Row linha, SolutionAnswerArquivoJson solutionAnswerArquivoJson, int index) throws Exception {
		if (solutionAnswerArquivoJson != null && solutionAnswerArquivoJson.getArquivoJson() != null) {
			ArquivoJson arquivoJson = solutionAnswerArquivoJson.getArquivoJson();

			linha.appendChild(this.criarDivCheckBoxSelecionaTodos(index, solutionAnswerArquivoJson));
			linha.appendChild(this.criarLabel(solutionAnswerArquivoJson.getToken(), solutionAnswerArquivoJson));
			linha.appendChild(this.criarLabel(String.valueOf(arquivoJson.getNumeroCasas().intValue()),
					solutionAnswerArquivoJson));
			
			linha.appendChild(this.criarLabel(arquivoJson.getCifrado(), solutionAnswerArquivoJson));
			linha.appendChild(this.criarLabel(arquivoJson.getDecifrado(), solutionAnswerArquivoJson));
			linha.appendChild(this.criarLabel(arquivoJson.getResumoCriptografico(), solutionAnswerArquivoJson));
			linha.appendChild(
					this.criarLabel(solutionAnswerArquivoJson.toStringStatusSolution(), solutionAnswerArquivoJson));
			
			linha.appendChild(this.criarDivComBotaoAPartir(solutionAnswerArquivoJson));
		}
	}

	private Label criarLabel(String texto, SolutionAnswerArquivoJson solutionAnswerArquivoJson) {
		Label label = new Label(texto);
		label.setStyle("font-weight:bold; color: " + toStringColorLinhaPorStatus(solutionAnswerArquivoJson));
		return label;
	}

	private String toStringColorLinhaPorStatus(SolutionAnswerArquivoJson solutionAnswerArquivoJson) {
		String strColor = "";

		if (solutionAnswerArquivoJson.isFinalizado())
			return strColor = "#1a237e";

		if (solutionAnswerArquivoJson.isCifrado() && !solutionAnswerArquivoJson.isDecifrado())
			strColor = "#b71c1c";

		if (solutionAnswerArquivoJson.isDecifrado() && !solutionAnswerArquivoJson.isFinalizado())
			strColor = "#4caf50";

		return strColor;
	}

	private Div criarDivCheckBoxSelecionaTodos(int index, SolutionAnswerArquivoJson solutionAnswerArquivoJson) {
		final Div div = new Div();
		final Checkbox checkMarcador = new Checkbox(" " + String.valueOf(index + 1));
		div.setStyle("font-weight:bold; color: " + toStringColorLinhaPorStatus(solutionAnswerArquivoJson));
		checkMarcador.setParent(div);

		return div;
	}

	private String extrairCaminhoImagemBotaoAPartir(SolutionAnswerArquivoJson solutionAnswerArquivoJson) {
		String caminhoImagemBotao = "";

		if (solutionAnswerArquivoJson.isFinalizado())
			return caminhoImagemBotao = "/resources/images/produto.png";

		if (solutionAnswerArquivoJson.isCifrado() && !solutionAnswerArquivoJson.isDecifrado())
			caminhoImagemBotao = "/resources/images/key.png";

		if (solutionAnswerArquivoJson.isDecifrado() && !solutionAnswerArquivoJson.isFinalizado())
			caminhoImagemBotao = "/resources/images/icons8-hash.png";

		return caminhoImagemBotao;
	}

	private String extrairDescTituloBotaoAPartir(SolutionAnswerArquivoJson solutionAnswerArquivoJson) {
		String descTituloBotao = "";

		if (solutionAnswerArquivoJson.isFinalizado())
			return descTituloBotao = "Enviar";

		if (solutionAnswerArquivoJson.isCifrado() && !solutionAnswerArquivoJson.isDecifrado())
			descTituloBotao = "Decifrar";

		if (solutionAnswerArquivoJson.isDecifrado() && !solutionAnswerArquivoJson.isFinalizado())
			descTituloBotao = "Cifrar";

		return descTituloBotao;
	}

	private String extrairDescTextoToolTipBotaoAPartir(SolutionAnswerArquivoJson solutionAnswerArquivoJson) {
		String descTextoToolTip = "";

		if (solutionAnswerArquivoJson.isFinalizado())
			return descTextoToolTip = "Enviar solução para o servidor";

		if (solutionAnswerArquivoJson.isCifrado() && !solutionAnswerArquivoJson.isDecifrado())
			descTextoToolTip = "Efetuar decifração do cifrado";

		if (solutionAnswerArquivoJson.isDecifrado() && !solutionAnswerArquivoJson.isFinalizado())
			descTextoToolTip = "Gerar criptografia HASH";

		return descTextoToolTip;
	}

	private String extrairMensagemQuestionAPartir(SolutionAnswerArquivoJson solutionAnswerArquivoJson) {
		String mensagem = "";

		if (solutionAnswerArquivoJson.isFinalizado())
			return mensagem = "Desejas realmente enviar solução para o servidor?";

		if (solutionAnswerArquivoJson.isCifrado() && !solutionAnswerArquivoJson.isDecifrado())
			mensagem = "Desejas decifrar o contexto cifrado a seguir?";

		if (solutionAnswerArquivoJson.isDecifrado() && !solutionAnswerArquivoJson.isFinalizado())
			mensagem = "Desejas gerar criptografia HASH do contexto decifrado a seguir?";

		return mensagem;
	}

	private void recarregarPagina() {
		Executions.getCurrent().sendRedirect("/views/visualiza_solutions_answer.zul");
	}

	private Boolean ativaBotaoEfetuarAcao(SolutionAnswerArquivoJson solutionAnswerArquivoJson) {
		Boolean ativado = Boolean.TRUE;
		
		if(solutionAnswerArquivoJson.isFinalizado())
			ativado = Boolean.FALSE;

		if (!solutionAnswerArquivoJson.isFinalizado() && solutionAnswerArquivoJson.isCifrado()
				&& !solutionAnswerArquivoJson.isDecifrado())
			ativado = Boolean.FALSE;

		if (solutionAnswerArquivoJson.isDecifrado() && !solutionAnswerArquivoJson.isFinalizado())
			ativado = Boolean.FALSE;

		return ativado;
	}
	
	private Boolean processarDadosAnswerSolucaoRespostaPor(SolutionAnswerArquivoJson solutionAnswerArquivoJson)
			throws NegocioException {
		Boolean response = Boolean.FALSE;
		CriptografiaNegocio criptografiaNegocio = new CriptografiaNegocioImpl();

		if (solutionAnswerArquivoJson.isFinalizado())
			response = criptografiaNegocio.enviarSolucaoAnswerResposta(solutionAnswerArquivoJson);

		if (solutionAnswerArquivoJson.isValidoEImportado() && solutionAnswerArquivoJson.isCifrado()
				&& !solutionAnswerArquivoJson.isDecifrado())
			response = criptografiaNegocio.efetuarDecifracaoCriptografia(solutionAnswerArquivoJson);

		if (solutionAnswerArquivoJson.isDecifrado())
			response = criptografiaNegocio.efetuarHashResumoCriptografado(solutionAnswerArquivoJson);

		return response;
	}

	private Div criarDivComBotaoAPartir(SolutionAnswerArquivoJson solutionAnswerArquivoJson) {
		final Div div = new Div();

		final Button botao = new Button(this.extrairDescTituloBotaoAPartir(solutionAnswerArquivoJson),
				this.extrairCaminhoImagemBotaoAPartir(solutionAnswerArquivoJson));
		botao.setTooltiptext(this.extrairDescTextoToolTipBotaoAPartir(solutionAnswerArquivoJson));
		botao.setHflex("2");
		botao.setParent(div);
		botao.setDisabled(ativaBotaoEfetuarAcao(solutionAnswerArquivoJson));

		botao.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void onEvent(Event event) throws Exception {
				Messagebox.show(extrairMensagemQuestionAPartir(solutionAnswerArquivoJson), "Pergunta",
						Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {
							@Override
							public void onEvent(Event event) throws Exception {
								if (Messagebox.ON_CANCEL.equals(event.getName()))
									return;

								Boolean response = processarDadosAnswerSolucaoRespostaPor(solutionAnswerArquivoJson);

								if (response) {
									botao.setDisabled(response);

									Messagebox.show("Answer solução, operação realizado com sucesso.", "Informação",
											Messagebox.OK, Messagebox.INFORMATION);
									recarregarPagina();
								}
							}
						});
			}
		});
		
		return div;
	}
}
