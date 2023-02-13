package com.wandson.food.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "permissoes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class PermissaoModel extends RepresentationModel<PermissaoModel> {

	@Schema(example = "1")
	@EqualsAndHashCode.Include
	private Long id;

	@Schema(example = "CONSULTAR_COZINHAS")
	private String nome;

	@Schema(example = "Permite consultar cozinhas")
	private String descricao;

}