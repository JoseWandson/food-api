package com.wandson.food.api.assembler;

import org.springframework.stereotype.Component;

import com.wandson.food.api.model.input.CozinhaIdInput;
import com.wandson.food.api.model.input.RestauranteInput;
import com.wandson.food.domain.model.Restaurante;

@Component
public class RestauranteInputAssembler {

	public RestauranteInput toInput(Restaurante restaurante) {
		RestauranteInput restauranteInput = new RestauranteInput();
		restauranteInput.setNome(restaurante.getNome());
		restauranteInput.setTaxaFrete(restaurante.getTaxaFrete());

		CozinhaIdInput cozinhaIdInput = new CozinhaIdInput();
		cozinhaIdInput.setId(restaurante.getCozinha().getId());

		restauranteInput.setCozinha(cozinhaIdInput);

		return restauranteInput;
	}

}
