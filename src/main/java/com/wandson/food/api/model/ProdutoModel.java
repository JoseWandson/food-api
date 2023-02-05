package com.wandson.food.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "produtos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ProdutoModel extends RepresentationModel<ProdutoModel> {

	@Schema(example = "1")
	@EqualsAndHashCode.Include
	private Long id;

	@Schema(example = "Espetinho de Cupim")
	private String nome;

	@Schema(example = "Acompanha farinha, mandioca e vinagrete")
	private String descricao;

	@Schema(example = "12.50")
	private BigDecimal preco;

	@Schema(example = "true")
	private Boolean ativo;

}