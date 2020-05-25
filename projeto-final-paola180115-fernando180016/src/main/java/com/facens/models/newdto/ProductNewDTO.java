package com.facens.models.newdto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ProductNewDTO implements Serializable {

private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull (message = "Name is required")
	private String name;
	
	@NotNull (message = "Quantity SKU is required")
	private Integer quantitySku;
	
	@NotNull (message = "Used Quantity is required")
	private Integer usedQuantity;
	
	@NotNull (message = "Price is required")
	private Double price;
	
	@NotNull (message = "Provider is required")
	private Integer providerId;
	
	public ProductNewDTO() {}
		
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
	
	public Integer getProviderId() {
		return providerId;
	}
	
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}	
}
