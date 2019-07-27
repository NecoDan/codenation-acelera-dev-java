package br.com.codenation.acelera.dev.java.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;
import br.com.codenation.acelera.dev.java.relatorio.DtoSolutionAnswerJson;

public class VisualizaSolutionsAnswerController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Window windowViewVisualizaSolutionsAnswerDynamic;
	
	private String token;

	private final ListModel<SolutionAnswerArquivoJson> solutionsAnswerArquivoJson = new ListModelList<SolutionAnswerArquivoJson>(
			new DtoSolutionAnswerJson().getSolutionAnswerArquivoJson());

	public ListModel<SolutionAnswerArquivoJson> getSolutionsAnswerArquivoJson() {
		return solutionsAnswerArquivoJson;
	}

	@Listen("onClick=#voltarBt")
	public void clicouEmBtVoltar() {
		Executions.getCurrent().sendRedirect("/views/decifra_criptografia.zul");
	}
	
	@Listen("onClick=#chkSelecionarTodos")
	public void clicouCheckBoxSelecionarTodos() {
		Messagebox.show("Chegou aqui ao clicar no checkbox", "Informação", Messagebox.OK, Messagebox.INFORMATION);
	}	
}
