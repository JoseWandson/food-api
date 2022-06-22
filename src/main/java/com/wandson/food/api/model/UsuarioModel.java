package com.wandson.food.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "João da Silva")
	private String nome;

	@Schema(example = "joao.ger@jwfood.com.br")
	private String email;

}
