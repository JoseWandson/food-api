package com.wandson.food.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.wandson.food.api.Links;
import com.wandson.food.api.controller.PedidoController;
import com.wandson.food.api.model.PedidoModel;
import com.wandson.food.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Links links;

	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

	@Override
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);

		pedidoModel.add(links.linkToPedidos());

		pedidoModel.getRestaurante().add(links.linkToRestaurante(pedido.getRestaurante().getId()));

		pedidoModel.getCliente().add(links.linkToUsuario(pedido.getCliente().getId()));

		pedidoModel.getFormaPagamento().add(links.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

		pedidoModel.getEnderecoEntrega().getCidade()
				.add(links.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

		pedidoModel.getItens().forEach(
				i -> i.add(links.linkToProduto(pedidoModel.getRestaurante().getId(), i.getProdutoId(), "produto")));

		return pedidoModel;
	}

}