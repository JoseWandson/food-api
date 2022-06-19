package com.wandson.food.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.PedidoModel;
import com.wandson.food.api.model.PedidoResumoModel;
import com.wandson.food.api.model.input.PedidoInput;
import com.wandson.food.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiOperation("Pesquisa os pedidos")
	@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula", name = "campos", paramType = "query", type = "string", dataTypeClass = String.class)
	Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

	@ApiOperation("Registra um pedido")
	@ApiResponse(responseCode = "201", description = "Pedido registrado")
	public PedidoModel adicionar(PedidoInput pedidoInput);

	@ApiOperation("Busca um pedido por código")
	@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula", name = "campos", paramType = "query", type = "string", dataTypeClass = String.class)
	@ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	PedidoModel buscar(@ApiParam(value = "Código de um pedido") String codigoPedido);
}