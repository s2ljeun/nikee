package com.nikeedev.nikee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
	private int order_no;
	private String order_regdate;
	private int order_price;
	private int order_cost;
	private int order_income;
	private String order_name;
	private String order_addr;
	private int mem_no;
}
