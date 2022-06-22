package com.wandson.food.api.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoIdInput {

	@NotNull
	@Schema(example = "1", required = true)
	private Long id;

}
