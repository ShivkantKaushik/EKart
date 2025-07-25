package ekart.order_service.controller;


import ekart.order_service.dto.OrderRequest;
import ekart.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }

}
