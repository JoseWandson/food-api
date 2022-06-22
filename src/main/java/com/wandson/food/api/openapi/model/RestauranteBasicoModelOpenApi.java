package com.wandson.food.api.openapi.model;

import java.math.BigDecimal;

import com.wandson.food.api.model.CozinhaModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(name = "RestauranteBasicoModel")
public class RestauranteBasicoModelOpenApi {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Thai Gourmet")
	private String nome;

	@Schema(example = "12.00")
	private BigDecimal taxaFrete;

	private CozinhaModel cozinha;

}