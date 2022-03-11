package com.wandson.food.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {

	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}

	void armazenar(NovaFoto novaFoto);

	@Getter
	@Builder
	class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;
	}

}
