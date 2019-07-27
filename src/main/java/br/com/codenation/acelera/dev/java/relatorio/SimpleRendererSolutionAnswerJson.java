package br.com.codenation.acelera.dev.java.relatorio;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import br.com.codenation.acelera.dev.java.modelo.ArquivoJson;
import br.com.codenation.acelera.dev.java.modelo.SolutionAnswerArquivoJson;

public class SimpleRendererSolutionAnswerJson implements RowRenderer<SolutionAnswerArquivoJson> {

	@Override
	public void render(Row linha, SolutionAnswerArquivoJson solutionAnswerArquivoJson, int index) throws Exception {
		if (solutionAnswerArquivoJson != null && solutionAnswerArquivoJson.getArquivoJson() != null) {

			final Div divCheckBox = new Div();
			final Checkbox checkMarcador = new Checkbox(" " + String.valueOf(index + 1));
			checkMarcador.setParent(divCheckBox);
			linha.appendChild(divCheckBox);

			ArquivoJson arquivoJson = solutionAnswerArquivoJson.getArquivoJson();
			linha.appendChild(new Label(solutionAnswerArquivoJson.getToken()));
			linha.appendChild(new Label(String.valueOf(arquivoJson.getNumeroCasas().intValue())));
			linha.appendChild(new Label(arquivoJson.getCifrado()));
			linha.appendChild(new Label(arquivoJson.getDecifrado()));
			linha.appendChild(new Label(arquivoJson.getResumoCriptografico()));			
			linha.appendChild(new Label(arquivoJson.getResumoCriptografico()));

			final Div divBtDecifrar = new Div();
			final Button btDecifrar = new Button("Decifrar", "/resources/images/key.png");
			btDecifrar.setTooltiptext("Efetuar decifração do cifrado");
			btDecifrar.setParent(divBtDecifrar);

			btDecifrar.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					divBtDecifrar.appendChild(new Label("Clicou aqui"));
					btDecifrar.setDisabled(Boolean.TRUE);
				}
			});

			linha.appendChild(divBtDecifrar);
		}
	}
}
