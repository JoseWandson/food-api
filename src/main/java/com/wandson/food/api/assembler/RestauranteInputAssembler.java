package com.wandson.food.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wandson.food.api.model.input.RestauranteInput;
import com.wandson.food.domain.model.Restaurante;

@Component
public class RestauranteInputAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public RestauranteInput toInput(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteInput.class);
	}

}
