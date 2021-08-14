package com.wandson.food.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.wandson.food.api.model.CozinhaModel;
import com.wandson.food.api.model.RestauranteModel;
import com.wandson.food.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler {

	public RestauranteModel toModel(Restaurante restaurante) {
		CozinhaModel cozinhaModel = new CozinhaModel();
		cozinhaModel.setId(restaurante.getCozinha().getId());
		cozinhaModel.setNome(restaurante.getCozinha().getNome());

		RestauranteModel restauranteModel = new RestauranteModel();
		restauranteModel.setId(restaurante.getId());
		restauranteModel.setNome(restaurante.getNome());
		restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
		restauranteModel.setCozinha(cozinhaModel);
		return restauranteModel;
	}

	public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(this::toModel).collect(Collectors.toList());
	}

}
