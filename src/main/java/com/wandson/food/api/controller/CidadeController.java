package com.wandson.food.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wandson.food.api.assembler.CidadeInputDisassembler;
import com.wandson.food.api.assembler.CidadeModelAssembler;
import com.wandson.food.api.model.CidadeModel;
import com.wandson.food.api.model.input.CidadeInput;
import com.wandson.food.domain.exception.EstadoNaoEncontradoException;
import com.wandson.food.domain.exception.NegocioException;
import com.wandson.food.domain.model.Cidade;
import com.wandson.food.domain.repository.CidadeRepository;
import com.wandson.food.domain.service.CadastroCidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "Cidades")
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;

	@GetMapping
	@ApiOperation("Lista as cidades")
	public List<CidadeModel> listar() {
		List<Cidade> todasCidades = cidadeRepository.findAll();
		return cidadeModelAssembler.toCollectionModel(todasCidades);
	}

	@GetMapping("/{cidadeId}")
	@ApiOperation("Busca uma cidade por ID")
	public CidadeModel buscar(@ApiParam("ID de uma cidade") @PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		return cidadeModelAssembler.toModel(cidade);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Cadastra uma cidade")
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			cidade = cadastroCidade.salvar(cidade);
			return cidadeModelAssembler.toModel(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	@ApiOperation("Atualiza uma cidade por ID")
	public CidadeModel atualizar(@ApiParam(value = "ID de uma cidade") @PathVariable Long cidadeId,
			@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

			cidadeAtual = cadastroCidade.salvar(cidadeAtual);
			return cidadeModelAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Exclui uma cidade por ID")
	public void remover(@ApiParam(value = "ID de uma cidade") @PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}

}
