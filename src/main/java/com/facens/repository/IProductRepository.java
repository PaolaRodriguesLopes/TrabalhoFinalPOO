package com.facens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.facens.entity.Product;
import com.facens.entity.Provider;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
	
	@Transactional (readOnly = true)
	public List<Product> findByProvider (Provider provider);
	
	@Transactional (readOnly = true)
	@Query ("SELECT p from Product p where p.quantitySku > 0")
	public List<Product> findByQuantitySkuGreaterThanZero ();
	
	@Transactional (readOnly = true)
	@Query (value = "SELECT COUNT(*) FROM USED_PRODUCTS up WHERE up.PRODUCTS_IDS = :id ", nativeQuery = true)
	public Integer getUsedProductsCountByProductId (@Param ("id") Integer productId);
}
