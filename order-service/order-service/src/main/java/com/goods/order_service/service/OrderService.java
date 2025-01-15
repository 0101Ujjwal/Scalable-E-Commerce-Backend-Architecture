package com.goods.order_service.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.goods.order_service.dto.InventoryResponse;
import com.goods.order_service.dto.OrderLineItemsDto;
import com.goods.order_service.dto.OrderRequest;
import com.goods.order_service.model.Order;
import com.goods.order_service.model.OrderLineItems;
import com.goods.order_service.repository.OrderRepo;

@Service
public class OrderService {

    @Autowired
    OrderRepo repository;
    
    @Autowired
    private WebClient.Builder webclient;
    
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        
        // Set a random order number
        order.setOrdernumber(UUID.randomUUID().toString());

        // Ensure the list of OrderLineItemsDtos is not null before processing it
        List<OrderLineItemsDto> lineItemsDtos = orderRequest.getOrderLineItemsDtos();
        
        // Initialize the list for OrderLineItems entities
        List<OrderLineItems> orderLineItems = new ArrayList<>();

        // Use a traditional for-loop to process the items
        if (lineItemsDtos != null) {
            for (OrderLineItemsDto itemDto : lineItemsDtos) {
                OrderLineItems item = mapToEntity(itemDto);
                orderLineItems.add(item);
            }
        }
        
        order.setOrderLineItems(orderLineItems);
        
        //Fetch all skucode from orderlineitems
        List<String> skucodes = new ArrayList<>();
        for(OrderLineItems orderitems : orderLineItems) {
        	
        	skucodes.add(orderitems.getSkucode());
        }
        
        InventoryResponse[] inventoryResponses = webclient.build().get()
        		                               .uri("http://inventory-service/api/inventory",
        		                                uriBuilder -> uriBuilder.queryParam("skucode", skucodes).build())
        		                               .retrieve()
        		                               .bodyToMono(InventoryResponse[].class)
        		                               .block();
        
        boolean allproductinstock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInstock);

        if(allproductinstock) {
      

        // Save the order
        this.repository.save(order);
        
        return "Order place Sucessful";
        }
        else {
        	throw new IllegalArgumentException("Product is not in stcok");
        }
    }

    // Helper method to map OrderLineItemsDto to OrderLineItems entity
    public OrderLineItems mapToEntity(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems items = new OrderLineItems();
        items.setSkucode(orderLineItemsDto.getSkucode());
        items.setPrice(orderLineItemsDto.getPrice());
        items.setQuantity(orderLineItemsDto.getQuantity());
        return items;
    }
}
