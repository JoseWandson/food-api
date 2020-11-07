package com.wandson.food.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandson.food.domain.exception.EntidadeNaoEncontradaException;
import com.wandson.food.domain.model.Cozinha;
import com.wandson.food.domain.model.Restaurante;
import com.wandson.food.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private CozinhaService cozinhaService;

	@Autowired
	private RestauranteRepository restauranteRepository;

	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	public Optional<Restaurante> buscar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId);
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscar(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));

		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

}
