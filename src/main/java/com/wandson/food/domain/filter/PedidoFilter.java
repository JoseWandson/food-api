package com.wandson.food.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoFilter {

	@ApiModelProperty("ID do cliente para filtro da pesquisa")
	private Long clienteId;

	@ApiModelProperty("ID do restaurante para filtro da pesquisa")
	private Long restauranteId;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@ApiModelProperty("Data/hora de criação inicial para filtro da pesquisa")
	private OffsetDateTime dataCriacaoInicio;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@ApiModelProperty("Data/hora de criação final para filtro da pesquisa")
	private OffsetDateTime dataCriacaoFim;

}
