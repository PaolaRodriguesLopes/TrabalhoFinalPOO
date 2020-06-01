package com.facens.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facens.entity.Provider;
import com.facens.entity.dto.ProviderDTO;
import com.facens.entity.newdto.ProviderNewDTO;
import com.facens.repository.IProviderRepository;

@Service
public class ProviderService {
	
	@Autowired
	private IProviderRepository pr;
	
	public List<Provider> getProviders () {
		return pr.findAll ();
	}
	
	public Provider getProviderById (Integer id) {
		Optional<Provider> Provider = pr.findById (id);
		return Provider.orElseThrow (null);
	}
	
	public void deleteById (Integer id) {
		getProviderById (id);
		pr.deleteById (id);
	}
	
	@Transactional
	public Provider insert (Provider Provider) {
		Provider.setId (null);
		Provider = pr.save (Provider);
		
		return Provider;
	}
	
	@Transactional
	public Provider update (Provider Provider) {
		Provider newProvider = getProviderById (Provider.getId ());
		updateData (Provider, newProvider);
		return pr.save (newProvider);
	}
	
	public Provider fromDTO (ProviderDTO dto) {
		Provider p = new Provider(dto.getId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getCNPJ());
		return p;
	}
	
	public Provider fromNewDTO (ProviderNewDTO dto) {
		Provider p = new Provider ();
		
		p.setId (dto.getId ());
		p.setName (dto.getName ());
		p.setCNPJ (dto.getCNPJ ());
		p.setAddress (dto.getAddress ());
		p.setContact (dto.getContact ());
		p.setProducts (null);
		
		return p;
	}
	
	private void updateData (Provider oldProvider, Provider newProvider) {
		newProvider.setName (oldProvider.getName ());
		newProvider.setAddress (oldProvider.getAddress ());
		newProvider.setContact (oldProvider.getContact ());
		newProvider.setCNPJ (oldProvider.getCNPJ ());
	}
}
