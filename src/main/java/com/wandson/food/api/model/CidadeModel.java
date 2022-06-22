package com.wandson.food.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeModel {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "João Pessoa")
	private String nome;
	private EstadoModel estado;

}
