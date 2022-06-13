package com.wandson.food.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wandson.food.api.model.CidadeModel;
import com.wandson.food.domain.model.Cidade;

@Component
public class CidadeModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CidadeModel toModel(Cidade cidade) {
		return modelMapper.map(cidade, CidadeModel.class);
	}

	public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
		return cidades.stream().map(this::toModel).toList();
	}
}
