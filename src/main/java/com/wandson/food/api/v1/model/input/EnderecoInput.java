package com.wandson.food.api.v1.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@NotBlank
	@Schema(example = "38400-000", required = true)
	private String cep;

	@NotBlank
	@Schema(example = "Rua Floriano Peixoto", required = true)
	private String logradouro;

	@NotBlank
	@Schema(example = "1500", required = true)
	private String numero;

	@Schema(example = "Apto 901")
	private String complemento;

	@NotBlank
	@Schema(example = "Centro", required = true)
	private String bairro;

	@Valid
	@NotNull
	private CidadeIdInput cidade;

}
