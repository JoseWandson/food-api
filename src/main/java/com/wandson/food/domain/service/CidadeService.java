package com.wandson.food.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wandson.food.domain.exception.EntidadeEmUsoException;
import com.wandson.food.domain.exception.EntidadeNaoEncontradaException;
import com.wandson.food.domain.model.Cidade;
import com.wandson.food.domain.model.Estado;
import com.wandson.food.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	private static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cidade com código %d";
	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeRepository cidadeRepository;

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoService.buscarOuFalhar(estadoId);
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}

	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}

}
