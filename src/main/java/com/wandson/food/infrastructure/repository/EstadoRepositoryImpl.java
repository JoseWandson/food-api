package com.wandson.food.infrastructure.repository;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wandson.food.domain.model.Estado;
import com.wandson.food.domain.repository.EstadoRepository;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Estado> listar() {
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

	@Override
	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}

	@Override
	@Transactional
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Estado estado = buscar(id);

		if (Objects.isNull(estado)) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(estado);
	}

}
