package com.wandson.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wandson.food.domain.exception.RestauranteNaoEncontradoException;
import com.wandson.food.domain.model.Restaurante;
import com.wandson.food.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private CadastroCozinhaService cozinhaService;

	@Autowired
	private RestauranteRepository restauranteRepository;

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		var cozinha = cozinhaService.buscarOuFalhar(cozinhaId);

		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

}
