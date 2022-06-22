package com.wandson.food.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoFilter {

	@Schema(example = "1", description = "ID do cliente para filtro da pesquisa")
	private Long clienteId;

	@Schema(example = "1", description = "ID do restaurante para filtro da pesquisa")
	private Long restauranteId;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Schema(example = "2019-10-30T00:00:00Z", description = "Data/hora de criação inicial para filtro da pesquisa")
	private OffsetDateTime dataCriacaoInicio;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Schema(example = "2019-11-01T10:00:00Z", description = "Data/hora de criação final para filtro da pesquisa")
	private OffsetDateTime dataCriacaoFim;

}
