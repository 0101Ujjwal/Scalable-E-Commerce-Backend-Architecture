package com.goods.inventory_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goods.inventory_service.model.Inventory;

@Repository
public interface Inventory_Repo extends JpaRepository<Inventory, Long> {

	public List<Inventory> findBySkucodeIn(List<String> skucode);
}
