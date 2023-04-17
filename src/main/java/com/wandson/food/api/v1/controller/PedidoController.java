package com.wandson.food.api.v1.controller;

import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wandson.food.api.v1.assembler.PedidoInputDisassembler;
import com.wandson.food.api.v1.assembler.PedidoModelAssembler;
import com.wandson.food.api.v1.assembler.PedidoResumoModelAssembler;
import com.wandson.food.api.v1.model.PedidoModel;
import com.wandson.food.api.v1.model.PedidoResumoModel;
import com.wandson.food.api.v1.model.input.PedidoInput;
import com.wandson.food.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.wandson.food.core.data.PageWrapper;
import com.wandson.food.core.data.PageableTranslator;
import com.wandson.food.domain.exception.EntidadeNaoEncontradaException;
import com.wandson.food.domain.exception.NegocioException;
import com.wandson.food.domain.filter.PedidoFilter;
import com.wandson.food.domain.model.Pedido;
import com.wandson.food.domain.model.Usuario;
import com.wandson.food.domain.repository.PedidoRepository;
import com.wandson.food.domain.service.EmissaoPedidoService;
import com.wandson.food.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(path = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;

	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;

	@Autowired
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

	@Override
	@GetMapping
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault Pageable pageable) {
		Pageable pageableTraduzido = traduzirPageable(pageable);

		Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);
		pedidosPage = new PageWrapper<>(pedidosPage, pageable);

		return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);
	}

	@Override
	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);

		return pedidoModelAssembler.toModel(pedido);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedido.emitir(novoPedido);

			return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of("codigo", "codigo", "subtotal", "subtotal", "taxaFrete", "taxaFrete", "valorTotal",
				"valorTotal", "dataCriacao", "dataCriacao", "restaurante.nome", "restaurante.nome", "restaurante.id",
				"restaurante.id", "cliente.id", "cliente.id", "cliente.nome", "cliente.nome");

		return PageableTranslator.translate(apiPageable, mapeamento);
	}

}