package ekart.order_service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

//@FeignClient(value = "inventory", url = "http://localhost:4002") // with hardcoding inventory service url

//@FeignClient(value = "inventory", url = "${inventory.service.url}") //url from application.properties

//removing feign clients, from spring version 3, we have http interface clients, where
//we have similar functionality inbuilt

public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);


//    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory")

    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallBackMethod")
    @Retry(name = "inventory")
    boolean inStock(@RequestParam String skuCode, @RequestParam Integer quantity);


    default boolean fallBackMethod(String code, Integer quantity, Throwable throwable) {
        log.info("Cannot get inventory for skucode {}, failure reason: {}", code, throwable.getMessage());
        return false;
    }

}
