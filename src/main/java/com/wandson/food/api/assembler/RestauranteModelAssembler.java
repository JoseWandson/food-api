package com.wandson.food.api.assembler;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.wandson.food.api.Links;
import com.wandson.food.api.controller.RestauranteController;
import com.wandson.food.api.model.RestauranteModel;
import com.wandson.food.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Links links;

	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);
	}

	@Override
	public RestauranteModel toModel(Restaurante restaurante) {
		RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		modelMapper.map(restaurante, restauranteModel);

		restauranteModel.add(links.linkToRestaurantes("restaurantes"));
		restauranteModel.add(links.linkToRestauranteFormasPagamento(restaurante.getId(), "formas-pagamento"));
		restauranteModel.add(links.linkToRestauranteResponsaveis(restaurante.getId(), "responsaveis"));

		if (restaurante.ativacaoPermitida()) {
			restauranteModel.add(links.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
		}

		if (restaurante.inativacaoPermitida()) {
			restauranteModel.add(links.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
		}

		if (restaurante.aberturaPermitida()) {
			restauranteModel.add(links.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
		}

		if (restaurante.fechamentoPermitido()) {
			restauranteModel.add(links.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
		}

		restauranteModel.add(links.linkToProdutos(restaurante.getId(), "produtos"));

		restauranteModel.getCozinha().add(links.linkToCozinha(restaurante.getCozinha().getId()));

		if (Objects.nonNull(restauranteModel.getEndereco())) {
			restauranteModel.getEndereco().getCidade()
					.add(links.linkToCidade(restaurante.getEndereco().getCidade().getId()));
		}

		return restauranteModel;
	}

	@Override
	public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities).add(links.linkToRestaurantes());
	}

}
