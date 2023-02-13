package com.wandson.food.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wandson.food.api.Links;
import com.wandson.food.api.assembler.PermissaoModelAssembler;
import com.wandson.food.api.model.PermissaoModel;
import com.wandson.food.api.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.wandson.food.domain.model.Grupo;
import com.wandson.food.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;

	@Autowired
	private Links links;

	@Override
	@GetMapping
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

		CollectionModel<PermissaoModel> permissoesModel = permissaoModelAssembler
				.toCollectionModel(grupo.getPermissoes()).removeLinks().add(links.linkToGrupoPermissoes(grupoId))
				.add(links.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

		permissoesModel.getContent().forEach(permissaoModel -> permissaoModel
				.add(links.linkToGrupoPermissaoDesassociacao(grupoId, permissaoModel.getId(), "desassociar")));

		return permissoesModel;
	}

	@Override
	@DeleteMapping("/{permissaoId}")
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.desassociarPermissao(grupoId, permissaoId);

		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/{permissaoId}")
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.associarPermissao(grupoId, permissaoId);

		return ResponseEntity.noContent().build();
	}

}