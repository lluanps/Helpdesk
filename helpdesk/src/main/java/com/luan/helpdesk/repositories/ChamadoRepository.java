package com.luan.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luan.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
