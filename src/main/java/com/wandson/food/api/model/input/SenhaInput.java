package com.wandson.food.api.model.input;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInput {

	@NotBlank
	@Schema(example = "123", required = true)
	private String senhaAtual;

	@NotBlank
	@Schema(example = "123", required = true)
	private String novaSenha;
}