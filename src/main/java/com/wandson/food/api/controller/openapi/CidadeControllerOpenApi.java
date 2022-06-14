package com.wandson.food.api.controller.openapi;

import java.util.List;

import org.springframework.http.MediaType;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.CidadeModel;
import com.wandson.food.api.model.input.CidadeInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation("Lista as cidades")
	List<CidadeModel> listar();

	@ApiOperation("Busca uma cidade por ID")
	@ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	CidadeModel buscar(@ApiParam("ID de uma cidade") Long cidadeId);

	@ApiOperation("Cadastra uma cidade")
	@ApiResponse(responseCode = "201", description = "Cidade cadastrada")
	CidadeModel adicionar(CidadeInput cidadeInput);

	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponse(responseCode = "200", description = "Cidade atualizada")
	@ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	CidadeModel atualizar(@ApiParam(value = "ID de uma cidade") Long cidadeId, CidadeInput cidadeInput);

	@ApiOperation("Exclui uma cidade por ID")
	@ApiResponse(responseCode = "204", description = "Cidade excluída")
	@ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	void remover(@ApiParam(value = "ID de uma cidade") Long cidadeId);

}
