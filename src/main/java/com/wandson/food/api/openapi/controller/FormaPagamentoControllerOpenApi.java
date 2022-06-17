package com.wandson.food.api.openapi.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.FormaPagamentoModel;
import com.wandson.food.api.model.input.FormaPagamentoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Api(tags = "Formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

	@ApiOperation("Lista as formas de pagamento")
	public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request);

	@ApiOperation("Busca uma forma de pagamento por ID")
	@ApiResponse(responseCode = "400", description = "ID da forma de pagamento inválido", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	public ResponseEntity<FormaPagamentoModel> buscar(
			@ApiParam(value = "ID de uma forma de pagamento") Long formaPagamentoId, ServletWebRequest request);

	@ApiOperation("Cadastra uma forma de pagamento")
	@ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada")
	public FormaPagamentoModel adicionar(FormaPagamentoInput formaPagamentoInput);

	@ApiOperation("Atualiza uma forma de pagamento por ID")
	@ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada")
	@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	public FormaPagamentoModel atualizar(@ApiParam(value = "ID de uma forma de pagamento") Long formaPagamentoId,
			FormaPagamentoInput formaPagamentoInput);

	@ApiOperation("Exclui uma forma de pagamento por ID")
	@ApiResponse(responseCode = "204", description = "Forma de pagamento excluída")
	@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	public void remover(@ApiParam(value = "ID de uma forma de pagamento") Long formaPagamentoId);
}