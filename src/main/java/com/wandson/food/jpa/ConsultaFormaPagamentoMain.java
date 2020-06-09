package com.wandson.food.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.wandson.food.FoodApiApplication;
import com.wandson.food.domain.model.FormaPagamento;
import com.wandson.food.domain.repository.FormaPagamentoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsultaFormaPagamentoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);

		List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.listar();

		for (FormaPagamento formaPagamento : todasFormasPagamentos) {
			log.info(formaPagamento.getDescricao());
		}
	}

}
