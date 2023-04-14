package com.wandson.food.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@NotBlank
	@Schema(example = "38400-000", requiredMode = RequiredMode.REQUIRED)
	private String cep;

	@NotBlank
	@Schema(example = "Rua Floriano Peixoto", requiredMode = RequiredMode.REQUIRED)
	private String logradouro;

	@NotBlank
	@Schema(example = "1500", requiredMode = RequiredMode.REQUIRED)
	private String numero;

	@Schema(example = "Apto 901")
	private String complemento;

	@NotBlank
	@Schema(example = "Centro", requiredMode = RequiredMode.REQUIRED)
	private String bairro;

	@Valid
	@NotNull
	private CidadeIdInput cidade;

}
