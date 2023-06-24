package com.luan.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.luan.helpdesk.domain.enums.Perfil;

@Entity
public class Instrutor extends Pessoa {
	private static final long serialVersionUID = 1L;
	
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

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
