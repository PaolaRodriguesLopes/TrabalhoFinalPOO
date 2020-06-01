package com.facens.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer quantitySku;
	private Integer usedQuantity;
	private Double price;
	
	@ManyToOne @JoinColumn (name = "provider_id")
	private Provider provider;
	
	public Product() {}		
	public Product(Integer id, String name, Integer quantitySku, Integer usedQuantity, Double price, Provider provider) {
		super();
		this.id = id;
		this.name = name;
		this.quantitySku = quantitySku;
		this.usedQuantity = usedQuantity;
		this.price = price;
		this.provider = provider;
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
	
	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
