package ekart.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ApiGatewayApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("GET", "PUT", "PATCH", "DELETE", "POST", "OPTIONS","HEAD")
				.allowedHeaders("*")
				.allowCredentials(true);
	}

}
