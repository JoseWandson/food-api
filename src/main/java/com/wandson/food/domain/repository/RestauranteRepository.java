package com.wandson.food.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.wandson.food.domain.model.Restaurante;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
		JpaSpecificationExecutor<Restaurante> {

	@Query("from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();

}
