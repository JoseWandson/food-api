package com.wandson.food.api.v1.model.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank
	@Schema(example = "João da Silva", required = true)
	private String nome;

	@Email
	@NotBlank
	@Schema(example = "joao.ger@jwfood.com.br", required = true)
	private String email;

}