package com.wandson.food.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String senha;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;

	@ManyToMany
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private Set<Grupo> grupos;

	public Usuario() {
		grupos = new HashSet<>();
	}

	public boolean senhaCoincideCom(String senha) {
		return this.senha.equals(senha);
	}

	public boolean senhaNaoCoincideCom(String senha) {
		return !senhaCoincideCom(senha);
	}

	public boolean removerGrupo(Grupo grupo) {
		return grupos.remove(grupo);
	}

	public boolean adicionarGrupo(Grupo grupo) {
		return grupos.add(grupo);
	}

}
