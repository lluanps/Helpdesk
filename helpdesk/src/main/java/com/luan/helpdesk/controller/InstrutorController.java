package com.luan.helpdesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luan.helpdesk.domain.Instrutor;
import com.luan.helpdesk.domain.dtos.InstrutorDTO;
import com.luan.helpdesk.services.InstrutorService;

@RestController
@RequestMapping(value = "/instrutores")
public class InstrutorController {
	
	@Autowired
	private InstrutorService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<InstrutorDTO> findById(@PathVariable Integer id) {
		Instrutor obj = service.findById(id);
		return ResponseEntity.ok().body(new InstrutorDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<InstrutorDTO>> findAll() {
		List<Instrutor> list = service.findAll();
		List<InstrutorDTO> listDto = list.stream().map(x -> new InstrutorDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/save")
	public ResponseEntity<InstrutorDTO> create(@Valid @RequestBody InstrutorDTO dto) {
		Instrutor entity = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<InstrutorDTO> update(@PathVariable Integer id, @Valid @RequestBody InstrutorDTO dto) {
		Instrutor obj = service.update(id, dto);
		return ResponseEntity.ok().body(new InstrutorDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<InstrutorDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


	
}
