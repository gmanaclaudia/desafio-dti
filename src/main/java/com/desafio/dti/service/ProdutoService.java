package com.desafio.dti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.dti.Produto;
import com.desafio.dti.repository.ProdutoRepository;

@Component
public class ProdutoService {

	private ProdutoRepository repository;
	
	@Autowired
	public ProdutoService(ProdutoRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly = true)
	public List<Produto> findAll() {
		return this.repository.findDisponiveis();
	}
	
	@Transactional
	public Produto findById(Integer id) {
		return this.repository.findById(id).orElse(null);
	}

	public Produto salvar(Produto produto) {
		return this.repository.save(produto);
	}

	public void remover(Integer id) {
		Produto produto = this.findById(id);
		produto.setDisponivel(false);
		this.salvar(produto);
	}

}
