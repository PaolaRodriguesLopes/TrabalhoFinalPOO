package com.facens.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facens.entity.Client;
import com.facens.entity.dto.ClientDTO;
import com.facens.entity.newdto.ClientNewDTO;
import com.facens.repository.IClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private IClientRepository cr;
	
	public List<Client> getClients () {
		return cr.findAll ();
	}
	
	public Client getClientById (Integer id) {
		Optional<Client> client = cr.findById (id);
		return client.orElseThrow (null);
	}
	
	public void deleteById (Integer id) {
		getClientById (id);
		cr.deleteById (id);
	}
	
	@Transactional
	public Client insert (Client client) {
		client.setId (null);
		client = cr.save (client);
		
		return client;
	}
	
	@Transactional
	public Client update (Client client) {
		Client newClient = getClientById (client.getId ());
		updateData (client, newClient);
		return cr.save (newClient);
	}
	
	public Client fromDTO (ClientDTO dto) {
		Client c = new Client (dto.getId (), dto.getName (), dto.getAddress (), dto.getContact (), dto.getCPF ());
		return c;
	}
	
	public Client fromNewDTO (ClientNewDTO dto) {
		Client c = new Client ();
		
		c.setId (dto.getId ());
		c.setName (dto.getName ());
		c.setCPF (dto.getCPF ());
		c.setAddress (dto.getAddress ());
		c.setContact (dto.getContact ());
		c.setAttendances (null);
		
		return c;
	}
	
	private void updateData (Client oldClient, Client newClient) {
		newClient.setName (oldClient.getName ());
		newClient.setAddress (oldClient.getAddress ());
		newClient.setContact (oldClient.getContact ());
		newClient.setCPF (oldClient.getCPF ());
	}
}
