package com.wandson.food.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.wandson.food.domain.event.PedidoConfirmadoEvent;
import com.wandson.food.domain.model.Pedido;
import com.wandson.food.domain.service.EnvioEmailService;
import com.wandson.food.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

	@Autowired
	private EnvioEmailService envioEmail;

	@TransactionalEventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		Pedido pedido = event.getPedido();

		var mensagem = Mensagem.builder().destinatario(pedido.getCliente().getEmail())
				.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado").variavel("pedido", pedido)
				.corpo("pedido-confirmado.html").build();

		envioEmail.enviar(mensagem);
	}

}
