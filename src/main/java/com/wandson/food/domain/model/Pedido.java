package com.wandson.food.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.wandson.food.domain.event.PedidoConfirmadoEvent;
import com.wandson.food.domain.exception.NegocioException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Pedido extends AbstractAggregateRoot<Pedido> {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String codigo;

	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;

	@Embedded
	private Endereco enderecoEntrega;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@CreationTimestamp
	private OffsetDateTime dataCriacao;

	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;

	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens;

	public Pedido() {
		itens = new ArrayList<>();
		status = StatusPedido.CRIADO;
	}

	public void calcularValorTotal() {
		itens.forEach(ItemPedido::calcularPrecoTotal);

		subtotal = itens.stream().map(ItemPedido::getPrecoTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

		valorTotal = subtotal.add(taxaFrete);
	}

	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		dataConfirmacao = OffsetDateTime.now();

		registerEvent(new PedidoConfirmadoEvent(this));
	}

	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		dataEntrega = OffsetDateTime.now();
	}

	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		dataCancelamento = OffsetDateTime.now();
	}

	private void setStatus(StatusPedido status) {
		if (this.status.naoPodeAlterarPara(status)) {
			throw new NegocioException(String.format("Status do pedido %s não pode ser alterado de %s para %s", codigo,
					this.status.getDescricao(), status.getDescricao()));
		}
		this.status = status;
	}

	@PrePersist
	private void gerarCodigo() {
		codigo = UUID.randomUUID().toString();
	}

}
