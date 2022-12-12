package com.wandson.food.domain.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FotoProduto {

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "produto_id")
	private Long id;
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	private Produto produto;

	public Long getRestauranteId() {
		if (Objects.nonNull(produto)) {
			return produto.getRestaurante().getId();
		}
		return null;
	}

}
