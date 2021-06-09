package com.wandson.food.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.wandson.food.domain.repository.CustomJpaRepository;

public class CustomJpaRepositoryImpl<T, I> extends SimpleJpaRepository<T, I> implements CustomJpaRepository<T, I> {

	private EntityManager manager;

	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		manager = entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiro() {
		var jpql = "from " + getDomainClass().getName();

		var entity = manager.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult();

		return Optional.ofNullable(entity);
	}

}
