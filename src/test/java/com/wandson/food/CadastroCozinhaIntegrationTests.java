package com.wandson.food;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wandson.food.domain.model.Cozinha;
import com.wandson.food.domain.service.CadastroCozinhaService;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Test
	void testarCadastroCozinhaComSucesso() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		novaCozinha = cadastroCozinha.salvar(novaCozinha);

		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	@Test
	void testarCadastroCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		assertThrows(ConstraintViolationException.class, () -> cadastroCozinha.salvar(novaCozinha));
	}

}
