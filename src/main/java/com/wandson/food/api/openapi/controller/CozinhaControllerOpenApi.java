package com.wandson.food.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.CozinhaModel;
import com.wandson.food.api.model.input.CozinhaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

	@ApiOperation("Lista as cozinhas com paginação")
	public Page<CozinhaModel> listar(Pageable pageable);

	@ApiOperation("Busca uma cozinha por ID")
	@ApiResponse(responseCode = "400", description = "ID da cozinha inválido", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	public CozinhaModel buscar(@ApiParam("ID de uma cozinha") Long cozinhaId);

	@ApiOperation("Cadastra uma cozinha")
	@ApiResponse(responseCode = "201", description = "Cozinha cadastrada")
	public CozinhaModel adicionar(CozinhaInput cozinhaInput);

	@ApiOperation("Atualiza uma cozinha por ID")
	@ApiResponse(responseCode = "200", description = "Cozinha atualizada")
	@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	public CozinhaModel atualizar(@ApiParam("ID de uma cozinha") Long cozinhaId, CozinhaInput cozinhaInput);

	@ApiOperation("Exclui uma cozinha por ID")
	@ApiResponse(responseCode = "204", description = "Cozinha excluída")
	@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	public void remover(@ApiParam("ID de uma cozinha") Long cozinhaId);
}