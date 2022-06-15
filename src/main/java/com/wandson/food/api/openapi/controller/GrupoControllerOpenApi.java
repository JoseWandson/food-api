package com.wandson.food.api.openapi.controller;

import java.util.List;

import org.springframework.http.MediaType;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.GrupoModel;
import com.wandson.food.api.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	@ApiOperation("Lista os grupos")
	List<GrupoModel> listar();

	@ApiOperation("Busca um grupo por ID")
	@ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	GrupoModel buscar(@ApiParam("ID de um grupo") Long grupoId);

	@ApiOperation("Cadastra um grupo")
	@ApiResponse(responseCode = "201", description = "Grupo cadastrado")
	GrupoModel adicionar(GrupoInput grupoInput);

	@ApiOperation("Atualiza um grupo por ID")
	@ApiResponse(responseCode = "200", description = "Grupo atualizado")
	@ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	GrupoModel atualizar(@ApiParam("ID de um grupo") Long grupoId, GrupoInput grupoInput);

	@ApiOperation("Exclui um grupo por ID")
	@ApiResponse(responseCode = "204", description = "Grupo excluído")
	@ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	void remover(@ApiParam("ID de um grupo") Long grupoId);

}