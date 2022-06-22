package com.wandson.food.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {

	@NotBlank
	@Schema(example = "João Pessoa", required = true)
	private String nome;

	@Valid
	@NotNull
	private EstadoIdInput estado;

}