package com.luan.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luan.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
