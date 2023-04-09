package com.wandson.food.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wandson.food.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
