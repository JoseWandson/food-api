package com.wandson.food.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "estados")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class EstadoModel extends RepresentationModel<EstadoModel> {

	@Schema(example = "1")
	@EqualsAndHashCode.Include
	private Long id;

	@Schema(example = "Paraíba")
	private String nome;
}
