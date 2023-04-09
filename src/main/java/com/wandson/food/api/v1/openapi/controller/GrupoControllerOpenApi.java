package com.wandson.food.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.v1.model.GrupoModel;
import com.wandson.food.api.v1.model.input.GrupoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Grupos", description = "Gerencia os grupos de usuários")
public interface GrupoControllerOpenApi {

	@Operation(summary = "Lista os grupos")
	CollectionModel<GrupoModel> listar();

	@Operation(summary = "Busca um grupo por ID")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	GrupoModel buscar(@Parameter(description = "ID de um grupo", example = "1") Long grupoId);

	@Operation(summary = "Cadastra um grupo")
	@ApiResponse(responseCode = "201", description = "Grupo cadastrado")
	GrupoModel adicionar(@RequestBody(description = "Representação de um novo grupo") GrupoInput grupoInput);

	@Operation(summary = "Atualiza um grupo por ID")
	@ApiResponse(responseCode = "200", description = "Grupo atualizado")
	@ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	GrupoModel atualizar(@Parameter(description = "ID de um grupo", example = "1") Long grupoId,
			@RequestBody(description = "Representação de um grupo com os novos dados") GrupoInput grupoInput);

	@Operation(summary = "Exclui um grupo por ID")
	@ApiResponse(responseCode = "204", description = "Grupo excluído")
	@ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	void remover(@Parameter(description = "ID de um grupo", example = "1") Long grupoId);

}