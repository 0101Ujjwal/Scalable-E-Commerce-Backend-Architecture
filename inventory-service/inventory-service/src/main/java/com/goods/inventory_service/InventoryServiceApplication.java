package com.goods.inventory_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.goods.inventory_service.model.Inventory;
import com.goods.inventory_service.repository.Inventory_Repo;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(Inventory_Repo inventoryRepo) {
	    return args -> {
	        Inventory iv1 = new Inventory();
	        iv1.setSkucode("phone");
	        iv1.setQuantity(100);
	        
	        Inventory iv2 = new Inventory();
	        iv2.setSkucode("watch");
	        iv2.setQuantity(100);
	        
	        inventoryRepo.save(iv1);
	        inventoryRepo.save(iv2);
	    };
	} 

}
