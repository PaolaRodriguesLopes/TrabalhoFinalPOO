package com.facens.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facens.entity.Product;
import com.facens.entity.Provider;
import com.facens.entity.dto.ProductDTO;
import com.facens.entity.newdto.ProductNewDTO;
import com.facens.repository.IProductRepository;
import com.facens.repository.IProviderRepository;

@Service
public class ProductService {
	
	@Autowired
	private IProductRepository pr;
	
	@Autowired
	private IProviderRepository providerRepo;
	
	@Autowired
	private ProviderService ps;
	
	public List<Product> getProducts () {
		return pr.findAll ();
	}
	
	public List<Product> getProductsByProvider (Provider provider) {
		return pr.findByProvider (provider);
	}
	
	public List<Product> getProductsWhenQuantitySkuGreaterThanZero () {
		return pr.findByQuantitySkuGreaterThanZero();
	}
	
	public Product getProductById (Integer id) {
		Optional<Product> Product = pr.findById (id);
		return Product.orElseThrow (null);
	}
	
	public void deleteById (Integer id) {
		getProductById (id);
		pr.deleteById (id);
	}
	
	@Transactional
	public Product insert (Product p) {
		p.setId (null);
		p = pr.save (p);
		providerRepo.save (p.getProvider ());
		
		return p;
	}
	
	@Transactional
	public Product update (Product p) {
		Product newProduct = getProductById (p.getId ());
		updateData (p, newProduct);
		return pr.save (newProduct);
	}
	
	public Product fromDTO (ProductDTO dto) {
		Product p = new Product(dto.getId(), dto.getName(), dto.getQuantitySku(), dto.getUsedQuantity(), dto.getPrice(), null);
		p.setProvider (ps.fromDTO (dto.getProvider ()));
		return p;
	}
	
	public Product fromNewDTO (ProductNewDTO dto) {
		Product p = new Product ();
		
		p.setId (dto.getId ());
		p.setName (dto.getName ());
		
		Integer currentQuantitySku = (dto.getQuantitySku () - dto.getUsedQuantity ());
		p.setQuantitySku(currentQuantitySku > 0 ? currentQuantitySku : 0);
		p.setUsedQuantity(dto.getUsedQuantity());
		p.setPrice(dto.getPrice() > 0 ? dto.getPrice() : 0);
		
		Provider provider = ps.getProviderById (dto.getProviderId ());
		p.setProvider (provider);
		
		return p;
	}
	
	private void updateData (Product oldProduct, Product newProduct) {
		newProduct.setName (oldProduct.getName ());
		
		Integer currentQuantitySku = (oldProduct.getQuantitySku () - oldProduct.getUsedQuantity ());
		newProduct.setQuantitySku(currentQuantitySku > 0 ? currentQuantitySku : 0);
		newProduct.setUsedQuantity(oldProduct.getUsedQuantity());
		newProduct.setPrice(oldProduct.getPrice() > 0 ? oldProduct.getPrice() : 0);
	}
}
