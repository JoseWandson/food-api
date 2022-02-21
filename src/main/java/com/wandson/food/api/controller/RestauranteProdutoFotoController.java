package com.wandson.food.api.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wandson.food.api.model.input.FotoProdutoInput;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) {
		var nomeArquivo = UUID.randomUUID().toString() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();
		var arquivoFoto = Path.of("C:\\Users\\Avell\\OneDrive\\Imagens\\catalogo", nomeArquivo);

		log.info(fotoProdutoInput.getDescricao());
		log.info(arquivoFoto.toString());
		log.info(fotoProdutoInput.getArquivo().getContentType());

		try {
			fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}

}