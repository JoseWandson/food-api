package com.wandson.food.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.wandson.food.api.model.PermissaoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Permissões", description = "Gerencia as permissões")
public interface PermissaoControllerOpenApi {

	@Operation(summary = "Lista as permissões")
	CollectionModel<PermissaoModel> listar();

}