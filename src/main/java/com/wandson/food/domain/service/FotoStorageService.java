package com.wandson.food.domain.service;

import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {

	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}

	default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
		armazenar(novaFoto);

		if (StringUtils.isNotBlank(nomeArquivoAntigo)) {
			remover(nomeArquivoAntigo);
		}
	}

	void armazenar(NovaFoto novaFoto);

	void remover(String nomeArquivo);

	@Getter
	@Builder
	class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;
	}

}
