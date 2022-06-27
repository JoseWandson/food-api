package com.wandson.food.api.openapi.controller;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.FotoProdutoModel;
import com.wandson.food.api.model.input.FotoProdutoInput;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

	@Operation(summary = "Atualiza a foto do produto de um restaurante")
	@ApiResponse(responseCode = "200", description = "Foto do produto atualizada")
	@ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	FotoProdutoModel atualizarFoto(@Parameter(description = "ID do restaurante", example = "1") Long restauranteId,
			@Parameter(description = "ID do produto", example = "1") Long produtoId, FotoProdutoInput fotoProdutoInput)
			throws IOException;

	@Operation(summary = "Exclui a foto do produto de um restaurante")
	@ApiResponse(responseCode = "204", description = "Foto do produto excluída")
	@ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
	void excluir(@Parameter(description = "ID do restaurante", example = "1") Long restauranteId,
			@Parameter(description = "ID do produto", example = "1") Long produtoId);

	@Operation(summary = "Busca a foto do produto de um restaurante")
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json"),
			@Content(mediaType = "image/jpeg"), @Content(mediaType = "image/png") })
	@ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
	FotoProdutoModel buscar(@Parameter(description = "ID do restaurante", example = "1") Long restauranteId,
			@Parameter(description = "ID do produto", example = "1") Long produtoId);

	@Hidden
	ResponseEntity<InputStreamResource> servirFoto(Long restauranteId, Long produtoId, String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;
}