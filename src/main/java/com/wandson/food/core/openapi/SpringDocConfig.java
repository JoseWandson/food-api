package com.wandson.food.core.openapi;

import java.util.Objects;

import org.springdoc.core.SpringDocUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.wandson.food.api.exceptionhandler.Problem;
import com.wandson.food.api.openapi.model.PageableModelOpenApi;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SpringDocConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(
				new Info().title("WandFood Api").description("API aberta para clientes e restaurantes").version("1")
						.contact(new Contact().name("Wandson")
								.url("https://www.linkedin.com/in/wandson-cavalcante-b8428b139/")
								.email("wandsonacop@hotmail.com")));
	}

	@Bean
	public OpenApiCustomiser openApiCustomiser() {
		SpringDocUtils.getConfig().replaceWithClass(Pageable.class, PageableModelOpenApi.class);

		return openApi -> openApi.getPaths().values().forEach(pathItem -> {
			Operation operationGet = pathItem.getGet();
			if (Objects.nonNull(operationGet)) {
				ApiResponses apiResponses = operationGet.getResponses();
				apiResponses.addApiResponse(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()),
						apiResponseNotAcceptable());
				apiResponses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						apiResponseInternalServerError());
			}

			Operation operationPost = pathItem.getPost();
			if (Objects.nonNull(operationPost)) {
				globalPostPutApiResponses(operationPost);
			}

			Operation operationPut = pathItem.getPut();
			if (Objects.nonNull(operationPut)) {
				globalPostPutApiResponses(operationPut);
			}

			Operation operationtDelete = pathItem.getDelete();
			if (Objects.nonNull(operationtDelete)) {
				ApiResponses apiResponses = operationtDelete.getResponses();
				apiResponses.addApiResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), apiResponseBadRequest());
				apiResponses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						apiResponseInternalServerError());
			}
		});
	}

	private void globalPostPutApiResponses(Operation operation) {
		ApiResponses apiResponses = operation.getResponses();
		apiResponses.addApiResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), apiResponseBadRequest());
		apiResponses.addApiResponse(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()), apiResponseNotAcceptable());
		apiResponses.addApiResponse(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()),
				apiResponseUnsupportedMediaType());
		apiResponses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
				apiResponseInternalServerError());
	}

	private ApiResponse apiResponseBadRequest() {
		return apiResponse("Requisição inválida (erro do cliente)");
	}

	private ApiResponse apiResponseNotAcceptable() {
		return new ApiResponse().description("Recurso não possui representação que pode ser aceita pelo consumidor");
	}

	private ApiResponse apiResponseUnsupportedMediaType() {
		return apiResponse("Requisição recusada porque o corpo está em um formato não suportado");
	}

	private ApiResponse apiResponseInternalServerError() {
		return apiResponse("Erro interno do Servidor");
	}

	@SuppressWarnings("unchecked")
	private ApiResponse apiResponse(String description) {
		Schema<Problem> schema = ModelConverters.getInstance()
				.resolveAsResolvedSchema(new AnnotatedType(Problem.class).resolveAsRef(true)).schema;

		return new ApiResponse().description(description).content(new Content().addMediaType(
				org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType().schema(schema)));
	}

}