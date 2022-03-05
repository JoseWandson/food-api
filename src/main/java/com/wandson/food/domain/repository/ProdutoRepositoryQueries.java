package com.wandson.food.domain.repository;

import com.wandson.food.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

	FotoProduto save(FotoProduto foto);

	void delete(FotoProduto foto);

}
