package com.luan.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luan.helpdesk.domain.dtos.InstrutorDTO;
import com.luan.helpdesk.domain.enums.Perfil;

@Entity
public class Instrutor extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore//usado para evitar problema de serialização
	//um tecnico para muitos chamados
	@OneToMany(mappedBy = "instrutor")
	private List<Chamado> chamados = new ArrayList<>();

	public Instrutor() {
		super();
		addPerfil(Perfil.INSTRUTOR);//adiciona perfil ao instrutor quando for criado
	}

	public Instrutor(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
	}
	
	public Instrutor(InstrutorDTO obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dateCriacao = obj.getDateCriacao();
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
