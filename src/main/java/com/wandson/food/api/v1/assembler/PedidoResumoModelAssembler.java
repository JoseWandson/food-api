package com.wandson.food.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.wandson.food.api.v1.Links;
import com.wandson.food.api.v1.controller.PedidoController;
import com.wandson.food.api.v1.model.PedidoResumoModel;
import com.wandson.food.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Links links;

	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

	@Override
	public PedidoResumoModel toModel(Pedido pedido) {
		PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoResumoModel);

		pedidoResumoModel.add(links.linkToPedidos("pedidos"));
		pedidoResumoModel.getRestaurante().add(links.linkToRestaurante(pedido.getRestaurante().getId()));
		pedidoResumoModel.getCliente().add(links.linkToUsuario(pedido.getCliente().getId()));

		return pedidoResumoModel;
	}

}