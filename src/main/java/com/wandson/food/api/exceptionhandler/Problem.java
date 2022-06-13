package com.wandson.food.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
public class Problem {

	@ApiModelProperty(example = "400")
	private Integer status;

	@ApiModelProperty(example = "https://wandfood.com.br/dados-invalidos")
	private String type;

	@ApiModelProperty(example = "Dados inválidos")
	private String title;

	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
	private String detail;

	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
	private String userMessage;

	@ApiModelProperty(example = "2022-06-13T18:15:27.9899105Z")
	private OffsetDateTime timestamp;

	@ApiModelProperty("Lista de objetos ou campos que geraram o erro (opcional)")
	private List<Object> objects;

	@Getter
	@Builder
	@ApiModel("ObjetoProblema")
	public static class Object {

		@ApiModelProperty(example = "preco")
		private String name;

		@ApiModelProperty(example = "O preço é obrigatório")
		private String userMessage;
	}

}
