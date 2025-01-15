package com.goods.inventory_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goods.inventory_service.dto.InventoryResponse;
import com.goods.inventory_service.service.Inventory_Service;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	Inventory_Service service;
	
	@GetMapping()
	  public List<InventoryResponse> InStock(@RequestParam List<String> skucode) {
		
		return service.CheckIsInStock(skucode);
		
		
	}
}
