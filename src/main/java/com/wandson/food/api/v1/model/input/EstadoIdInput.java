package com.wandson.food.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInput {

	@NotNull
	@Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
	private Long id;

}
