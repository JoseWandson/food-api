package com.wandson.food.api.openapi.controller;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;

import com.wandson.food.domain.filter.VendaDiariaFilter;
import com.wandson.food.domain.model.dto.VendaDiaria;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estatísticas", description = "Estatísticas da JWFood")
public interface EstatisticasControllerOpenApi {

	@Operation(summary = "Estatísticas", hidden = true)
	EstatisticasModel estatisticas();

	@Operation(summary = "Consulta estatísticas de vendas diárias")
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json"),
			@Content(mediaType = "application/pdf") })
	@Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", schema = @Schema(type = "int"), in = ParameterIn.QUERY)
	@Parameter(name = "dataCriacaoInicio", description = "Data/hora inicial da criação do pedido", example = "2019-12-01T00:00:00Z", schema = @Schema(type = "date-time"), in = ParameterIn.QUERY)
	@Parameter(name = "dataCriacaoFim", description = "Data/hora final da criação do pedido", example = "2019-12-02T23:59:59Z", schema = @Schema(type = "date-time"), in = ParameterIn.QUERY)
	List<VendaDiaria> consultarVendasDiarias(@Parameter(hidden = true) VendaDiariaFilter filtro,
			@Parameter(description = "Deslocamento de horário a ser considerado na consulta em relação ao UTC", schema = @Schema(defaultValue = "+00:00")) String timeOffset);

	@Hidden
	ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);

	static class EstatisticasModel extends RepresentationModel<EstatisticasModel> {
	}
}