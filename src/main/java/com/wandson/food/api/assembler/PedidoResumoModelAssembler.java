package com.wandson.food.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wandson.food.api.model.PedidoResumoModel;
import com.wandson.food.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public List<PedidoResumoModel> toCollectionModel(List<Pedido> pedidos) {
		return pedidos.stream().map(this::toModel).collect(Collectors.toList());
	}

	private PedidoResumoModel toModel(Pedido pedido) {
		return modelMapper.map(pedido, PedidoResumoModel.class);
	}

}