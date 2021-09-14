package com.wandson.food.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wandson.food.api.model.UsuarioModel;
import com.wandson.food.domain.model.Usuario;

@Component
public class UsuarioModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioModel toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioModel.class);
	}

	public List<UsuarioModel> toCollectionModel(List<Usuario> usuarios) {
		return usuarios.stream().map(this::toModel).collect(Collectors.toList());
	}

}