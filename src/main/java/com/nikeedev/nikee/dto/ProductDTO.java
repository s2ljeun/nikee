package com.nikeedev.nikee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
	private	int prod_no;
	private String prod_name;
	private int	prod_price;
	private int prod_cost;
	private int prod_income;
	private String prod_regdate;
	private int prod_sale;
	private int	prod_amount;
	private int	cate_no;
	private String prod_img;
}
