package com.goods.inventory_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goods.inventory_service.dto.InventoryResponse;
import com.goods.inventory_service.model.Inventory;
import com.goods.inventory_service.repository.Inventory_Repo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Inventory_Service {

	@Autowired
	private Inventory_Repo repository;
	
	
	
	 @Transactional(readOnly = true)
	 @SneakyThrows
	public List<InventoryResponse> CheckIsInStock(List<String> skucode) {
	log.info("wait started");
	Thread.sleep(10000);
	log.info("wait Ended");
		
		List<InventoryResponse> inventoryResponse = new ArrayList<>();
		
		List<Inventory> inventories = this.repository.findBySkucodeIn(skucode);
		
		for(Inventory inventory : inventories) {
			
			boolean isInstock = inventory.getQuantity() > 0;
			
			InventoryResponse response = new InventoryResponse(inventory.getSkucode() , isInstock);
			
			inventoryResponse.add(response);
		}
	    
		return inventoryResponse;
	}
}
