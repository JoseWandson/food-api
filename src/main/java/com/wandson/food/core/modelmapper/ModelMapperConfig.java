package com.wandson.food.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wandson.food.api.v1.model.EnderecoModel;
import com.wandson.food.api.v1.model.input.ItemPedidoInput;
import com.wandson.food.api.v2.model.input.CidadeInputV2;
import com.wandson.food.domain.model.Cidade;
import com.wandson.food.domain.model.Endereco;
import com.wandson.food.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
				.addMappings(mapper -> mapper.skip(ItemPedido::setId));

		modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class).addMappings(mapper -> mapper.skip(Cidade::setId));

		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);
		enderecoToEnderecoModelTypeMap.<String>addMapping(enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

		return modelMapper;
	}

}
