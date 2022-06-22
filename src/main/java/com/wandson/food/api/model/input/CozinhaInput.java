package com.wandson.food.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInput {

	@NotBlank
	@Schema(example = "Brasileira", required = true)
	private String nome;

}
