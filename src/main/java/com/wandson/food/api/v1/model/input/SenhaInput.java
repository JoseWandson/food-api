package com.wandson.food.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInput {

	@NotBlank
	@Schema(example = "123", requiredMode = RequiredMode.REQUIRED)
	private String senhaAtual;

	@NotBlank
	@Schema(example = "123", requiredMode = RequiredMode.REQUIRED)
	private String novaSenha;
}