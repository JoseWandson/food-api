package com.wandson.food.api.assembler;

import org.springframework.stereotype.Component;

import com.wandson.food.api.model.input.RestauranteInput;
import com.wandson.food.domain.model.Cozinha;
import com.wandson.food.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {

	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInput.getCozinha().getId());

		restaurante.setCozinha(cozinha);

		return restaurante;
	}

}
