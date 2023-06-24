package com.luan.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luan.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
