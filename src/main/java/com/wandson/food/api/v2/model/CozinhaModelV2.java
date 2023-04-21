package com.wandson.food.api.v2.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "cozinhas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CozinhaModelV2 extends RepresentationModel<CozinhaModelV2> {

	@Schema(example = "1")
	@EqualsAndHashCode.Include
	private Long idCozinha;

	@Schema(example = "Brasileira")
	private String nomeCozinha;

}
