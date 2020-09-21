package com.wandson.food.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandson.food.domain.model.Cozinha;
import com.wandson.food.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}

	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}

	public Cozinha buscar(Long cozinhaId) {
		return cozinhaRepository.buscar(cozinhaId);
	}

	public void remover(Cozinha cozinha) {
		cozinhaRepository.remover(cozinha);
	}

}
