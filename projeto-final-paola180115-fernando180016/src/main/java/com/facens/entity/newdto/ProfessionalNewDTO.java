package com.facens.entity.newdto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProfessionalNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotNull (message = "Name is required")
	private String name;
	
	private String address;
	
	@NotNull (message = "Contact is required")
	private String contact;
	
	@NotNull (message = "CPF is required")
	private String CPF;

	@NotNull (message = "At least one role is required")
	@NotEmpty (message = "At least one role is required")
	private Set<String> roles = new HashSet<String>();
	
	public ProfessionalNewDTO() {}

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
	
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	
	public Set<String> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
