package com.desafio.dti.as;

import java.util.Date;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.dti.HistoricoCadastro;
import com.desafio.dti.HistoricoVendas;
import com.desafio.dti.Produto;
import com.desafio.dti.service.HistoricoCadastroService;
import com.desafio.dti.service.HistoricoVendasService;
import com.desafio.dti.service.ProdutoService;

@Service
public class ProdutoAS {
	
	private ProdutoService service;
	private HistoricoCadastroService cadastroService;
	private HistoricoVendasService vendasService;
	
	@Autowired
	public ProdutoAS(ProdutoService service, HistoricoCadastroService cadastroService,
			HistoricoVendasService vendasService) {
		this.service = service;
		this.cadastroService = cadastroService;
		this.vendasService = vendasService;
	}
	

	public List<Produto> findAll() {
		return this.service.findAll();
	}
	
	public List<HistoricoCadastro> findAllHistoricoCadastro() {
		return this.cadastroService.findAll();
	}
	
	public List<HistoricoVendas> findAllHistoricoVendas() {
		return this.vendasService.findAll();
	}
	
	public Produto findById(Integer id) {
		return this.service.findById(id);
	}

	@Transactional
	public Produto salvar(Produto produto) {
		if(produto == null) {
			new ServiceException("Produto não pode ser nulo.");
		}
		if(produto.getId() != null) {
			Produto produtoAntes = this.service.findById(produto.getId());
			if(produtoAntes.getQuantidade() > produto.getQuantidade()) {
				Integer total = produtoAntes.getQuantidade() - produto.getQuantidade();
				this.registrarVenda(produto, total);
				return this.service.salvar(produto);
			}
		}
		produto.setDisponivel(true);
		this.registrarCadastro(produto);
		return this.service.salvar(produto);
	}
	
	@Transactional
	public void remover(Integer id) {
		if(id == null) {
			new ServiceException("Id não pode ser nulo.");
		}
		this.service.remover(id);
		this.registrarCadastro(id, 0);
	}
	
	private void registrarCadastro(Integer id, Integer quantidade) {
		Produto produto = this.findById(id);
		produto.setQuantidade(quantidade);
		this.registrarCadastro(produto);
	}
	
	private void registrarCadastro(Produto produto) {
		HistoricoCadastro cadastro = new HistoricoCadastro();
		cadastro.setProduto(produto);
		cadastro.setQuantidade(produto.getQuantidade());
		cadastro.setDataCadastro(new Date());
		cadastro.setValorUnitario(produto.getValorUnitario());
		this.cadastroService.salvar(cadastro);
	}
	
	private void registrarVenda(Produto produto, Integer quantidadeVendida) {
		HistoricoVendas venda = new HistoricoVendas();
		venda.setProduto(produto);
		venda.setQuantidade(quantidadeVendida);
		venda.setDataVenda(new Date());
		venda.setValorUnitario(produto.getValorUnitario());
		this.vendasService.salvar(venda);
	}

}
