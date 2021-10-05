package com.wandson.food.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wandson.food.api.model.GrupoModel;
import com.wandson.food.domain.model.Grupo;

@Component
public class GrupoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public GrupoModel toModel(Grupo grupo) {
		return modelMapper.map(grupo, GrupoModel.class);
	}

	public List<GrupoModel> toCollectionModel(Collection<Grupo> grupos) {
		return grupos.stream().map(this::toModel).collect(Collectors.toList());
	}

}
