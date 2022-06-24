package com.wandson.food.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.PedidoModel;
import com.wandson.food.api.model.PedidoResumoModel;
import com.wandson.food.api.model.input.PedidoInput;
import com.wandson.food.api.openapi.model.PedidosResumoModelOpenApi;
import com.wandson.food.domain.filter.PedidoFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedidos", description = "Gerencia os pedidos")
public interface PedidoControllerOpenApi {

	@Operation(summary = "Pesquisa os pedidos")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PedidosResumoModelOpenApi.class)))
	@Parameter(description = "Nomes das propriedades para filtrar na resposta, separados por vírgula", name = "campos", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
	Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

	@Operation(summary = "Registra um pedido")
	@ApiResponse(responseCode = "201", description = "Pedido registrado")
	PedidoModel adicionar(@RequestBody(description = "Representação de um novo pedido") PedidoInput pedidoInput);

	@Operation(summary = "Busca um pedido por código")
	@Parameter(description = "Nomes das propriedades para filtrar na resposta, separados por vírgula", name = "campos", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	PedidoModel buscar(
			@Parameter(description = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55") String codigoPedido);
}