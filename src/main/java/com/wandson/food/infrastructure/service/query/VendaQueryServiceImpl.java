package com.wandson.food.infrastructure.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.wandson.food.domain.filter.VendaDiariaFilter;
import com.wandson.food.domain.model.Pedido;
import com.wandson.food.domain.model.StatusPedido;
import com.wandson.food.domain.model.dto.VendaDiaria;
import com.wandson.food.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

	private static final String DATA_CRIACAO = "dataCriacao";

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);
		var predicates = new ArrayList<Predicate>();

		var functionConvertTzDataCriacao = builder.function("convert_tz", Date.class, root.get(DATA_CRIACAO),
				builder.literal("+00:00"), builder.literal(timeOffset));
		var functionDateDataCriacao = builder.function("date", Date.class, functionConvertTzDataCriacao);

		var selection = builder.construct(VendaDiaria.class, functionDateDataCriacao, builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));

		if (Objects.nonNull(filtro.getRestauranteId())) {
			predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}

		if (Objects.nonNull(filtro.getDataCriacaoInicio())) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(DATA_CRIACAO), filtro.getDataCriacaoInicio()));
		}

		if (Objects.nonNull(filtro.getDataCriacaoFim())) {
			predicates.add(builder.lessThanOrEqualTo(root.get(DATA_CRIACAO), filtro.getDataCriacaoFim()));
		}

		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

		query.select(selection);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy(functionDateDataCriacao);

		return manager.createQuery(query).getResultList();
	}

}
