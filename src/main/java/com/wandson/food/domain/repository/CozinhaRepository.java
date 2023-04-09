package com.wandson.food.domain.repository;

import java.util.List;
import java.util.Optional;

import com.wandson.food.domain.model.Cozinha;

public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

	List<Cozinha> findTodasByNomeContaining(String nome);

	Optional<Cozinha> findByNome(String nome);

	boolean existsByNome(String nome);

}