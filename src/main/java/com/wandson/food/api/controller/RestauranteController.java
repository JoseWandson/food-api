package com.wandson.food.api.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wandson.food.core.validation.ValidacaoException;
import com.wandson.food.domain.exception.CozinhaNaoEncontradaException;
import com.wandson.food.domain.exception.NegocioException;
import com.wandson.food.domain.model.Restaurante;
import com.wandson.food.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private SmartValidator validator;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteService.listar();
	}

	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Long restauranteId) {
		return restauranteService.buscarOuFalhar(restauranteId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
		try {
			return restauranteService.salvar(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody @Valid Restaurante restaurante) {
		var restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
				"produtos");

		try {
			return restauranteService.salvar(restauranteAtual);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PatchMapping("/{restauranteId}")
	public Restaurante atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos,
			HttpServletRequest request) {
		var restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

		merge(campos, restauranteAtual, request);
		validate(restauranteAtual, "restaurante");

		return atualizar(restauranteId, restauranteAtual);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
		var serverHttpRequest = new ServletServerHttpRequest(request);

		try {
			var objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);

			var restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				var c = Character.toUpperCase(nomePropriedade.charAt(0));
				String nomeMetodo = "get" + c + nomePropriedade.substring(1);
				var method = ReflectionUtils.findMethod(Restaurante.class, nomeMetodo);

				Object novoValor = ReflectionUtils.invokeMethod(method, restauranteOrigem);

				nomeMetodo = "s" + nomeMetodo.substring(1);
				method = ReflectionUtils.findMethod(Restaurante.class, nomeMetodo, novoValor.getClass());
				ReflectionUtils.invokeMethod(method, restauranteDestino, novoValor);
			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}

	private void validate(Restaurante restaurante, String objectName) {
		var bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		validator.validate(restaurante, bindingResult);

		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
	}

}
