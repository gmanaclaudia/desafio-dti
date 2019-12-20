package com.desafio.dti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.desafio.dti.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query("SELECT p FROM Produto p WHERE p.disponivel = true")
	public List<Produto> findDisponiveis();

}
