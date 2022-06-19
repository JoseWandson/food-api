package com.wandson.food.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoModel {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "João Pessoa")
	private String nome;

	@ApiModelProperty(example = "Paraíba")
	private String estado;

}
