package com.facens.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Provider implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String address;
	private String contact;
	private String CNPJ;
	
	@JsonIgnore @OneToMany (mappedBy = "provider")
	private Set<Product> products = new HashSet<>();
	
	public Provider() {}
	public Provider(Integer id, String name, String address, String contact, String CNPJ) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.CNPJ = CNPJ;
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
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getCNPJ() {
		return CNPJ;
	}
	
	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}
	
	public Set<Product> getProducts() {
		return products;
	}
	
	public void setProducts(Set<Product> products) {
		this.products = products;
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
		Provider other = (Provider) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
