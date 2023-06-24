package com.luan.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.helpdesk.domain.Instrutor;
import com.luan.helpdesk.domain.dtos.InstrutorDTO;
import com.luan.helpdesk.repositories.InstrutorRepository;
import com.luan.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class InstrutorService {

	@Autowired
	private InstrutorRepository repository;

	public Instrutor findById(Integer id) {
		Optional<Instrutor> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
	}

	public List<Instrutor> findAll() {
		return repository.findAll();
	}

	public Instrutor create(InstrutorDTO dto) {
		dto.setId(null);
		Instrutor save = new Instrutor(dto);
		return repository.save(save);
	}

}
