package com.wandson.food.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.CozinhaModel;
import com.wandson.food.api.model.input.CozinhaInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cozinhas", description = "Gerencia as cozinhas")
public interface CozinhaControllerOpenApi {

	@Operation(summary = "Lista as cozinhas com paginação")
	@ApiResponse(responseCode = "200")
	PagedModel<CozinhaModel> listar(Pageable pageable);

	@Operation(summary = "Busca uma cozinha por ID")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "400", description = "ID da cozinha inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
	CozinhaModel buscar(@Parameter(description = "ID de uma cozinha", example = "1") Long cozinhaId);

	@Operation(summary = "Cadastra uma cozinha")
	@ApiResponse(responseCode = "201", description = "Cozinha cadastrada")
	CozinhaModel adicionar(@RequestBody(description = "Representação de uma nova cozinha") CozinhaInput cozinhaInput);

	@Operation(summary = "Atualiza uma cozinha por ID")
	@ApiResponse(responseCode = "200", description = "Cozinha atualizada")
	@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
	CozinhaModel atualizar(@Parameter(description = "ID de uma cozinha", example = "1") Long cozinhaId,
			@RequestBody(description = "Representação de uma cozinha com os novos dados") CozinhaInput cozinhaInput);

	@Operation(summary = "Exclui uma cozinha por ID")
	@ApiResponse(responseCode = "204", description = "Cozinha excluída")
	@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
	void remover(@Parameter(description = "ID de uma cozinha", example = "1") Long cozinhaId);
}