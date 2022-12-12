package com.wandson.food.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private Integer quantidade;
	private String observacao;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;

	public void calcularPrecoTotal() {
		BigDecimal precoUnitarioAux = precoUnitario;
		Integer quantidadeAux = quantidade;

		if (Objects.isNull(precoUnitarioAux)) {
			precoUnitarioAux = BigDecimal.ZERO;
		}

		if (Objects.isNull(quantidadeAux)) {
			quantidadeAux = 0;
		}

		precoTotal = precoUnitarioAux.multiply(new BigDecimal(quantidadeAux));
	}

}
