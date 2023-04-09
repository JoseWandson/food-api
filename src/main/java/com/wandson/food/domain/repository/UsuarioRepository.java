package com.wandson.food.domain.repository;

import java.util.Optional;

import com.wandson.food.domain.model.Usuario;

public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);

}