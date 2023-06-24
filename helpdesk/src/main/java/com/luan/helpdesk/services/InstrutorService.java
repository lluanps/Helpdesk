package com.luan.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.helpdesk.domain.Instrutor;
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
}
