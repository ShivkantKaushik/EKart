package ekart.order_service.service;

import ekart.order_service.client.InventoryClient;
import ekart.order_service.dto.OrderRequest;
import ekart.order_service.model.Order;
import ekart.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) throws Exception {


       boolean productInStock =  inventoryClient.inStock(orderRequest.skuCode(), orderRequest.quantity());

       if(productInStock){
           Order order = new Order();
           order.setOrderNumber(UUID.randomUUID().toString());
           order.setPrice(orderRequest.price().multiply(BigDecimal.valueOf(orderRequest.quantity())));
           order.setSkuCode(orderRequest.skuCode());
           order.setQuantity(orderRequest.quantity());
           orderRepository.save(order);
       }else {
           throw new Exception("Product with SKU Code " + orderRequest.skuCode() + " not in stock.");
       }




    }

}
