package com.wandson.food.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StatusPedido {

	CRIADO("Criado"), CONFIRMADO("Confirmado"), ENTREGUE("Entregue"), CANCELADO("Cancelado");

	private String descricao;

}
