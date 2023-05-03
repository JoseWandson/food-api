package com.wandson.food.api.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.wandson.food.api.v2.assembler.CozinhaInputDisassemblerV2;
import com.wandson.food.api.v2.assembler.CozinhaModelAssemblerV2;
import com.wandson.food.api.v2.model.CozinhaModelV2;
import com.wandson.food.api.v2.model.input.CozinhaInputV2;
import com.wandson.food.api.v2.openapi.controller.CozinhaControllerV2OpenApi;
import com.wandson.food.domain.model.Cozinha;
import com.wandson.food.domain.repository.CozinhaRepository;
import com.wandson.food.domain.service.CadastroCozinhaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v2/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaControllerV2 implements CozinhaControllerV2OpenApi {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaModelAssemblerV2 cozinhaModelAssembler;

	@Autowired
	private CozinhaInputDisassemblerV2 cozinhaInputDisassembler;

	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

	@Override
	@GetMapping
	public PagedModel<CozinhaModelV2> listar(@PageableDefault Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

		return pagedResourcesAssembler.toModel(cozinhasPage, cozinhaModelAssembler);
	}

	@Override
	@GetMapping("/{cozinhaId}")
	public CozinhaModelV2 buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
		return cozinhaModelAssembler.toModel(cozinha);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModelV2 adicionar(@RequestBody @Valid CozinhaInputV2 cozinhaInput) {
		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		cozinha = cadastroCozinha.salvar(cozinha);
		return cozinhaModelAssembler.toModel(cozinha);
	}

	@Override
	@PutMapping("/{cozinhaId}")
	public CozinhaModelV2 atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInputV2 cozinhaInput) {
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);

		cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);

		cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
		return cozinhaModelAssembler.toModel(cozinhaAtual);
	}

	@Override
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinha.excluir(cozinhaId);
	}

}
