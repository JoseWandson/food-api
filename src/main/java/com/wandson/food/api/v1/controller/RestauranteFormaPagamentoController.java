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
import com.wandson.food.api.v1.assembler.FormaPagamentoModelAssembler;
import com.wandson.food.api.v1.model.FormaPagamentoModel;
import com.wandson.food.api.v1.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.wandson.food.domain.model.Restaurante;
import com.wandson.food.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

	@Autowired
	private Links links;

	@Override
	@GetMapping
	public CollectionModel<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		CollectionModel<FormaPagamentoModel> formasPagamentoModel = formaPagamentoModelAssembler
				.toCollectionModel(restaurante.getFormasPagamento()).removeLinks()
				.add(links.linkToRestauranteFormasPagamento(restauranteId))
				.add(links.linkToRestauranteFormaPagamentoAssociacao(restauranteId, "associar"));

		formasPagamentoModel.getContent()
				.forEach(formaPagamentoModel -> formaPagamentoModel
						.add(links.linkToRestauranteFormaPagamentoDesassociacao(restauranteId,
								formaPagamentoModel.getId(), "desassociar")));

		return formasPagamentoModel;
	}

	@Override
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);

		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);

		return ResponseEntity.noContent().build();
	}

}
