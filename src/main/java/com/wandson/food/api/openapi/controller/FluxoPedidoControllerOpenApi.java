package com.wandson.food.api.openapi.controller;

import com.wandson.food.api.exceptionhandler.Problem;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

	@Operation(summary = "Confirmação de pedido")
	@ApiResponse(responseCode = "204", description = "Pedido confirmado com sucesso")
	@ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	void confirmar(
			@Parameter(description = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55") String codigoPedido);

	@Operation(summary = "Cancelamento de pedido")
	@ApiResponse(responseCode = "204", description = "Pedido cancelado com sucesso")
	@ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	void cancelar(
			@Parameter(description = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55") String codigoPedido);

	@Operation(summary = "Registrar entrega de pedido")
	@ApiResponse(responseCode = "204", description = "Entrega de pedido registrada com sucesso")
	@ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	void entregar(
			@Parameter(description = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55") String codigoPedido);
}