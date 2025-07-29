package ekart.product_service.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//in swagger-ui.html page on top, to have some information we can create this bean
//otherwise without this also it works

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productServiceAPI(){

        return new OpenAPI()
                .info(new Info().title("Product Service API").
                        description("Rest API for Product Service")
                        .version("v1")
                        .license(new License().name("License1")))
                .externalDocs(new ExternalDocumentation()
                        .description("Refer to REST API Section")
                        .url("A url"));

    }

}
