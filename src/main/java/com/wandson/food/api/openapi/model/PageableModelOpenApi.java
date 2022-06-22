package com.wandson.food.api.openapi.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Pageable")
public class PageableModelOpenApi {

	@Schema(description = "Número da página (começa em 0)", example = "0")
	private int page;

	@Schema(description = "Quantidade de elementos por página", example = "10")
	private int size;

	@ArraySchema(schema = @Schema(description = "Nome da propriedade para ordenação", example = "nome,asc"))
	private List<String> sort;

}
