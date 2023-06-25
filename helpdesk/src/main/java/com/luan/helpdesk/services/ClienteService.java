package com.luan.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.helpdesk.domain.Cliente;
import com.luan.helpdesk.domain.Pessoa;
import com.luan.helpdesk.domain.dtos.ClienteDTO;
import com.luan.helpdesk.repositories.ClienteRepository;
import com.luan.helpdesk.repositories.PessoaRepository;
import com.luan.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.luan.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente id não encontrado! id: " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO dto) {
		dto.setId(null);
		validaPorCpfEEmail(dto);
		Cliente save = new Cliente();
		return repository.save(save);
	}

	private void validaPorCpfEEmail(ClienteDTO dto) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(dto.getCpf());
		if (obj.isPresent() && obj.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("CPF ja cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(dto.getEmail());
		if (obj.isPresent() && obj.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("Email ja cadastrado no sistema!");
		}
		
	}

	

}
