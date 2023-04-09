package com.wandson.food.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wandson.food.domain.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}