package com.facens.models.dto;

import java.io.Serializable;

import com.facens.models.Provider;

public class ProviderDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String address;
	private String contact;
	private String CNPJ;
	
	public ProviderDTO() {}
	public ProviderDTO(Provider provider) {
		super();
		this.id = provider.getId();
		this.name = provider.getName();
		this.address = provider.getAddress();
		this.contact = provider.getContact();
		this.CNPJ = provider.getCNPJ();
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
}
