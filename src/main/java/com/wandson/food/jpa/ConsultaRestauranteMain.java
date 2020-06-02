package com.wandson.food.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.wandson.food.FoodApiApplication;
import com.wandson.food.domain.model.Restaurante;
import com.wandson.food.domain.repository.RestauranteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

		List<Restaurante> todosRestaurantes = restauranteRepository.listar();

		for (Restaurante restaurante : todosRestaurantes) {
			log.info(String.format("%s - %f - %s", restaurante.getNome(), restaurante.getTaxaFrete(),
					restaurante.getCozinha().getNome()));
		}
	}

}
