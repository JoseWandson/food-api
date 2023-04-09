package com.wandson.food.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.v1.model.GrupoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuários")
public interface UsuarioGrupoControllerOpenApi {

	@Operation(summary = "Lista os grupos associados a um usuário")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	CollectionModel<GrupoModel> listar(@Parameter(description = "ID do usuário", example = "1") Long usuarioId);

	@Operation(summary = "Desassociação de grupo com usuário")
	@ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso")
	@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ResponseEntity<Void> desassociar(@Parameter(description = "ID do usuário", example = "1") Long usuarioId,
			@Parameter(description = "ID do grupo", example = "1") Long grupoId);

	@Operation(summary = "Associação de grupo com usuário")
	@ApiResponse(responseCode = "204", description = "Associação realizada com sucesso")
	@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ResponseEntity<Void> associar(@Parameter(description = "ID do usuário", example = "1") Long usuarioId,
			@Parameter(description = "ID do grupo", example = "1") Long grupoId);
}