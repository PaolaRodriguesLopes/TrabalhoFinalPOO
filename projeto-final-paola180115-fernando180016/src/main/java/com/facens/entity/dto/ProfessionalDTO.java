package com.facens.entity.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.facens.entity.Professional;

public class ProfessionalDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String address;
	private String contact;
	private String CPF;

	private Set<String> roles = new HashSet<String>();
	
	public ProfessionalDTO() {}
	public ProfessionalDTO(Professional professional) {
		super();
		this.id = professional.getId();
		this.name = professional.getName();
		this.address = professional.getAddress();
		this.contact = professional.getContact();
		this.address = professional.getCPF();
		this.roles = professional.getRoles();
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
