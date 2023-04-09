package com.wandson.food.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.v1.model.UsuarioModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

	@Operation(summary = "Lista os usuários responsáveis associados a restaurante")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	CollectionModel<UsuarioModel> listar(
			@Parameter(description = "ID do restaurante", example = "1") Long restauranteId);

	@Operation(summary = "Desassociação de restaurante com usuário responsável")
	@ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso")
	@ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ResponseEntity<Void> desassociar(@Parameter(description = "ID do restaurante", example = "1") Long restauranteId,
			@Parameter(description = "ID do usuário", example = "1") Long usuarioId);

	@Operation(summary = "Associação de restaurante com usuário responsável")
	@ApiResponse(responseCode = "204", description = "Associação realizada com sucesso")
	@ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ResponseEntity<Void> associar(@Parameter(description = "ID do restaurante", example = "1") Long restauranteId,
			@Parameter(description = "ID do usuário", example = "1") Long usuarioId);
}