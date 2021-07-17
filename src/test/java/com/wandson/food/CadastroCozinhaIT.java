package com.wandson.food;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wandson.food.domain.exception.CozinhaNaoEncontradaException;
import com.wandson.food.domain.exception.EntidadeEmUsoException;
import com.wandson.food.domain.model.Cozinha;
import com.wandson.food.domain.service.CadastroCozinhaService;

@SpringBootTest
class CadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Test
	void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		novaCozinha = cadastroCozinha.salvar(novaCozinha);

		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	@Test
	void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		assertThrows(ConstraintViolationException.class, () -> cadastroCozinha.salvar(novaCozinha));
	}

	@Test
	void deveFalhar_QuandoExcluirCozinhaEmUso() {
		assertThrows(EntidadeEmUsoException.class, () -> cadastroCozinha.excluir(1L));
	}

	@Test
	void deveFalhar_QuandoExcluirCozinhaInexistente() {
		assertThrows(CozinhaNaoEncontradaException.class, () -> cadastroCozinha.excluir(100L));
	}

}
