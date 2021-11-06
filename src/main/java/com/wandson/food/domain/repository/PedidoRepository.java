package com.wandson.food.domain.repository;

import org.springframework.stereotype.Repository;

import com.wandson.food.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

}
