package com.facens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.facens.entity.Product;
import com.facens.entity.Provider;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
	
	@Transactional (readOnly = true)
	public List<Product> findByProvider (Provider provider);
}
