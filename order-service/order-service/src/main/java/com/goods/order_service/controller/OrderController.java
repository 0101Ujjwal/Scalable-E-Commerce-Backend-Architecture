package com.goods.order_service.controller;

import java.util.concurrent.CompletableFuture;

import org.apache.hc.client5.http.impl.Operations.CompletedFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goods.order_service.dto.OrderRequest;
import com.goods.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/apis/order")
public class OrderController {

    @Autowired
    OrderService service;

//    @PostMapping
//    @CircuitBreaker(name = "inventory", fallbackMethod = "inventoryFallback") // Use fallbackMethod for handling failures
//    @TimeLimiter(name="inventory")
//    @Retry(name="inventory")
//    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
//    	return CompletableFuture.supplyAsync(()->  service.placeOrder(orderRequest));
//       // return "Order placed successfully";
//    }
//
//    // Fallback method for circuit breaker
//    public CompletableFuture<String> inventoryFallback(OrderRequest orderRequest, Throwable throwable) {
//       return CompletableFuture.supplyAsync(()->"Oops! Inventory service is currently unavailable. Please try again later.");
//    	
//    }
    
    @PostMapping
//    @CircuitBreaker(name = "inventory", fallbackMethod = "inventoryFallback") // Use fallbackMethod for handling failures
//    @TimeLimiter(name="inventory")
//    @Retry(name="inventory")
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
    	 service.placeOrder(orderRequest);
       return "Order placed successfully";
    }
}
