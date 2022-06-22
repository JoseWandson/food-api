package com.wandson.food.api.openapi.controller;

import java.util.List;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.CidadeModel;
import com.wandson.food.api.model.input.CidadeInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cidades", description = "Gerencia as cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Lista as cidades")
	List<CidadeModel> listar();

	@Operation(summary = "Busca uma cidade por ID")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
	CidadeModel buscar(@Parameter(description = "ID de uma cidade", example = "1") Long cidadeId);

	@Operation(summary = "Cadastra uma cidade")
	@ApiResponse(responseCode = "201", description = "Cidade cadastrada")
	CidadeModel adicionar(@RequestBody(description = "Representação de uma nova cidade") CidadeInput cidadeInput);

	@Operation(summary = "Atualiza uma cidade por ID")
	@ApiResponse(responseCode = "200", description = "Cidade atualizada")
	@ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
	CidadeModel atualizar(@Parameter(description = "ID de uma cidade", example = "1") Long cidadeId,
			@RequestBody(description = "Representação de uma cidade com os novos dados") CidadeInput cidadeInput);

	@Operation(summary = "Exclui uma cidade por ID")
	@ApiResponse(responseCode = "204", description = "Cidade excluída")
	@ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
	void remover(@Parameter(description = "ID de uma cidade", example = "1") Long cidadeId);

}
