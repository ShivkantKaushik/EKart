package ekart.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.ParameterRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
public class SecurityConfig {

    private final String[] permittedURLs = {"/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**",
            "/swagger-resources/**", "/api-docs/**", "/aggregate/**", "/actuator/prometheus"};



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //here any request should be used in last, permittable urls should be configure prior
        //otherwise was throwing error

      return  httpSecurity.authorizeHttpRequests( (auth) -> {
            auth.requestMatchers(permittedURLs).permitAll()
                    .anyRequest().authenticated();
        } ).oauth2ResourceServer( oauth2 -> oauth2.jwt(Customizer.withDefaults()) )
           .build();

    }

}
