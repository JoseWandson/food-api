package com.wandson.food.domain.service;

import java.util.List;

import com.wandson.food.domain.filter.VendaDiariaFilter;
import com.wandson.food.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

}
