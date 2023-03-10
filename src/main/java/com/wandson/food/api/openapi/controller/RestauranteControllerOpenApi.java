package com.wandson.food.api.openapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.model.RestauranteApenasNomeModel;
import com.wandson.food.api.model.RestauranteBasicoModel;
import com.wandson.food.api.model.RestauranteModel;
import com.wandson.food.api.model.input.RestauranteInput;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Restaurantes", description = "Gerencia os restaurantes")
public interface RestauranteControllerOpenApi {

	@Operation(summary = "Lista restaurantes", ignoreJsonView = true)
	@Parameter(description = "Nome da projeção de pedidos", name = "projecao", in = ParameterIn.QUERY, schema = @Schema(type = "string", allowableValues = "apenas-nome"))
	CollectionModel<RestauranteBasicoModel> listar();

	@Hidden
	CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();

	@Operation(summary = "Busca um restaurante por ID")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(schema = @Schema(implementation = Problem.class)))
	@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	RestauranteModel buscar(@Parameter(description = "ID de um restaurante", example = "1") Long restauranteId);

	@Operation(summary = "Cadastra um restaurante")
	@ApiResponse(responseCode = "201", description = "Restaurante cadastrado")
	RestauranteModel adicionar(
			@RequestBody(description = "Representação de um novo restaurante") RestauranteInput restauranteInput);

	@Operation(summary = "Atualiza um restaurante por ID")
	@ApiResponse(responseCode = "200", description = "Restaurante atualizado")
	@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	RestauranteModel atualizar(@Parameter(description = "ID de um restaurante", example = "1") Long restauranteId,
			@RequestBody(description = "Representação de um restaurante com os novos dados") RestauranteInput restauranteInput);

	@Operation(summary = "Ativa um restaurante por ID")
	@ApiResponse(responseCode = "204", description = "Restaurante ativado com sucesso")
	@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ResponseEntity<Void> ativar(@Parameter(description = "ID de um restaurante", example = "1") Long restauranteId);

	@Operation(summary = "Inativa um restaurante por ID")
	@ApiResponse(responseCode = "204", description = "Restaurante inativado com sucesso")
	@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ResponseEntity<Void> inativar(@Parameter(description = "ID de um restaurante", example = "1") Long restauranteId);

	@Operation(summary = "Ativa múltiplos restaurantes")
	@ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso")
	void ativarMultiplos(@RequestBody(description = "IDs de restaurantes") List<Long> restauranteIds);

	@Operation(summary = "Inativa múltiplos restaurantes")
	@ApiResponse(responseCode = "204", description = "Restaurantes inativados com sucesso")
	void inativarMultiplos(@RequestBody(description = "IDs de restaurantes") List<Long> restauranteIds);

	@Operation(summary = "Abre um restaurante por ID")
	@ApiResponse(responseCode = "204", description = "Restaurante aberto com sucesso")
	@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ResponseEntity<Void> abrir(@Parameter(description = "ID de um restaurante", example = "1") Long restauranteId);

	@Operation(summary = "Fecha um restaurante por ID")
	@ApiResponse(responseCode = "204", description = "Restaurante fechado com sucesso")
	@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
	ResponseEntity<Void> fechar(@Parameter(description = "ID de um restaurante", example = "1") Long restauranteId);

	@Operation(summary = "Atualiza parcialmente um restaurante por ID")
	RestauranteModel atualizarParcial(
			@Parameter(description = "ID de um restaurante", example = "1") Long restauranteId,
			@RequestBody(description = "Representação parcial de um restaurante com os novos dados", content = @Content(schemaProperties = @SchemaProperty(name = "taxaFrete", schema = @Schema(example = "5.5", type = "object")))) Map<String, Object> campos,
			HttpServletRequest request);

}