package com.wandson.food.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.v1.model.FormaPagamentoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

	@Operation(summary = "Lista as formas de pagamento associadas a restaurante")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	CollectionModel<FormaPagamentoModel> listar(
			@Parameter(description = "ID do restaurante", example = "1") Long restauranteId);

	@Operation(summary = "Desassociação de restaurante com forma de pagamento")
	@ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso")
	@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ResponseEntity<Void> desassociar(@Parameter(description = "ID do restaurante", example = "1") Long restauranteId,
			@Parameter(description = "ID da forma de pagamento", example = "1") Long formaPagamentoId);

	@Operation(summary = "Associação de restaurante com forma de pagamento")
	@ApiResponse(responseCode = "204", description = "Associação realizada com sucesso")
	@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ResponseEntity<Void> associar(@Parameter(description = "ID do restaurante", example = "1") Long restauranteId,
			@Parameter(description = "ID da forma de pagamento", example = "1") Long formaPagamentoId);
}