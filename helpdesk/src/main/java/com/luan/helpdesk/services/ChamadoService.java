package com.luan.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.helpdesk.domain.Chamado;
import com.luan.helpdesk.domain.Cliente;
import com.luan.helpdesk.domain.Instrutor;
import com.luan.helpdesk.domain.dtos.ChamadoDTO;
import com.luan.helpdesk.domain.enums.Prioridade;
import com.luan.helpdesk.domain.enums.Status;
import com.luan.helpdesk.repositories.ChamadoRepository;
import com.luan.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;

	@Autowired
	private InstrutorService instrutorService;

	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! id: " + id));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO dto) {
		return repository.save(newChamado(dto));
	}

	public Chamado update(Integer id, @Valid ChamadoDTO dto) {
		dto.setId(id); // Assegura que obj vai ter um id
		Chamado oldObj = findById(id); // verifica se existe um chamado com o id passado na url
		oldObj = newChamado(dto); // Atualiza os dados usando o metodo newChamado
		return repository.save(oldObj);
	}

	private Chamado newChamado(ChamadoDTO obj) {
		Instrutor instrutor = instrutorService.findById(obj.getInstrutor());
		Cliente cliente = clienteService.findById(obj.getCliente());

		Chamado chamado = new Chamado();
		if (obj.getId() != null) {
			chamado.setId(obj.getId());
		}

		// Retorna data de fechamento do chamado
		if (obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}

		return setarDadosChamado(obj, instrutor, cliente, chamado);
	}

	private Chamado setarDadosChamado(ChamadoDTO obj, Instrutor instrutor, Cliente cliente, Chamado chamado) {
		chamado.setInstrutor(instrutor);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());

		return chamado;
	}

}
