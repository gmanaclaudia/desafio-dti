package com.desafio.dti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.dti.HistoricoVendas;
import com.desafio.dti.repository.HistoricoVendasRepository;

@Component
public class HistoricoVendasService {
	
	private HistoricoVendasRepository repository;
	
	@Autowired
	public HistoricoVendasService (HistoricoVendasRepository repository) {
		this.repository = repository;
	}

	
	public void salvar(HistoricoVendas venda) {
		this.repository.save(venda);		
	}


	public List<HistoricoVendas> findAll() {
		return this.repository.findAll();
	}

}
