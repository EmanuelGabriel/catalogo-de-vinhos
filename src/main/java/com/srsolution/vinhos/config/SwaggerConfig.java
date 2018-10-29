package com.srsolution.vinhos.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.srsolution.vinhos")).paths(regex("/vinhos/api/v1.*"))
				.build().apiInfo(metaInfo());
	}

	
	private ApiInfo metaInfo() {
		// Descrição acerca da API REST.
		String titulo = "Vinho API RESTful";
		String descricao = "RESTful Web Service de cadastro de vinho";
		String termosECondicoes = "Termos e Condições de uso";

		// Descrição acerca do contato do desenvolvedor e sua licença.
		String nomeDev = "Emanuel A. Gabriel";
		String urlDev = "https://emanuelgabriel.github.io";
		String emailDev = "emanuel.gabriel.sousa@hotmail.com";
		String licencaDev = "SRSolution Ltda Versão 1.0";
		String licencaUrlDev = "https://www.srsolution.com.br";

		@SuppressWarnings("rawtypes")
		ApiInfo apiInfo = new ApiInfo(titulo, descricao, "1.0", termosECondicoes,
				new Contact(nomeDev, urlDev, emailDev), licencaDev, licencaUrlDev, new ArrayList<VendorExtension>());

		return apiInfo;
	}

	
}
