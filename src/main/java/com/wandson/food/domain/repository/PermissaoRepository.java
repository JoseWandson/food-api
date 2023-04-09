package com.wandson.food.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wandson.food.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
