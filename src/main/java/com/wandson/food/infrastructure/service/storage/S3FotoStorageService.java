package com.wandson.food.infrastructure.service.storage;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.wandson.food.domain.service.FotoStorageService;

@Service
public class S3FotoStorageService implements FotoStorageService {

	@Override
	public void armazenar(NovaFoto novaFoto) {

	}

	@Override
	public void remover(String nomeArquivo) {

	}

	@Override
	public InputStream recuperar(String nomeArquivo) {
		return null;
	}

}