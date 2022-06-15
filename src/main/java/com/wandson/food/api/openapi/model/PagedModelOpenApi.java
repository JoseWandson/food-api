package com.wandson.food.api.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedModelOpenApi<T> {

	private List<T> content;

	@ApiModelProperty(example = "10", value = "Quantidade de registros por página")
	private int size;

	@ApiModelProperty(example = "50", value = "Total de registros")
	private long totalElements;

	@ApiModelProperty(example = "5", value = "Total de páginas")
	private int totalPages;

	@ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
	private int number;

}
