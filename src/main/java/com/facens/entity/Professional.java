package com.facens.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Professional implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String address;
	private String contact;
	private String CPF;
	
	@ManyToMany
	@JoinTable (name = "professional_attendances", joinColumns = @JoinColumn (name = "professional_id"), inverseJoinColumns = @JoinColumn (name = "attendance_id"))
	private Set<Attendance> attendances = new HashSet<>();
	
	@ElementCollection (fetch = FetchType.EAGER) @CollectionTable (name = "roles")
	private Set<String> roles = new HashSet<String>();
	
	public Professional() {}
	public Professional(Integer id, String name, String address, String contact, String CPF) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.CPF = CPF;
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
	
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	
	public Set<Attendance> getAttendances() {
		return attendances;
	}
	
	public void setAttendances(Set<Attendance> attendances) {
		this.attendances = attendances;
	}
	
	public Set<String> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<String> roles) {
		this.roles = roles;
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
		Professional other = (Professional) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}