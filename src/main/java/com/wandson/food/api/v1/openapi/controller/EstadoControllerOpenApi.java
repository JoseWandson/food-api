package com.wandson.food.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.v1.model.EstadoModel;
import com.wandson.food.api.v1.model.input.EstadoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estados", description = "Gerencia os estados")
public interface EstadoControllerOpenApi {

	@Operation(summary = "Lista os estados")
	CollectionModel<EstadoModel> listar();

	@Operation(summary = "Busca um estado por ID")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "400", description = "ID do estado inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	EstadoModel buscar(@Parameter(description = "ID de um estado", example = "1") Long estadoId);

	@Operation(summary = "Cadastra um estado")
	@ApiResponse(responseCode = "201", description = "Estado cadastrado")
	EstadoModel adicionar(@RequestBody(description = "Representação de um novo estado") EstadoInput estadoInput);

	@Operation(summary = "Atualiza um estado por ID")
	@ApiResponse(responseCode = "200", description = "Estado atualizado")
	@ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	EstadoModel atualizar(@Parameter(description = "ID de um estado", example = "1") Long estadoId,
			@RequestBody(description = "Representação de um estado com os novos dados") EstadoInput estadoInput);

	@Operation(summary = "Exclui um estado por ID")
	@ApiResponse(responseCode = "204", description = "Estado excluído")
	@ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	void remover(@Parameter(description = "ID de um estado", example = "1") Long estadoId);
}