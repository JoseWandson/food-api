package com.wandson.food.api.model.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wandson.food.domain.model.Restaurante;

public abstract class CozinhaMixin {

	@JsonIgnore
	private List<Restaurante> restaurantes;

}
