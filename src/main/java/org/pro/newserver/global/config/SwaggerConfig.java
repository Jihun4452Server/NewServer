package org.pro.newserver.global.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	private SecurityScheme createBearerAuthScheme() {
		return new SecurityScheme()
			.type(SecurityScheme.Type.HTTP)
			.bearerFormat("JWT")
			.scheme("bearer");
	}

	private OpenApiCustomizer createOpenApiCustomizer(String title, String version) {
		return openApi -> {
			openApi.info(new Info().title(title).version(version));
			openApi.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
			openApi.schemaRequirement("bearerAuth", createBearerAuthScheme());
			if (!serverUrl.isBlank()) {
				openApi.setServers(List.of(new Server().url(serverUrl)));
			}
		};
	}

	@Bean
	public GroupedOpenApi allApi() {
		return GroupedOpenApi.builder()
			.group("all")
			.pathsToMatch("/**")
			.displayName("All API")
			.addOpenApiCustomizer(createOpenApiCustomizer("모든 API", "v0.4"))
			.build();
	}

}