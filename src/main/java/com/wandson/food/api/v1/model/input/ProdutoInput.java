package com.wandson.food.api.v1.model.input;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

	@NotBlank
	@Schema(example = "Espetinho de Cupim", requiredMode = RequiredMode.REQUIRED)
	private String nome;

	@NotBlank
	@Schema(example = "Acompanha farinha, mandioca e vinagrete", requiredMode = RequiredMode.REQUIRED)
	private String descricao;

	@NotNull
	@PositiveOrZero
	@Schema(example = "12.50", requiredMode = RequiredMode.REQUIRED)
	private BigDecimal preco;

	@NotNull
	@Schema(example = "true", requiredMode = RequiredMode.REQUIRED)
	private Boolean ativo;

}