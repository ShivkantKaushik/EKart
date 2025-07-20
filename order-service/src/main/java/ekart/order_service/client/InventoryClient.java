package ekart.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(value = "inventory", url = "http://localhost:4002") // with hardcoding inventory service url

@FeignClient(value = "inventory", url = "${inventory.service.url}") //url from application.properties
public interface InventoryClient {


    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory")
    boolean inStock(@RequestParam String skuCode, @RequestParam Integer quantity);

}
