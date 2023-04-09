package com.wandson.food.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "pedidos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel> {

	@EqualsAndHashCode.Include
	@Schema(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
	private String codigo;

	@Schema(example = "298.90")
	private BigDecimal subtotal;

	@Schema(example = "10.00")
	private BigDecimal taxaFrete;

	@Schema(example = "308.90")
	private BigDecimal valorTotal;

	@Schema(example = "CRIADO")
	private String status;

	@Schema(example = "2019-12-01T20:34:04Z")
	private OffsetDateTime dataCriacao;
	private RestauranteApenasNomeModel restaurante;
	private UsuarioModel cliente;

}