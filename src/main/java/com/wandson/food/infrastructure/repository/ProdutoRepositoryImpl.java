package com.wandson.food.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wandson.food.domain.model.FotoProduto;
import com.wandson.food.domain.repository.ProdutoRepositoryQueries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public FotoProduto save(FotoProduto foto) {
		return manager.merge(foto);
	}

	@Override
	@Transactional
	public void delete(FotoProduto foto) {
		manager.remove(foto);
	}

}
