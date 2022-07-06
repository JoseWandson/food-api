package com.wandson.food.api.openapi.controller;

import java.util.List;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.UsuarioModel;
import com.wandson.food.api.model.input.SenhaInput;
import com.wandson.food.api.model.input.UsuarioComSenhaInput;
import com.wandson.food.api.model.input.UsuarioInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuários", description = "Gerencia os usuários")
public interface UsuarioControllerOpenApi {

	@Operation(summary = "Lista os usuários")
	List<UsuarioModel> listar();

	@Operation(summary = "Busca um usuário por ID")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "400", description = "ID do usuário inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	UsuarioModel buscar(@Parameter(description = "ID do usuário", example = "1") Long usuarioId);

	@Operation(summary = "Cadastra um usuário")
	@ApiResponse(responseCode = "201", description = "Usuário cadastrado")
	UsuarioModel adicionar(
			@RequestBody(description = "Representação de um novo usuário") UsuarioComSenhaInput usuarioInput);

	@Operation(summary = "Atualiza um usuário por ID")
	@ApiResponse(responseCode = "200", description = "Usuário atualizado")
	@ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	UsuarioModel atualizar(@Parameter(description = "ID do usuário", example = "1") Long usuarioId,
			@RequestBody(description = "Representação de um usuário com os novos dados") UsuarioInput usuarioInput);

	@Operation(summary = "Atualiza a senha de um usuário")
	@ApiResponse(responseCode = "204", description = "Senha alterada com sucesso")
	@ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	void alterarSenha(@Parameter(description = "ID do usuário", example = "1") Long usuarioId,
			@RequestBody(description = "Representação de uma nova senha") SenhaInput senha);
}