package com.wandson.food.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(name = "Problema")
@JsonInclude(Include.NON_NULL)
public class Problem {

	@Schema(example = "400")
	private Integer status;

	@Schema(example = "https://wandfood.com.br/dados-invalidos")
	private String type;

	@Schema(example = "Dados inválidos")
	private String title;

	@Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
	private String detail;

	@Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
	private String userMessage;

	@Schema(example = "2022-06-13T18:15:27.9899105Z")
	private OffsetDateTime timestamp;

	@ArraySchema(arraySchema = @Schema(description = "Lista de objetos ou campos que geraram o erro (opcional)"))
	private List<Object> objects;

	@Getter
	@Builder
	@Schema(name = "ObjetoProblema")
	public static class Object {

		@Schema(example = "preco")
		private String name;

		@Schema(example = "O preço é obrigatório")
		private String userMessage;
	}

}
