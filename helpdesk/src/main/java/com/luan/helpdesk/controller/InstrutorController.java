package com.luan.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
