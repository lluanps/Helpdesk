package com.luan.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luan.helpdesk.domain.Instrutor;
import com.luan.helpdesk.domain.Pessoa;
import com.luan.helpdesk.domain.dtos.InstrutorDTO;
import com.luan.helpdesk.repositories.InstrutorRepository;
import com.luan.helpdesk.repositories.PessoaRepository;
import com.luan.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.luan.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class InstrutorService {

	@Autowired
	private InstrutorRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder enc;

	public Instrutor findById(Integer id) {
		Optional<Instrutor> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
	}

	public List<Instrutor> findAll() {
		return repository.findAll();
	}

	public Instrutor create(InstrutorDTO dto) {
		dto.setId(null);
		dto.setSenha(enc.encode(dto.getSenha()));
		validaPorCpfEEmail(dto);
		Instrutor save = new Instrutor(dto);
		return repository.save(save);
	}

	public Instrutor update(Integer id, @Valid InstrutorDTO dto) {
		dto.setId(id);
		Instrutor oldObj = findById(id);
		
		if (!dto.getSenha().equals(oldObj.getSenha())) {
			dto.setSenha(enc.encode(dto.getSenha()));
		}
		
		validaPorCpfEEmail(dto);
		oldObj = new Instrutor(dto);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Instrutor obj = findById(id);
		// Verifica se o instrutor tem uma ordem em seu nome ou não
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Instrutor possui ordem de serviço e não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	private void validaPorCpfEEmail(InstrutorDTO dto) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(dto.getCpf());
		if (obj.isPresent() && obj.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}

		obj = pessoaRepository.findByEmail(dto.getEmail());
		if (obj.isPresent() && obj.get().getId() != dto.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema");
		}

	}

}
