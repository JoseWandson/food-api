package com.wandson.food.api.openapi.controller;

import java.util.List;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.GrupoModel;

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
	List<GrupoModel> listar(@Parameter(description = "ID do usuário", example = "1") Long usuarioId);

	@Operation(summary = "Desassociação de grupo com usuário")
	@ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso")
	@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	void desassociar(@Parameter(description = "ID do usuário", example = "1") Long usuarioId,
			@Parameter(description = "ID do grupo", example = "1") Long grupoId);

	@Operation(summary = "Associação de grupo com usuário")
	@ApiResponse(responseCode = "204", description = "Associação realizada com sucesso")
	@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	void associar(@Parameter(description = "ID do usuário", example = "1") Long usuarioId,
			@Parameter(description = "ID do grupo", example = "1") Long grupoId);
}