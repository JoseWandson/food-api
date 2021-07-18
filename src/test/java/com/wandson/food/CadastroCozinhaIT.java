package com.wandson.food;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

	@LocalServerPort
	private int port;

	@Autowired
	private Flyway flyway;

	@BeforeEach
	void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";

		flyway.migrate();
	}

	@Test
	void deveRetornarStatus200_QuandoConsultarCozinhas() {
		given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
	}

	@Test
	void deveConter4Cozinhas_QuandoConsultarCozinhas() {
		given().accept(ContentType.JSON).when().get().then().body("", hasSize(4)).body("nome",
				hasItems("Indiana", "Tailandesa"));
	}

	@Test
	void testRetornarStatus201_QuandoCadastrarCozinha() {
		given().body("{ \"nome\": \"Chinesa\" }").contentType(ContentType.JSON).accept(ContentType.JSON).when().post()
				.then().statusCode(HttpStatus.CREATED.value());
	}

}
