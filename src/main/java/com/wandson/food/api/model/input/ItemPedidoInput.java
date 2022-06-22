package com.wandson.food.api.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {

	@NotNull
	@Schema(example = "1", required = true)
	private Long produtoId;

	@NotNull
	@PositiveOrZero
	@Schema(example = "2", required = true)
	private Integer quantidade;

	@Schema(example = "Menos picante, por favor")
	private String observacao;
}