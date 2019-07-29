package br.com.codenation.acelera.dev.java.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;
import br.com.codenation.acelera.dev.java.view.RegistroViewModelo;

public class GeraHashResumoCriptografadoController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	private RegistroViewModelo viewModelGeraHashResumoCriptografado;

	private SolutionAnswerArquivoJson solutionAnswerArquivoJson;

	@Wire
	private Window windowViewGerarHashCriptografado;

	@Wire
	private Textbox tokenBox;

	public GeraHashResumoCriptografadoController() {
		super();
		this.viewModelGeraHashResumoCriptografado = new RegistroViewModelo();
		this.solutionAnswerArquivoJson = this.viewModelGeraHashResumoCriptografado.getSolutionAnswerArquivoJson();
	}

	@Listen("onChange=#tokenBox")
	public void pressEnterInputTextBoxToken() {
		String token = tokenBox.getValue();
		this.solutionAnswerArquivoJson.setToken(token);
	}

	@Listen("onClick=#buscarArquivoAnswerBt")
	public void clicouEmBtSolicitarArquivo() {
		boolean verificaToken = Boolean.FALSE;

		if (this.solutionAnswerArquivoJson == null) {
			Messagebox.show("Erro na aplicação. Instância não inicializada", "Erro", Messagebox.OK, Messagebox.ERROR);
			return;
		}

		if (verificaToken && !this.solutionAnswerArquivoJson.isTokenValido()) {
			Messagebox.show("Nenhum token de acesso foi informado. Informe-o e tente novamente.", "Aviso",
					Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}

		Executions.getCurrent().sendRedirect("/views/visualiza_solutions_answer.zul");
	}

	@Listen("onClick=#cancelarBt")
	public void clicouEmBtCancelar() {
		Executions.getCurrent().sendRedirect("/index.zul");
	}
}
