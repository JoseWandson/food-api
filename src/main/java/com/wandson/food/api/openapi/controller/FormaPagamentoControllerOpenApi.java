package com.wandson.food.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.FormaPagamentoModel;
import com.wandson.food.api.model.input.FormaPagamentoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Formas de pagamento", description = "Gerencia as formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

	@Operation(summary = "Lista as formas de pagamento")
	public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request);

	@Operation(summary = "Busca uma forma de pagamento por ID")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "400", description = "ID da forma de pagamento inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
	public ResponseEntity<FormaPagamentoModel> buscar(
			@Parameter(description = "ID de uma forma de pagamento", example = "1") Long formaPagamentoId,
			ServletWebRequest request);

	@Operation(summary = "Cadastra uma forma de pagamento")
	@ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada")
	public FormaPagamentoModel adicionar(
			@RequestBody(description = "Representação de uma nova forma de pagamento") FormaPagamentoInput formaPagamentoInput);

	@Operation(summary = "Atualiza uma forma de pagamento por ID")
	@ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada")
	@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
	public FormaPagamentoModel atualizar(
			@Parameter(description = "ID de uma forma de pagamento", example = "1") Long formaPagamentoId,
			@RequestBody(description = "Representação de uma forma de pagamento com os novos dados") FormaPagamentoInput formaPagamentoInput);

	@Operation(summary = "Exclui uma forma de pagamento por ID")
	@ApiResponse(responseCode = "204", description = "Forma de pagamento excluída")
	@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
	public void remover(@Parameter(description = "ID de uma forma de pagamento", example = "1") Long formaPagamentoId);
}