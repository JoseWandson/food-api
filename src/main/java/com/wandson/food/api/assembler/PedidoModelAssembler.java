package com.wandson.food.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.wandson.food.api.controller.CidadeController;
import com.wandson.food.api.controller.FormaPagamentoController;
import com.wandson.food.api.controller.PedidoController;
import com.wandson.food.api.controller.RestauranteController;
import com.wandson.food.api.controller.RestauranteProdutoController;
import com.wandson.food.api.controller.UsuarioController;
import com.wandson.food.api.model.PedidoModel;
import com.wandson.food.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	@Autowired
	private ModelMapper modelMapper;

	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

	@Override
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);

		pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));
		pedidoModel.getRestaurante().add(
				linkTo(methodOn(RestauranteController.class).buscar(pedido.getRestaurante().getId())).withSelfRel());
		pedidoModel.getCliente()
				.add(linkTo(methodOn(UsuarioController.class).buscar(pedido.getCliente().getId())).withSelfRel());
		pedidoModel.getFormaPagamento()
				.add(linkTo(methodOn(FormaPagamentoController.class).buscar(pedido.getFormaPagamento().getId(), null))
						.withSelfRel());
		pedidoModel.getEnderecoEntrega().getCidade()
				.add(linkTo(methodOn(CidadeController.class).buscar(pedido.getEnderecoEntrega().getCidade().getId()))
						.withSelfRel());
		pedidoModel.getItens().forEach(i -> i.add(linkTo(
				methodOn(RestauranteProdutoController.class).buscar(pedido.getRestaurante().getId(), i.getProdutoId()))
						.withRel("produto")));

		return pedidoModel;
	}

}