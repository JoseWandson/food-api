package com.wandson.food.api.openapi.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedModelOpenApi<T> {

	private List<T> content;

	@Schema(example = "10", description = "Quantidade de registros por página")
	private int size;

	@Schema(example = "50", description = "Total de registros")
	private long totalElements;

	@Schema(example = "5", description = "Total de páginas")
	private int totalPages;

	@Schema(example = "0", description = "Número da página (começa em 0)")
	private int number;

}
