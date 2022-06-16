package com.wandson.food.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInput {

	@NotBlank
	@ApiModelProperty(example = "Brasileira", required = true)
	private String nome;

}
