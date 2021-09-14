package com.wandson.food.domain.repository;

import org.springframework.stereotype.Repository;

import com.wandson.food.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

}