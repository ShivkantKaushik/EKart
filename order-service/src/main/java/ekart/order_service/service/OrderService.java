package ekart.order_service.service;

import ekart.order_service.client.InventoryClient;
import ekart.order_service.dto.OrderRequest;
import ekart.order_service.event.OrderPlacedEvent;
import ekart.order_service.model.Order;
import ekart.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryClient inventoryClient;


    @Autowired
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) throws Exception {


       boolean productInStock =  inventoryClient.inStock(orderRequest.skuCode(), orderRequest.quantity());

       if(productInStock){
           Order order = new Order();
           order.setOrderNumber(UUID.randomUUID().toString());
           order.setPrice(orderRequest.price().multiply(BigDecimal.valueOf(orderRequest.quantity())));
           order.setSkuCode(orderRequest.skuCode());
           order.setQuantity(orderRequest.quantity());
           orderRepository.save(order);

           //send message to kafka topic
           OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), orderRequest.userDetails().email());
           log.info("Start - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
           kafkaTemplate.send("order-placed", orderPlacedEvent);
           log.info("End - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);


       }else {
           throw new Exception("Product with SKU Code " + orderRequest.skuCode() + " not in stock.");
       }




    }

}
