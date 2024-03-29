package com.wandson.food.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.wandson.food.domain.model.Restaurante;
import com.wandson.food.domain.model.Restaurante_;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestauranteSpecs {

	public static Specification<Restaurante> comFreteGratis() {
		return (root, query, builder) -> builder.equal(root.get(Restaurante_.taxaFrete), BigDecimal.ZERO);
	}

	public static Specification<Restaurante> comNomeSemelhante(String nome) {
		return (root, query, builder) -> builder.like(root.get(Restaurante_.nome), "%" + nome + "%");
	}

}
