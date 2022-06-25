package com.wandson.food.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

	@NotBlank
	@Schema(example = "Espetinho de Cupim", required = true)
	private String nome;

	@NotBlank
	@Schema(example = "Acompanha farinha, mandioca e vinagrete", required = true)
	private String descricao;

	@NotNull
	@PositiveOrZero
	@Schema(example = "12.50", required = true)
	private BigDecimal preco;

	@NotNull
	@Schema(example = "true", required = true)
	private Boolean ativo;

}