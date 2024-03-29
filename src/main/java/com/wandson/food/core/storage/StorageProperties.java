package com.wandson.food.core.storage;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("food.storage")
public class StorageProperties {

	private Local local;
	private S3 s3;
	private TipoStorage tipo;

	public StorageProperties() {
		local = new Local();
		s3 = new S3();
		tipo = TipoStorage.LOCAL;
	}

	@Getter
	@Setter
	public class Local {
		private Path diretorioFotos;
	}

	@Getter
	@Setter
	public class S3 {
		private String idChaveAcesso;
		private String chaveAcessoSecreta;
		private String bucket;
		private Regions regiao;
		private String diretorioFotos;
	}

	public enum TipoStorage {
		LOCAL, S3;
	}

}
