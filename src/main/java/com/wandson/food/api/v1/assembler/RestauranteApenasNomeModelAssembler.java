package com.wandson.food.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.wandson.food.api.v1.Links;
import com.wandson.food.api.v1.controller.RestauranteController;
import com.wandson.food.api.v1.model.RestauranteApenasNomeModel;
import com.wandson.food.domain.model.Restaurante;

@Component
public class RestauranteApenasNomeModelAssembler
		extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Links links;

	public RestauranteApenasNomeModelAssembler() {
		super(RestauranteController.class, RestauranteApenasNomeModel.class);
	}

	@Override
	public RestauranteApenasNomeModel toModel(Restaurante restaurante) {
		RestauranteApenasNomeModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);

		modelMapper.map(restaurante, restauranteModel);

		restauranteModel.add(links.linkToRestaurantes("restaurantes"));

		return restauranteModel;
	}

	@Override
	public CollectionModel<RestauranteApenasNomeModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities).add(links.linkToRestaurantes());
	}
}