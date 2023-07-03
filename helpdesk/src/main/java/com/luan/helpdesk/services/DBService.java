package com.luan.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private BCryptPasswordEncoder enc;

	public void instanciaDB() {
		
		Instrutor inst1 = new Instrutor(null, "Luan Pinheiro", "22263207090", "luan@gmail.com", enc.encode("admin"));
		inst1.addPerfil(Perfil.ADMIN);
		Instrutor inst2 = new Instrutor(null, "Falcão", "04041557070", "falcao@gmail.com", enc.encode("admin"));
		inst1.addPerfil(Perfil.INSTRUTOR);
		Instrutor inst3 = new Instrutor(null, "Ronaldo Nazário", "77714353007", "ronaldo@gmail.com", enc.encode("admin"));
		inst1.addPerfil(Perfil.INSTRUTOR);
		
		Cliente cli1 = new Cliente(null, "Messi", "53021203051", "messi@gmail.com", enc.encode("123"));
		Cliente cli2 = new Cliente(null, "Neymar Jr.", "37018789001", "neymar@gmail.com", enc.encode("123"));
		Cliente cli3 = new Cliente(null, "Cristiano R.", "96084382096", "cr7@gmail.com", enc.encode("123"));
		Cliente cli4 = new Cliente(null, "Vinicius Jr.", "34617950036", "vinijr@gmail.com", enc.encode("123"));
		Cliente cli5 = new Cliente(null, "Thiago Silva", "67387091021", "thiagosilva@gmail.com", enc.encode("123"));
		Cliente cli6 = new Cliente(null, "Modric", "33197821009", "modric@gmail.com", enc.encode("123"));
		Cliente cli7 = new Cliente(null, "Mbappé", "99672304013", "mbappe@gmail.com", enc.encode("123"));
		Cliente cli8 = new Cliente(null, "Rodrygo", "13655051034", "rodrygo@gmail.com", enc.encode("123"));
		Cliente cli9 = new Cliente(null, "Haaland", "16844448023", "haaland@gmail.com", enc.encode("123"));
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", inst1, cli1);
		Chamado c2 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 02", "Segundo chamado", inst2, cli2);
		Chamado c3 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 03", "Chamado urgente", inst3, cli2);
		
		instrutorRepository.saveAll(Arrays.asList(inst1));
		instrutorRepository.saveAll(Arrays.asList(inst2));
		instrutorRepository.saveAll(Arrays.asList(inst3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		clienteRepository.saveAll(Arrays.asList(cli2));
		clienteRepository.saveAll(Arrays.asList(cli3));
		clienteRepository.saveAll(Arrays.asList(cli4));
		clienteRepository.saveAll(Arrays.asList(cli5));
		clienteRepository.saveAll(Arrays.asList(cli6));
		clienteRepository.saveAll(Arrays.asList(cli7));
		clienteRepository.saveAll(Arrays.asList(cli8));
		clienteRepository.saveAll(Arrays.asList(cli9));
		
		chamadoRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(c2));
		chamadoRepository.saveAll(Arrays.asList(c3));
	}
	
}
