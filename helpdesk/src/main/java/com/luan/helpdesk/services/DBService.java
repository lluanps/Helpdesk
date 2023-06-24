package com.luan.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.helpdesk.domain.Chamado;
import com.luan.helpdesk.domain.Cliente;
import com.luan.helpdesk.domain.Instrutor;
import com.luan.helpdesk.domain.enums.Perfil;
import com.luan.helpdesk.domain.enums.Prioridade;
import com.luan.helpdesk.domain.enums.Status;
import com.luan.helpdesk.repositories.ChamadoRepository;
import com.luan.helpdesk.repositories.ClienteRepository;
import com.luan.helpdesk.repositories.InstrutorRepository;

@Service
public class DBService {
	
	@Autowired
	private InstrutorRepository instrutorRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		
		Instrutor inst1 = new Instrutor(null, "Luan Pinheiro", "222.632.070-90", "luan@gmail.com", "admin");
		inst1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Messi", "530.212.030-51", "messi@gmail.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", inst1, cli1);
		
		instrutorRepository.saveAll(Arrays.asList(inst1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
	
	
}
