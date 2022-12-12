package com.wandson.food.infrastructure.repository.spec;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.wandson.food.domain.filter.PedidoFilter;
import com.wandson.food.domain.model.Pedido;
import com.wandson.food.domain.model.Pedido_;
import com.wandson.food.domain.model.Restaurante_;
import com.wandson.food.domain.model.Usuario_;

import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoSpecs {

	public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
		return (root, query, builder) -> {
			if (Pedido.class.equals(query.getResultType())) {
				root.fetch("restaurante");
				root.fetch("cliente");
			}

			var predicates = new ArrayList<Predicate>();

			if (Objects.nonNull(filtro.getClienteId())) {
				predicates.add(builder.equal(root.get(Pedido_.cliente).get(Usuario_.id), filtro.getClienteId()));
			}
			if (Objects.nonNull(filtro.getRestauranteId())) {
				predicates.add(
						builder.equal(root.get(Pedido_.restaurante).get(Restaurante_.id), filtro.getRestauranteId()));
			}
			if (Objects.nonNull(filtro.getDataCriacaoInicio())) {
				predicates.add(
						builder.greaterThanOrEqualTo(root.get(Pedido_.dataCriacao), filtro.getDataCriacaoInicio()));
			}
			if (Objects.nonNull(filtro.getDataCriacaoFim())) {
				predicates.add(builder.lessThanOrEqualTo(root.get(Pedido_.dataCriacao), filtro.getDataCriacaoFim()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
