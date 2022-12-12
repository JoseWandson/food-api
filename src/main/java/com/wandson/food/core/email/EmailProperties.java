package com.wandson.food.core.email;

import jakarta.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties("food.email")
public class EmailProperties {

	@NotBlank
	private String remetente;
	private Implementacao impl;
	private Sandbox sandbox;

	public EmailProperties() {
		impl = Implementacao.FAKE;
		sandbox = new Sandbox();
	}

	public enum Implementacao {
		SMTP, FAKE, SANDBOX
	}

	@Getter
	@Setter
	public class Sandbox {
		private String destinatario;
	}

}
