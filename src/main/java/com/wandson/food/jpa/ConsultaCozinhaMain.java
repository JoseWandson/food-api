package com.wandson.food.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.wandson.food.FoodApiApplication;
import com.wandson.food.domain.model.Cozinha;
import com.wandson.food.domain.repository.CozinhaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

		List<Cozinha> todasCozinhas = cozinhaRepository.listar();

		for (Cozinha cozinha : todasCozinhas) {
			log.info(cozinha.getNome());
		}
	}

}
