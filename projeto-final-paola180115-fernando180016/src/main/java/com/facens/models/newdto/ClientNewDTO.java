package com.facens.models.newdto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ClientNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotNull (message = "Name is required")
	private String name;
	
	private String address;
	
	@NotNull (message = "Contact is required")
	private String contact;
	
	@NotNull (message = "CPF is required")
	private String CPF;
	
	public ClientNewDTO() {}
	
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
	
	public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
}
