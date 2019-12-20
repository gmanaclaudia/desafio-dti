package com.desafio.dti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.dti.HistoricoCadastro;
import com.desafio.dti.repository.HistoricoCadastroRepository;

@Component
public class HistoricoCadastroService {
	
	private HistoricoCadastroRepository repository;
	
	@Autowired
	public HistoricoCadastroService(HistoricoCadastroRepository repository) {
		this.repository = repository;
	}

	public void salvar(HistoricoCadastro cadastro) {
		this.repository.save(cadastro);		
	}

	public List<HistoricoCadastro> findAll() {
		return this.repository.findAll();
	}

}
