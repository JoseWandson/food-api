package com.wandson.food.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wandson.food.domain.exception.NegocioException;
import com.wandson.food.domain.model.Pedido;
import com.wandson.food.domain.model.StatusPedido;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);

		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throwNegocioException(pedido, StatusPedido.CONFIRMADO);
		}

		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());
	}

	@Transactional
	public void cancelar(Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);

		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throwNegocioException(pedido, StatusPedido.CANCELADO);
		}

		pedido.setStatus(StatusPedido.CANCELADO);
		pedido.setDataCancelamento(OffsetDateTime.now());
	}

	@Transactional
	public void entregar(Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);

		if (!pedido.getStatus().equals(StatusPedido.CONFIRMADO)) {
			throwNegocioException(pedido, StatusPedido.ENTREGUE);
		}

		pedido.setStatus(StatusPedido.ENTREGUE);
		pedido.setDataEntrega(OffsetDateTime.now());
	}

	private void throwNegocioException(Pedido pedido, StatusPedido statusPedido) {
		throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s",
				pedido.getId(), pedido.getStatus().getDescricao(), statusPedido.getDescricao()));
	}

}
