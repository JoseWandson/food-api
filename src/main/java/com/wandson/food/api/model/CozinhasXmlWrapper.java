package com.wandson.food.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.wandson.food.domain.model.Cozinha;

import lombok.Data;
import lombok.NonNull;

@Data
@JsonRootName("cozinhas")
public class CozinhasXmlWrapper {

	@NonNull
	@JsonProperty("cozinha")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Cozinha> cozinhas;

}
