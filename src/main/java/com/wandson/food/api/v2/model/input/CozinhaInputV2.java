package com.wandson.food.api.v2.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CozinhaInput")
public class CozinhaInputV2 {

	@NotBlank
	@Schema(example = "Brasileira", requiredMode = RequiredMode.REQUIRED)
	private String nomeCozinha;

}
