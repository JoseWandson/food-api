package com.wandson.food.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wandson.food.domain.exception.EntidadeEmUsoException;
import com.wandson.food.domain.exception.EntidadeNaoEncontradaException;
import com.wandson.food.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Problema> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
		Problema problema = Problema.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<Problema> tratarEntidadeEmUsoException(EntidadeEmUsoException e) {
		Problema problema = Problema.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build();
		return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Problema> tratarNegocioException(NegocioException e) {
		Problema problema = Problema.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}

}
