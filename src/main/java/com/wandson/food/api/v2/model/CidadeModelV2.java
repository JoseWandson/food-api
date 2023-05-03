package com.wandson.food.api.v2.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CidadeModel")
@Relation(collectionRelation = "cidades")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2> {

	@Schema(example = "1")
	@EqualsAndHashCode.Include
	private Long idCidade;

	@Schema(example = "João Pessoa")
	private String nomeCidade;
	private String idEstado;
	private String nomeEstado;

}
