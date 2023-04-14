package com.wandson.food.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank
	@Schema(example = "João da Silva", requiredMode = RequiredMode.REQUIRED)
	private String nome;

	@Email
	@NotBlank
	@Schema(example = "joao.ger@jwfood.com.br", requiredMode = RequiredMode.REQUIRED)
	private String email;

}