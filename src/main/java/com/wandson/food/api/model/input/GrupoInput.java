package com.wandson.food.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {

	@NotBlank
	@Schema(example = "Gerente", required = true)
	private String nome;

}
