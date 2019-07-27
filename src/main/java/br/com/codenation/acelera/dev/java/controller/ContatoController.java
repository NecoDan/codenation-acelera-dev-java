package br.com.codenation.acelera.dev.java.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;

import br.com.codenation.acelera.dev.java.modelo.Contato;

@SuppressWarnings("serial")
public class ContatoController extends SelectorComposer<Component> {

	// Atributos
	@Wire
	private Textbox nomeBox;

	@Wire
	private Datebox dataBox;

	@Wire
	private Textbox telefone1Box;

	@Wire
	private Textbox telefone2Box;

	@Wire
	private Textbox celularBox;

	@Wire
	private Button salvarBt;

	@Wire
	private Button cancelarBt;

	@Listen("onClick=#salvarBt")
	public void clicouEmBtSalvar() {

	}

	@Listen("onClick=#cancelarBt")
	public void clicouEmBtCancelar() {
	}

	private Contato extrairContatoAPartirCampos() {
		Contato contato = new Contato();

		return contato;
	}
}
