package br.com.xyinc.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.xyinc.rest.GPSController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = { GPSController.class })
public class SwaggerConfig {
	private ApiInfo apiInfo() {
		return new ApiInfo(Version.NOME_SISTEMA, Version.DESCRICAO_SISTEMA, Version.VERSAO_BUILD, "",
				new Contact("Lucas Volgarini", null, "lucasvolgarini@gmail.com"), "", // "License of API",
				"", // "API license URL",
				Collections.emptyList());
	}

	@Bean
	public Docket api() {
		final List<ResponseMessage> globalResponses = Arrays.asList(
				new ResponseMessageBuilder().code(401).message("Unauthorized").build(),
				new ResponseMessageBuilder().code(403).message("Forbidden").build(),
				new ResponseMessageBuilder().code(404).message("Not Found").build(),
				new ResponseMessageBuilder().code(500).message("Internal Server Error").build());

		return new Docket(DocumentationType.SWAGGER_2).groupName("poi").useDefaultResponseMessages(false)
				.apiInfo(apiInfo()).globalResponseMessage(RequestMethod.POST, globalResponses)
				.globalResponseMessage(RequestMethod.PUT, globalResponses)
				.globalResponseMessage(RequestMethod.GET, globalResponses)
				.globalResponseMessage(RequestMethod.DELETE, globalResponses).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.regex("/v2.*")).build();
	}

	@Bean
	protected UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().deepLinking(true).displayOperationId(false).defaultModelsExpandDepth(1)
				.defaultModelExpandDepth(1).defaultModelRendering(ModelRendering.EXAMPLE).displayRequestDuration(false)
				.docExpansion(DocExpansion.NONE).filter(false).maxDisplayedTags(null)
				.operationsSorter(OperationsSorter.ALPHA).showExtensions(false).tagsSorter(TagsSorter.ALPHA)
				.supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS).validatorUrl(null).build();
	}
}
