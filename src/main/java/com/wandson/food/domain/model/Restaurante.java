package com.wandson.food.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
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
	@JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento;

	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> responsaveis;

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos;

	public Restaurante() {
		formasPagamento = new HashSet<>();
		responsaveis = new HashSet<>();
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

	public boolean removerResponsavel(Usuario usuario) {
		return responsaveis.remove(usuario);
	}

	public boolean adicionarResponsavel(Usuario usuario) {
		return responsaveis.add(usuario);
	}

	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
		return !aceitaFormaPagamento(formaPagamento);
	}

	public boolean aberturaPermitida() {
		return getAtivo() && isFechado();
	}

	public boolean ativacaoPermitida() {
		return isInativo();
	}

	public boolean inativacaoPermitida() {
		return getAtivo();
	}

	public boolean fechamentoPermitido() {
		return getAberto();
	}

	private boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
		return formasPagamento.contains(formaPagamento);
	}

	private boolean isFechado() {
		return !getAberto();
	}

	private boolean isInativo() {
		return !getAtivo();
	}

}
