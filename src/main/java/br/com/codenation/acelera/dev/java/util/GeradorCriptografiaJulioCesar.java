package br.com.codenation.acelera.dev.java.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class GeradorCriptografiaJulioCesar {

	@SuppressWarnings("unused")
	private final static String LETRAS_ALFABETO = "a;b;c;d;e;f;g;h;i;j;k;l;m;n;o;p;q;r;s;t;u;v;w;x;y;z";

	/*
	 * ############################### Atributos
	 */

	private String mensagem;
	private String mensagemCifrada;
	private String mensagemDecifrada;
	private int cifra;
	private char[] alfabetoCriptografado;

	/*
	 * ############################### Constructors
	 */

	public GeradorCriptografiaJulioCesar(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public GeradorCriptografiaJulioCesar(String mensagem, int cifra) {
		this.mensagem = mensagem;
		this.cifra = cifra;
	}
	
	public GeradorCriptografiaJulioCesar(int cifra) {
		this.cifra = cifra;
		this.alfabetoCriptografado = this.geraAlfabetoCriptografado(AlfabetoUtil.getAlfabeto());
	}

	/*
	 * ############################### Getters & Setters
	 */

	public String getMensagemCifrada() {
		return mensagemCifrada;
	}

	public void setMensagemCifrada(String mensagemCifrada) {
		this.mensagemCifrada = mensagemCifrada;
	}

	public String getMensagemDecifrada() {
		return mensagemDecifrada;
	}

	/*
	 * ############################### Métodos de Ação
	 */

	private char[] geraAlfabetoCriptografado(char[] alfabeto) {
		int tamanhoAlfabeto = alfabeto.length;
		char[] alfabetoCriptografado = new char[tamanhoAlfabeto];
		int posicaoNoAlfabeto = 0;

		for (int i = 0; i < tamanhoAlfabeto; i++) {
			posicaoNoAlfabeto = (AlfabetoUtil.posicaoLetraAlfabeto(alfabeto[i], alfabeto) + this.cifra)
					% tamanhoAlfabeto;
			alfabetoCriptografado[i] = alfabeto[posicaoNoAlfabeto];
		}

		return alfabetoCriptografado;
	}

	public String criptografa(String entrada, boolean criptografar) {
		char[] letrasDaPalavra = entrada.toCharArray();

		StringBuilder palavraCifrada = new StringBuilder();

		for (int i = 0; i < letrasDaPalavra.length; i++) {
			char letraDaPalavra = letrasDaPalavra[i];

			if (AlfabetoUtil.naoEhUmaLetraMasEhValido(letraDaPalavra)) {
				palavraCifrada.append(letraDaPalavra);
			} else if (AlfabetoUtil.ehUmaLetra(letraDaPalavra)) {
				int posicaoNoAlfabeto = 0;

				char letra = ' ';

				if (criptografar) {
					posicaoNoAlfabeto = AlfabetoUtil.posicaoLetraAlfabeto(letraDaPalavra, AlfabetoUtil.getAlfabeto());
					letra = this.alfabetoCriptografado[posicaoNoAlfabeto];
				} else {
					posicaoNoAlfabeto = AlfabetoUtil.posicaoLetraAlfabeto(letraDaPalavra, this.alfabetoCriptografado);
					letra = AlfabetoUtil.getAlfabeto()[posicaoNoAlfabeto];
				}

				if (AlfabetoUtil.isLetraMinuscula(letraDaPalavra)) {
					letra = Character.toLowerCase(letra);
				}

				palavraCifrada.append(letra);
			} else {
				palavraCifrada.append('_');
			}
		}

		return palavraCifrada.toString();
	}
	
	/*
	 * ############################### Demais Métodos
	 */
	
	@SuppressWarnings("unused")
	private ArrayList<String> extrairListaLetrasAlfabeto(String conteudo, String token) {
		ArrayList<String> listTokens = new ArrayList<String>();
		StringTokenizer strToken = new StringTokenizer(conteudo, token);

		while (strToken.hasMoreTokens())
			listTokens.add(strToken.nextToken());

		return listTokens;
	}	
	
	public String gerarCriptografia() {		
		String mens = this.mensagem;
		int chave = this.cifra;
		
		char ascii;
		char x, y;
		
		// A chave tem que ter o tamanho do alfabeto
		while (chave >= 26) {
			chave = chave - 26;
		}
		
		for (int i = 0; i < mens.length(); i++) {
			// Tratamento Letras minusculas
			if (mens.charAt(i) >= 97 && mens.charAt(i) <= 122) {// letrans minusculas de acordo com a tabela ASCII
				if ((int) (mens.charAt(i) + chave) > 122) {
					x = (char) (mens.charAt(i) + chave);
					y = (char) (x - 122);
					ascii = (char) (96 + y);
					System.out.print(ascii + "");
				} else {
					ascii = (char) (mens.charAt(i) + chave);
					System.out.print(ascii + "");

				}
			}
			// Tratamento Letras mausculas
			if (mens.charAt(i) >= 65 && mens.charAt(i) <= 90) {
				if (mens.charAt(i) + chave > 90) {
					x = (char) (mens.charAt(i) + chave);
					y = (char) (x - 90);
					ascii = (char) (64 + y);
					System.out.print(ascii + "");
				} else {
					ascii = (char) (mens.charAt(i) + chave);
					System.out.print(ascii + "");
				}
			}
		}
		
		return null;
	}

	public void gerarCriptografia2() {
		char[] caracteres = this.mensagem.toCharArray();

		for (int i = 0; i < caracteres.length; i++) {
			int ascii = (int) caracteres[i] - this.cifra;
			System.out.print((char) ascii);
		}
	}

	public String cifra(String mensagem, int chave) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < mensagem.length(); i++) {
			char caracter = (char) (mensagem.charAt(i) + chave);
			builder.append(caracter);
		}

		return builder.toString();
	}

	public String decifra(String mensagem, int chave) {
		return this.cifra(mensagem, -chave);
	}

	public String cifrar(String mensagem, int chave) {
		return this.cifra(mensagem, +chave);
	}

}
