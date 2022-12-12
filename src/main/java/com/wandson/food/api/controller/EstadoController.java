package com.wandson.food.api.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wandson.food.api.assembler.EstadoInputDisassembler;
import com.wandson.food.api.assembler.EstadoModelAssembler;
import com.wandson.food.api.model.EstadoModel;
import com.wandson.food.api.model.input.EstadoInput;
import com.wandson.food.api.openapi.controller.EstadoControllerOpenApi;
import com.wandson.food.domain.model.Estado;
import com.wandson.food.domain.repository.EstadoRepository;
import com.wandson.food.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;

	@Override
	@GetMapping
	public CollectionModel<EstadoModel> listar() {
		List<Estado> todosEstados = estadoRepository.findAll();

		return estadoModelAssembler.toCollectionModel(todosEstados);
	}

	@Override
	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);

		return estadoModelAssembler.toModel(estado);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		estado = cadastroEstado.salvar(estado);
		return estadoModelAssembler.toModel(estado);
	}

	@Override
	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);

		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

		estadoAtual = cadastroEstado.salvar(estadoAtual);
		return estadoModelAssembler.toModel(estadoAtual);
	}

	@Override
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		cadastroEstado.excluir(estadoId);
	}

}
