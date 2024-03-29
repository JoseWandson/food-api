package com.wandson.food.api.v1.controller;

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

import com.wandson.food.api.v1.Links;
import com.wandson.food.api.v1.assembler.GrupoModelAssembler;
import com.wandson.food.api.v1.model.GrupoModel;
import com.wandson.food.api.v1.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.wandson.food.domain.model.Usuario;
import com.wandson.food.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@Autowired
	private Links links;

	@Override
	@GetMapping
	public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

		CollectionModel<GrupoModel> grupos = grupoModelAssembler.toCollectionModel(usuario.getGrupos()).removeLinks()
				.add(links.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));

		grupos.getContent().forEach(
				grupo -> grupo.add(links.linkToUsuarioGrupoDesassociacao(usuarioId, grupo.getId(), "desassociar")));

		return grupos;
	}

	@Override
	@DeleteMapping("/{grupoId}")
	public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.desassociarGrupo(usuarioId, grupoId);

		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/{grupoId}")
	public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.associarGrupo(usuarioId, grupoId);

		return ResponseEntity.noContent().build();
	}

}