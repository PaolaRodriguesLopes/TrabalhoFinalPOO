package com.facens.models.newdto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ProviderNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotNull (message = "Name is required")
	private String name;
	
	@NotNull (message = "Address is required")
	private String address;
	
	@NotNull (message = "Contact is required")
	private String contact;
	
	@NotNull (message = "CNPJ is required")
	private String CNPJ;
	
	public ProviderNewDTO() {}
		
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
