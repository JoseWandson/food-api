package com.wandson.food.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wandson.food.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
		JpaSpecificationExecutor<Restaurante> {

	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	@Query("from Restaurante r join r.cozinha")
	List<Restaurante> findAll();

	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

	int countByCozinhaId(Long cozinha);

}
