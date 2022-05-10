package com.wandson.food.core.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wandson.food.domain.service.EnvioEmailService;
import com.wandson.food.infrastructure.service.email.FakeEnvioEmailService;
import com.wandson.food.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

	@Bean
	public EnvioEmailService envioEmailService(EmailProperties emailProperties) {
		return switch (emailProperties.getImpl()) {
		case FAKE -> new FakeEnvioEmailService();
		case SMTP -> new SmtpEnvioEmailService();
		};
	}
}