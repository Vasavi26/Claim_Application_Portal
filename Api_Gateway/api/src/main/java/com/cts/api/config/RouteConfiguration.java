package com.cts.api.config;



import com.cts.api.filter.TokenValidationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@Configuration
public class RouteConfiguration {
	
	private final TokenValidationFilter tokenValidationFilter;
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		log.info("API Route entered");
		return builder.routes()
				.route("adminModule",ps->ps.path("/auth/**")
				.uri("http://localhost:8200/auth"))
				

				.route("commonModule",ps->ps.path("/common/**")
				.filters(f->f.filter(tokenValidationFilter))
				.uri("http://localhost:8300/common")).build();

//
//

	}
}
