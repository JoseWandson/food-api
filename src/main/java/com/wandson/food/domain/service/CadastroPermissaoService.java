package com.wandson.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandson.food.domain.exception.PermissaoNaoEncontradaException;
import com.wandson.food.domain.model.Permissao;
import com.wandson.food.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;

	public Permissao buscarOuFalhar(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}

}