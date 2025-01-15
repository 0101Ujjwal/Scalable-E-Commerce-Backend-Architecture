package com.goods.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {

	private Long id;
	private String skucode;
	private Integer quantity;
	private long price;
}
