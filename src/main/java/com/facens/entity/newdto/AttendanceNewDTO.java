package com.facens.entity.newdto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AttendanceNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	//@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@NotNull (message = "Datetime is required")
	private String datetime;
	
	@NotNull (message = "At least one professional is required")
	@NotEmpty (message = "At least one professional is required")
	private Set<Integer> professionalIds = new HashSet<>();
	
	@NotNull (message = "At least one product is required")
	@NotEmpty (message = "At least one product is required")
	private List<Integer> productIds = new ArrayList<>();
	
	@NotNull (message = "At least one used quantity for product is required")
	@NotEmpty (message = "At least one used quantity for product is required")
	private List<Integer> usedQuantities = new ArrayList<>();
	
	@NotNull (message = "Client is required")
	private Integer clientId;
	
	public AttendanceNewDTO() {}

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

	public Set<Integer> getProfessionalIds() {
		return professionalIds;
	}

	public void setProfessionalIds(Set<Integer> professionalIds) {
		this.professionalIds = professionalIds;
	}

	public List<Integer> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}
	
	public List<Integer> getUsedQuantities() {
		return usedQuantities;
	}
	
	public void setUsedQuantities(List<Integer> usedQuantities) {
		this.usedQuantities = usedQuantities;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
}