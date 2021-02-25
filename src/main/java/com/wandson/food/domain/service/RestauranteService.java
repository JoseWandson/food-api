package com.wandson.food.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandson.food.domain.exception.EntidadeNaoEncontradaException;
import com.wandson.food.domain.model.Cozinha;
import com.wandson.food.domain.model.Restaurante;
import com.wandson.food.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe um cadastro de restaurante com código %d";

	@Autowired
	private CozinhaService cozinhaService;

	@Autowired
	private RestauranteRepository restauranteRepository;

	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);

		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

}
