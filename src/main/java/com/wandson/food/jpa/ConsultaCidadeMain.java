package com.wandson.food.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.wandson.food.FoodApiApplication;
import com.wandson.food.domain.model.Cidade;
import com.wandson.food.domain.repository.CidadeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsultaCidadeMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);

		List<Cidade> todasCidades = cidadeRepository.listar();

		for (Cidade cidade : todasCidades) {
			log.info(String.format("%s - %s%n", cidade.getNome(), cidade.getEstado().getNome()));
		}
	}

}
