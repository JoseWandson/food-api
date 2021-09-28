package com.wandson.food.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private BigDecimal taxaFrete;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cozinha cozinha;

	@Embedded
	private Endereco endereco;
	private Boolean ativo;
	private Boolean aberto;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;

	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento", inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento;

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos;

	public Restaurante() {
		formasPagamento = new HashSet<>();
		produtos = new ArrayList<>();

		ativar();
		fechar();
	}

	public void ativar() {
		ativo = true;
	}

	public void inativar() {
		ativo = false;
	}

	public void abrir() {
		aberto = true;
	}

	public void fechar() {
		aberto = false;
	}

	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return formasPagamento.remove(formaPagamento);
	}

	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return formasPagamento.add(formaPagamento);
	}

}
