package com.wandson.food.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wandson.food.api.model.input.PedidoInput;
import com.wandson.food.domain.model.Pedido;

@Component
public class PedidoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Pedido toDomainObject(PedidoInput pedidoInput) {
		return modelMapper.map(pedidoInput, Pedido.class);
	}

}