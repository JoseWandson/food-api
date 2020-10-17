package com.wandson.food.infrastructure.repository;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wandson.food.domain.model.Cidade;
import com.wandson.food.domain.repository.CidadeRepository;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cidade> listar() {
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long id) {
		return manager.find(Cidade.class, id);
	}

	@Override
	@Transactional
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Cidade cidade = buscar(id);

		if (Objects.isNull(cidade)) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(cidade);
	}

}
