package com.facens.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Attendance implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	private Timestamp datetime;
	
	@ManyToMany (mappedBy = "attendances")
	private Set<Professional> professionals = new HashSet<>();
	
	@ElementCollection @CollectionTable (name = "used_products")
	private List<Integer> productsIds = new ArrayList<>();
	
	@ManyToOne (cascade = CascadeType.ALL) @JoinColumn (name = "client_id")
	private Client client;
	
	public Attendance() {}
	public Attendance(Integer id, Timestamp datetime, Client client) {
		super();
		this.id = id;
		this.datetime = datetime;
		this.client = client;
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
	
	public Set<Professional> getProfessionals() {
		return professionals;
	}
	
	public void setProfessionals(Set<Professional> professionals) {
		this.professionals = professionals;
	}
	
	public List<Integer> getProductsIds() {
		return productsIds;
	}
	
	public void setProductsIds(List<Integer> products) {
		this.productsIds = products;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
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
		Attendance other = (Attendance) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}