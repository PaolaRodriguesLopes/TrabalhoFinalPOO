package com.facens.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facens.entity.Attendance;
import com.facens.entity.Client;
import com.facens.entity.Product;
import com.facens.entity.Professional;
import com.facens.entity.dto.AttendanceDTO;
import com.facens.entity.dto.ProductDTO;
import com.facens.entity.dto.ProfessionalDTO;
import com.facens.entity.newdto.AttendanceNewDTO;
import com.facens.repository.IAttendanceRepository;
import com.facens.repository.IClientRepository;
import com.facens.repository.IProfessionalRepository;

@Service
public class AttendanceService {
	
	@Autowired
	private IAttendanceRepository ar;
	
	@Autowired
	private IProfessionalRepository pr;
	
	@Autowired
	private IClientRepository cr;
	
	@Autowired
	private ClientService cs;
	
	@Autowired
	private ProfessionalService ps;
	
	@Autowired
	private ProductService productService;
	
	public List<Attendance> getAttendances () {
		return ar.findAll ();
	}
	
	public List<Attendance> getAttendancesByProfessional (Professional p) {
		return ar.findAllByProfessionalsContaining (p);
	}
	
	public List<Attendance> getAttendancesByClient (Client c) {
		return ar.findAllByClient (c);
	}
	
	public Attendance getAttendanceById (Integer id) {
		Optional<Attendance> att = ar.findById (id);
		return att.orElseThrow (null);
	}
	
	public void deleteById (Integer id) {
		Attendance att = getAttendanceById (id);
		
		for (Professional p : att.getProfessionals())
		{
			p.getAttendances().removeIf(other -> other.getId().equals(id));
			pr.save (p);
		}
		
		Client client = att.getClient();
		client.getAttendances().removeIf(other -> other.getId().equals(id));
		cr.save(client);
		
		ar.deleteById (id);		
	}
	
	@Transactional
	public Attendance insert (Attendance att) {
		att.setId (null);
		att = ar.save (att);
		for (Professional p : att.getProfessionals())
		{
			p.getAttendances().add(att);
			pr.save (p);
		}
		
		cr.save (att.getClient ());
		
		return att;
	}
	
	@Transactional
	public Attendance update (Attendance att) {
		Attendance newAttendance = getAttendanceById (att.getId ());
		newAttendance.setStatus("Finalizado");
		newAttendance.setProductsIds (att.getProductsIds ());		
		newAttendance = ar.save (newAttendance);		
		return newAttendance;
	}
	
	@Transactional
	public Attendance cancelAttendance (Attendance att) {
		att.setStatus("Cancelado");
		return ar.save (att);
	}
	
	public Attendance fromDTO (AttendanceDTO dto) {
		Attendance att = new Attendance(dto.getId(), dto.getDatetime(), null);
		
		Client c = cs.fromDTO(dto.getClient ());
		att.setClient (c);
		
		for (ProfessionalDTO pDto : dto.getProfessionals ()) {
			Professional p = ps.fromDTO (pDto);
			att.getProfessionals().add(p);
		}
		
		for (ProductDTO pDto : dto.getProducts()) {
			Product p = productService.fromDTO(pDto);
			att.getProductsIds().add(p.getId());
		}
				
		return att;
	}
	
	public Attendance fromNewDTO (AttendanceNewDTO dto) {
		Attendance att = new Attendance ();
		
		att.setId (dto.getId ());
		
		String datetime = dto.getDatetime ();
		datetime = datetime.replaceAll("T", " ");
		att.setDatetime (Timestamp.valueOf (datetime));
		
		Client c = cs.getClientById (dto.getClientId ());
		att.setClient (c);
		
		for (Integer id : dto.getProfessionalIds ()) 
		{
			Professional p = ps.getProfessionalById (id);
			att.getProfessionals ().add (p);
		}
				
		for (Integer id : dto.getProductIds()) {
			Product p = productService.getProductById(id);
			att.getProductsIds().add(p.getId());
		}
				
		return att;
	}
}
