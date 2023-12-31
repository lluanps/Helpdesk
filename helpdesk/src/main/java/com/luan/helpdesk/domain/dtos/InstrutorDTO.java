package com.luan.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luan.helpdesk.domain.Instrutor;
import com.luan.helpdesk.domain.enums.Perfil;

public class InstrutorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	
	@NotNull(message = "Campo nome é obrigatório")
	protected String nome;
	
	@NotNull(message = "Campo cpf é obrigatório")
	protected String cpf;
	
	@NotNull(message = "Campo email é obrigatório")
	protected String email;
	
	@NotNull(message = "Campo senha é obrigatório")
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dateCriacao = LocalDate.now();

	public InstrutorDTO() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public InstrutorDTO(Instrutor obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dateCriacao = obj.getDateCriacao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfis) {
		this.perfis.add(perfis.getCodigo());
	}

	public LocalDate getDateCriacao() {
		return dateCriacao;
	}

	public void setDateCriacao(LocalDate dateCriacao) {
		this.dateCriacao = dateCriacao;
	}
	
}
