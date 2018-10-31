package com.srsolution.vinhos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srsolution.vinhos.model.Produto;

public interface Produtos extends JpaRepository<Produto, Long>{
	
	public List<Produto> findByNomeContainingIgnoreCase (String nome);
	Produto findById(Long id);

	
}
