package com.wandson.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wandson.food.domain.exception.EntidadeEmUsoException;
import com.wandson.food.domain.exception.EstadoNaoEncontradoException;
import com.wandson.food.domain.model.Estado;
import com.wandson.food.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";

	@Autowired
	private EstadoRepository estadoRepository;

	@Transactional
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
	}

	@Transactional
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}

}
