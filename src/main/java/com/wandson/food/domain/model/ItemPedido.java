package com.wandson.food.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
