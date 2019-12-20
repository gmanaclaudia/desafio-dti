package com.desafio.dti.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.dti.HistoricoCadastro;
import com.desafio.dti.HistoricoVendas;
import com.desafio.dti.Produto;
import com.desafio.dti.as.ProdutoAS;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ProdutoAPI {

	private ProdutoAS as;
	
	@Autowired
    public ProdutoAPI(ProdutoAS as) {
        this.as = as;
    }
	
	@RequestMapping("/qry/find-all")
	public List<Produto> findAll() {
		return this.as.findAll();
	}
	
	@RequestMapping("/qry/find-all-historico-cadastro")
	public List<HistoricoCadastro> findAllHistoricoCadastro() {
		return this.as.findAllHistoricoCadastro();
	}
	
	@RequestMapping("/qry/find-all-historico-vendas")
	public List<HistoricoVendas> findAllHistoricoVendas() {
		return this.as.findAllHistoricoVendas();
	}
	
	@RequestMapping("/{id}")
	public Produto findById(@PathVariable(value = "id") Integer id) {
		return this.as.findById(id);
	}
	
	@PostMapping
	public Produto salvar(@RequestBody Produto produto) {
		return this.as.salvar(produto);
	}
	
	@PostMapping("/remover")
	public void remover(@RequestBody String id) {
		this.as.remover(Integer.parseInt(id));
	}
}
