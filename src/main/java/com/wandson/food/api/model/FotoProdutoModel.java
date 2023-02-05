package com.wandson.food.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "fotos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class FotoProdutoModel extends RepresentationModel<FotoProdutoModel> {

	@EqualsAndHashCode.Include
	@Schema(example = "b8bbd21a-4dd3-4954-835c-3493af2ba6a0_Prime-Rib.jpg")
	private String nomeArquivo;

	@Schema(example = "Prime Rib ao ponto")
	private String descricao;

	@Schema(example = "image/jpeg")
	private String contentType;

	@Schema(example = "202912")
	private Long tamanho;

}
