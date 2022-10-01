package com.wandson.food.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonView;
import com.wandson.food.api.model.view.RestauranteView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "cozinhas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CozinhaModel extends RepresentationModel<CozinhaModel> {

	@Schema(example = "1")
	@EqualsAndHashCode.Include
	@JsonView(RestauranteView.Resumo.class)
	private Long id;

	@JsonView(RestauranteView.Resumo.class)
	@Schema(example = "Brasileira")
	private String nome;

}
