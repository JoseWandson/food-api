package com.wandson.food.api.openapi.controller;

import java.util.List;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.ProdutoModel;
import com.wandson.food.api.model.input.ProdutoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produtos", description = "Gerencia os produtos de restaurantes")
public interface RestauranteProdutoControllerOpenApi {

	@Operation(summary = "Lista os produtos de um restaurante")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	List<ProdutoModel> listar(@Parameter(description = "ID do restaurante", example = "1") Long restauranteId,
			@Parameter(description = "Indica se deve ou não incluir produtos inativos no resultado da listagem", schema = @Schema(defaultValue = "false", type = "boolean")) boolean incluirInativos);

	@Operation(summary = "Busca um produto de um restaurante")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ProdutoModel buscar(@Parameter(description = "ID do restaurante", example = "1") Long restauranteId,
			@Parameter(description = "ID do produto", example = "1") Long produtoId);

	@Operation(summary = "Cadastra um produto de um restaurante")
	@ApiResponse(responseCode = "201", description = "Produto cadastrado")
	@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ProdutoModel adicionar(@Parameter(description = "ID do restaurante", example = "1") Long restauranteId,
			@RequestBody(description = "Representação de um novo produto") ProdutoInput produtoInput);

	@Operation(summary = "Atualiza um produto de um restaurante")
	@ApiResponse(responseCode = "200", description = "Produto atualizado")
	@ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ProdutoModel atualizar(@Parameter(description = "ID do restaurante", example = "1") Long restauranteId,
			@Parameter(description = "ID do produto", example = "1") Long produtoId,
			@RequestBody(description = "Representação de um produto com os novos dados") ProdutoInput produtoInput);
}