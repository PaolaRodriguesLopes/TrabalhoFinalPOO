package com.facens.models.dto;

import java.io.Serializable;

import com.facens.models.Product;

public class ProductDTO implements Serializable {

private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Integer quantitySku;
	private Integer usedQuantity;
	private Double price;
	
	private ProviderDTO provider;
	
	public ProductDTO() {}		
	public ProductDTO(Product product) {
		super();
		this.id = product.getId();
		this.name = product.getName();
		this.quantitySku = product.getQuantitySku();
		this.usedQuantity = product.getUsedQuantity();
		this.price = product.getPrice();
	}
		
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getQuantitySku() {
		return quantitySku;
	}
	
	public void setQuantitySku(Integer quantitySku) {
		this.quantitySku = quantitySku;
	}
	
	public Integer getUsedQuantity() {
		return usedQuantity;
	}
	
	public void setUsedQuantity(Integer usedQuantity) {
		this.usedQuantity = usedQuantity;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public ProviderDTO getProvider() {
		return provider;
	}
	
	public void setProvider(ProviderDTO provider) {
		this.provider = provider;
	}	
}
