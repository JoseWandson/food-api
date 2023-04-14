package com.wandson.food.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {

	@NotNull
	@Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
	private Long produtoId;

	@NotNull
	@PositiveOrZero
	@Schema(example = "2", requiredMode = RequiredMode.REQUIRED)
	private Integer quantidade;

	@Schema(example = "Menos picante, por favor")
	private String observacao;
}