package com.wandson.food.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.wandson.food.api.model.view.RestauranteView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {

	@Schema(example = "1")
	@JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
	private Long id;

	@Schema(example = "Thai Gourmet")
	@JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
	private String nome;

	@Schema(example = "12.00")
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;

	@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;
	private Boolean ativo;
	private Boolean aberto;
	private EnderecoModel endereco;

}
