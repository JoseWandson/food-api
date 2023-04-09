package com.wandson.food.api.v1.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.wandson.food.core.validation.FileContentType;
import com.wandson.food.core.validation.FileSize;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {

	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	@Schema(description = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)", required = true)
	private MultipartFile arquivo;

	@NotBlank
	@Schema(description = "Descrição da foto do produto", required = true)
	private String descricao;

}
