package com.wandson.food.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.wandson.food.api.v1.Links;
import com.wandson.food.api.v1.model.PermissaoModel;
import com.wandson.food.domain.model.Permissao;

@Component
public class PermissaoModelAssembler implements RepresentationModelAssembler<Permissao, PermissaoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Links links;

	@Override
	public PermissaoModel toModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoModel.class);
	}

	@Override
	public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
		return RepresentationModelAssembler.super.toCollectionModel(entities).add(links.linkToPermissoes());
	}

}