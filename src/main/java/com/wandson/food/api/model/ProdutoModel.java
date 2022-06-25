package com.wandson.food.api.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Espetinho de Cupim")
	private String nome;

	@Schema(example = "Acompanha farinha, mandioca e vinagrete")
	private String descricao;

	@Schema(example = "12.50")
	private BigDecimal preco;

	@Schema(example = "true")
	private Boolean ativo;

}