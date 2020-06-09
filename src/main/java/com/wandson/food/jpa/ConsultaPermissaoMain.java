package com.wandson.food.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.wandson.food.FoodApiApplication;
import com.wandson.food.domain.model.Permissao;
import com.wandson.food.domain.repository.PermissaoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsultaPermissaoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);

		List<Permissao> todasPermissoes = permissaoRepository.listar();

		for (Permissao permissao : todasPermissoes) {
			log.info(String.format("%s - %s%n", permissao.getNome(), permissao.getDescricao()));
		}
	}

}
