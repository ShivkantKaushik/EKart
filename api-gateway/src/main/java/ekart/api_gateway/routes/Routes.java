package ekart.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.*;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class Routes {


    //these are called spring mvc functional endpoints
    //https://docs.spring.io/spring-framework/reference/web/webmvc-functional.html
    //even we can use get, post etc. mapping with this thing

    //but here we are doing what we used to do in yml file, routes, predicates, filters

    @Bean
    RouterFunction<ServerResponse> productServiceRoutes() {

        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:4000"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> orderServiceRoutes() {

        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:4001"))
                .build();
    }


    @Bean
    RouterFunction<ServerResponse> notificationServiceRoutes() {

        return GatewayRouterFunctions.route("inventory_service")
                .route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:4002"))
                .build();
    }


    @Bean
    RouterFunction<ServerResponse> productServiceSwaggerRoute(){

        return GatewayRouterFunctions.route("product_service_swagger")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:4000"))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> orderServiceSwaggerRoute(){
       return GatewayRouterFunctions.route("order_service_swagger")
                .route( RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:4001"))
                .filter(setPath("/api-docs"))
                .build();

    }

    @Bean
    RouterFunction<ServerResponse> inventoryServiceSwaggerRoute(){

        return GatewayRouterFunctions.route("inventory_service_swagger")
                .route( RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http("http://localhost:4002") )
                .filter(setPath("/api-docs"))
                .build();

    }


}
