package br.com.codenation.acelera.dev.java.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
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
import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolicitaArquivoJson;
import br.com.codenation.acelera.dev.java.negocio.SolicitaArquivoNegocio;
import br.com.codenation.acelera.dev.java.negocio.SolicitaArquivoNegocioImpl;
import br.com.codenation.acelera.dev.java.view.RegistroViewModelo;

public class SolicitaArquivoController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	private SolicitaArquivoNegocio solicitaArquivoNegocio;

	private RegistroViewModelo viewModelSolicitaArquivoJSON;

	private SolicitaArquivoJson solicitaArquivoJSON;

	private Boolean respostaMessageBox;

	public SolicitaArquivoController() {
		super();
		this.viewModelSolicitaArquivoJSON = new RegistroViewModelo();
		this.solicitaArquivoJSON = this.viewModelSolicitaArquivoJSON.getSolicitaArquivoJSON();
		this.solicitaArquivoNegocio = new SolicitaArquivoNegocioImpl();
		this.respostaMessageBox = Boolean.FALSE;
	}

	@Wire
	private Window windowViewSolicitarArquivo;

	@Wire
	private Textbox tokenBox;

	@Listen("onChange=#tokenBox")
	public void pressEnterInputTextBoxToken() {
		String token = tokenBox.getValue();
		this.solicitaArquivoJSON.setToken(token);
	}

	@Listen("onClick=#solicitarArquivoBt")
	public void clicouEmBtSolicitarArquivo() {
		if (this.solicitaArquivoJSON == null) {
			Messagebox.show("Erro na aplicação. Instância não inicializada", "Erro", Messagebox.OK, Messagebox.ERROR);
			return;
		}

		if (!this.solicitaArquivoJSON.isTokenValido()) {
			Messagebox.show("Nenhum token de acesso foi informado. Informe-o e tente novamente.", "Aviso",
					Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}

		this.dMessageBoxQuestion();

		if (!isAcceptOkMessageBox()) {
			Messagebox.show("Solicitação cancelada.", "Informação", Messagebox.OK, Messagebox.INFORMATION);
			return;
		}

		try {
			ArquivoJson arquivoJson = solicitaArquivoNegocio.salvarArquivo(this.solicitaArquivoJSON);

			String mensagemRetornoUsuario = (arquivoJson.isImportadoSalvo())
					? "Arquivo referente ao token já encontra-se importado e salvo."
					: "Salvo com sucesso.";

			Messagebox.show(mensagemRetornoUsuario, "Informação", Messagebox.OK, Messagebox.INFORMATION);
			this.showNotify(mensagemRetornoUsuario, windowViewSolicitarArquivo);
			this.limparCampos();

		} catch (NegocioException e) {
			Messagebox.show(e.getMessage(), "Erro", Messagebox.OK, Messagebox.ERROR);
			e.printStackTrace();
		}
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
	}

	private Boolean isAcceptOkMessageBox() {
		return this.respostaMessageBox;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void dMessageBoxQuestion() {
		Messagebox.show("Desejas realmente solicitar arquivo ao servidor?", "Pergunta",
				Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						if (Messagebox.ON_OK.equals(event.getName())) {
							respostaMessageBox = Boolean.TRUE;
						} else if (Messagebox.ON_CANCEL.equals(event.getName())) {
							respostaMessageBox = Boolean.FALSE;
						}
					}
				});
	}
}
