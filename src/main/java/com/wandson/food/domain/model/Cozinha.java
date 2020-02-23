package com.wandson.food.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tab_cozinhas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {

	@Id
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "nom_cozinha")
	private String nome;

}
