package com.wandson.food.api.v2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.wandson.food.api.v2.controller.CidadeControllerV2;
import com.wandson.food.api.v2.controller.CozinhaControllerV2;

@Component
public class LinksV2 {

	public Link linkToCidades(String rel) {
		return linkTo(CidadeControllerV2.class).withRel(rel);
	}

	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF.value());
	}

	public Link linkToCozinhas(String rel) {
		return linkTo(CozinhaControllerV2.class).withRel(rel);
	}
}
