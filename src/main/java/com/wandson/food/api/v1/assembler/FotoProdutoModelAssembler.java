package com.wandson.food.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.wandson.food.api.v1.Links;
import com.wandson.food.api.v1.controller.RestauranteProdutoFotoController;
import com.wandson.food.api.v1.model.FotoProdutoModel;
import com.wandson.food.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Links links;

	public FotoProdutoModelAssembler() {
		super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
	}

	@Override
	public FotoProdutoModel toModel(FotoProduto foto) {
		var fotoProdutoModel = modelMapper.map(foto, FotoProdutoModel.class);

		fotoProdutoModel.add(links.linkToFotoProduto(foto.getRestauranteId(), foto.getProduto().getId()));
		fotoProdutoModel.add(links.linkToProduto(foto.getRestauranteId(), foto.getProduto().getId(), "produto"));

		return fotoProdutoModel;
	}

}
