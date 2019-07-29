package br.com.codenation.acelera.dev.java.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.codenation.acelera.dev.java.exception.NegocioException;
import br.com.codenation.acelera.dev.java.modelo.SolicitaArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;
import br.com.codenation.acelera.dev.java.negocio.SolutionAnswerArquivoJsonNegocio;
import br.com.codenation.acelera.dev.java.negocio.SolutionAnswerArquivoJsonNegocioImpl;
import br.com.codenation.acelera.dev.java.view.RegistroViewModelo;

public class SolicitaArquivoController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	private RegistroViewModelo viewModelSolicitaArquivoJSON;

	private SolicitaArquivoJson solicitaArquivoJson;

	private Boolean respostaMessageBox;

	public SolicitaArquivoController() {
		super();
		this.viewModelSolicitaArquivoJSON = new RegistroViewModelo();
		this.solicitaArquivoJson = this.viewModelSolicitaArquivoJSON.getSolicitaArquivoJSON();
		this.respostaMessageBox = Boolean.FALSE;
	}

	@Wire
	private Window windowViewSolicitarArquivo;

	@Wire
	private Textbox tokenBox;

	@Listen("onChange=#tokenBox")
	public void pressEnterInputTextBoxToken() {
		String token = tokenBox.getValue();
		this.solicitaArquivoJson.setToken(token);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Listen("onClick=#solicitarArquivoBt")
	public void clicouEmBtSolicitarArquivo() {
		if (this.solicitaArquivoJson == null) {
			Messagebox.show("Erro na aplicação. Instância não inicializada", "Erro", Messagebox.OK, Messagebox.ERROR);
			return;
		}

		if (!this.solicitaArquivoJson.isTokenValido()) {
			Messagebox.show("Nenhum token de acesso foi informado. Informe-o e tente novamente.", "Aviso",
					Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}

		Messagebox.show("Desejas realmente solicitar arquivo ao servidor?", "Pergunta",
				Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						if (Messagebox.ON_CANCEL.equals(event.getName())) {
							Messagebox.show("Solicitação cancelada.", "Informação", Messagebox.OK,
									Messagebox.INFORMATION);
							return;
						}

						try {
							SolutionAnswerArquivoJsonNegocio solutionAnswerArquivoJsonNegocio = new SolutionAnswerArquivoJsonNegocioImpl();
							SolutionAnswerArquivoJson solutionAnswerArquivoJson = solutionAnswerArquivoJsonNegocio
									.processarSolicitacaoArquivo(solicitaArquivoJson);

							String mensagemRetornoUsuario = (solutionAnswerArquivoJson.isValidoEImportado())
									? "Arquivo referente ao token já encontra-se importado e salvo."
									: "Salvo com sucesso.";

							Messagebox.show(mensagemRetornoUsuario, "Informação", Messagebox.OK,
									Messagebox.INFORMATION);
							limparCampos();
						} catch (NegocioException e) {
							Messagebox.show(e.getMessage(), "Erro", Messagebox.OK, Messagebox.ERROR);
							e.printStackTrace();
						}
					}
				});
	}

	@Listen("onClick=#cancelarBt")
	public void clicouEmBtCancelar() {
		Executions.getCurrent().sendRedirect("/index.zul");
	}

	private void showNotify(String mensagem, Component componente) {
		Clients.showNotification(mensagem, "info", componente, "end_center", 2000);
	}

	private void limparCampos() {
		this.tokenBox.setValue("");
		Executions.getCurrent().sendRedirect("/views/solicita_arquivo_server.zul");
	}

	private Boolean isAcceptOkMessageBox() {
		return this.respostaMessageBox;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void dMessageBoxQuestion() {

	}
}
