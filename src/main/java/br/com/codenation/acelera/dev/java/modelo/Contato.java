package br.com.codenation.acelera.dev.java.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contato {

	private Long id;
	private String nome;
	private Boolean ativo;
	private ArrayList<Email> emails;
	private ArrayList<Telefone> telefones;

	public Contato() {
		this.emails = new ArrayList<Email>();
		this.telefones = new ArrayList<Telefone>();
	}

	public Contato(Long id) {
		this.id = id;
		this.emails = new ArrayList<Email>();
		this.telefones = new ArrayList<Telefone>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public ArrayList<Email> getEmails() {
		return emails;
	}

	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}

	public void setEmails(Collection<Email> emails) {
		this.emails = new ArrayList<Email>();
		this.emails.addAll(emails);
	}

	public void addEmail(Email email) {
		Optional<Email> mail = this.emails.stream().parallel().filter(i -> i.getEndereco().equals(email.getEndereco()))
				.findAny();

		if (!mail.isPresent())
			this.emails.add(email);
	}

	public void adicionarEmails(ArrayList<Email> emails) {
		emails.stream().forEach(i -> {
			i.setContato(this);
			this.emails.add(i);
		});
	}

	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ArrayList<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void setTelefones(Collection<Telefone> telefones) {
		this.telefones = new ArrayList<Telefone>();
		this.telefones.addAll(telefones);
	}

	public void addTelefone(Telefone telefone) {
		List<Telefone> fones = this.telefones.stream().filter(f -> f.getNumero().equals(telefone.getNumero()))
				.collect(Collectors.toList());

		if (fones != null && fones.isEmpty())
			this.telefones.add(telefone);
	}
	
	public void adicionarTelefones(ArrayList<Telefone> telefones) {
		telefones.stream().forEach(i -> {
			i.setContato(this);
			this.telefones.add(i);
		});
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", nome=" + nome + ", ativo=" + ativo + ", emails=" + emails + ", telefones="
				+ telefones + "]";
	}
}
