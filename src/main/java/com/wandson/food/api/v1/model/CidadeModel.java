package com.wandson.food.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "cidades")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CidadeModel extends RepresentationModel<CidadeModel> {

	@Schema(example = "1")
	@EqualsAndHashCode.Include
	private Long id;

	@Schema(example = "João Pessoa")
	private String nome;
	private EstadoModel estado;

}
