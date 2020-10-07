package com.wandson.food.api.controller;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wandson.food.domain.exception.EntidadeNaoEncontradaException;
import com.wandson.food.domain.model.Restaurante;
import com.wandson.food.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteService.listar();
	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		if (Objects.nonNull(restaurante)) {
			return ResponseEntity.ok(restaurante);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = restauranteService.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteAtual = restauranteService.buscar(restauranteId);

			if (Objects.nonNull(restauranteAtual)) {
				BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

				restauranteAtual = restauranteService.salvar(restauranteAtual);
				return ResponseEntity.ok(restauranteAtual);
			}
			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> campos) {
		Restaurante restauranteAtual = restauranteService.buscar(restauranteId);

		if (Objects.isNull(restauranteAtual)) {
			return ResponseEntity.notFound().build();
		}

		merge(campos, restauranteAtual);

		return atualizar(restauranteId, restauranteAtual);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			char c = Character.toUpperCase(nomePropriedade.charAt(0));
			String nomeMetodo = "get" + c + nomePropriedade.substring(1);
			Method method = ReflectionUtils.findMethod(Restaurante.class, nomeMetodo);

			Object novoValor = ReflectionUtils.invokeMethod(method, restauranteOrigem);

			nomeMetodo = "s" + nomeMetodo.substring(1);
			method = ReflectionUtils.findMethod(Restaurante.class, nomeMetodo, novoValor.getClass());
			ReflectionUtils.invokeMethod(method, restauranteDestino, novoValor);
		});
	}

}
