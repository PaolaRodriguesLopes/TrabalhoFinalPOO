package com.facens.entity.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.facens.entity.Attendance;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AttendanceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
	private String datetime;
	
	private String status;
	
	private Set<ProfessionalDTO> professionals = new HashSet<>();
	private List<ProductDTO> products = new ArrayList<>();
	private ClientDTO client;
	
	public AttendanceDTO() {}
	public AttendanceDTO(Attendance attendance) {
		super();
		this.id = attendance.getId();
		this.datetime = attendance.getDatetime().toString();
		this.status = attendance.getStatus();
		this.client = new ClientDTO (attendance.getClient ());
		this.professionals = attendance.getProfessionals ().stream().map(p -> new ProfessionalDTO(p)).collect(Collectors.toSet());
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDatetime() {
		return datetime;
	}
	
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
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