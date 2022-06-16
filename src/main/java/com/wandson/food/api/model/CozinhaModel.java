package com.wandson.food.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wandson.food.api.model.view.RestauranteView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaModel {

	@ApiModelProperty(example = "1")
	@JsonView(RestauranteView.Resumo.class)
	private Long id;

	@JsonView(RestauranteView.Resumo.class)
	@ApiModelProperty(example = "Brasileira")
	private String nome;

}
