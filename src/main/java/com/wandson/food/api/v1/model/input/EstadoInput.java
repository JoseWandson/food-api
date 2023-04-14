package com.wandson.food.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {

	@NotBlank
	@Schema(example = "Paraíba", requiredMode = RequiredMode.REQUIRED)
	private String nome;

}
