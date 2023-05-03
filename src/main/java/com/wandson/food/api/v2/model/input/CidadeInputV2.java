package com.wandson.food.api.v2.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CidadeInput")
public class CidadeInputV2 {

	@NotBlank
	@Schema(example = "João Pessoa", requiredMode = RequiredMode.REQUIRED)
	private String nomeCidade;

	@NotNull
	@Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
	private Long idEstado;

}