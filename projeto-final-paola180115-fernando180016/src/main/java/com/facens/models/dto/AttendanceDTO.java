package com.facens.models.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.facens.models.Attendance;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AttendanceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm")
	private Timestamp datetime;
	private Set<ProfessionalDTO> professionals = new HashSet<>();
	private List<ProductDTO> products = new ArrayList<>();
	private ClientDTO client;
	
	public AttendanceDTO() {}
	public AttendanceDTO(Attendance attendance) {
		super();
		this.id = attendance.getId();
		this.datetime = attendance.getDatetime();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDatetime() {
		return datetime;
	}
	
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
	
	public Set<ProfessionalDTO> getProfessionals() {
		return professionals;
	}
	
	public void setProfessionals(Set<ProfessionalDTO> professionals) {
		this.professionals = professionals;
	}
	
	public List<ProductDTO> getProducts() {
		return products;
	}
	
	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	
	public ClientDTO getClient() {
		return client;
	}
	
	public void setClient(ClientDTO client) {
		this.client = client;
	}
}