package com.wandson.food.api.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "_Links")
public class LinksModelOpenApi {

	private LinkModel rel;

	@Getter
	@Setter
	@Schema(name = "_Link")
	private class LinkModel {
		private String href;
		private boolean templated;
	}

}
