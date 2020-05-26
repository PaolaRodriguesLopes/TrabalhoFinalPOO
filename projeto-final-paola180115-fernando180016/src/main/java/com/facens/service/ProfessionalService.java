package com.facens.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facens.entity.Professional;
import com.facens.entity.dto.ProfessionalDTO;
import com.facens.entity.newdto.ProfessionalNewDTO;
import com.facens.repository.IProfessionalRepository;

@Service
public class ProfessionalService {
	
	@Autowired
	private IProfessionalRepository pr;
	
	public List<Professional> getProfessionals () {
		return pr.findAll ();
	}
	
	public Professional getProfessionalById (Integer id) {
		Optional<Professional> professional = pr.findById (id);
		return professional.orElseThrow (null);
	}
	
	public void deleteById (Integer id) {
		getProfessionalById (id);
		pr.deleteById (id);
	}
	
	@Transactional
	public Professional insert (Professional professional) {
		professional.setId (null);
		professional = pr.save (professional);
		
		return professional;
	}
	
	@Transactional
	public Professional update (Professional professional) {
		Professional newProfessional = getProfessionalById (professional.getId ());
		updateData (professional, newProfessional);
		return pr.save (newProfessional);
	}
	
	public Professional fromDTO (ProfessionalDTO dto) {
		Professional p = new Professional (dto.getId (), dto.getName (), dto.getAddress (), dto.getContact (), dto.getCPF ());
		p.setRoles (dto.getRoles ());
		return p;
	}
	
	public Professional fromNewDTO (ProfessionalNewDTO dto) {
		Professional professional = new Professional ();
		
		professional.setId (dto.getId ());
		professional.setName (dto.getName ());
		professional.setCPF (dto.getCPF ());
		professional.setAddress (dto.getAddress ());
		professional.setContact (dto.getContact ());
		professional.setAttendances (null);
		professional.setRoles (dto.getRoles ());
		
		return professional;
	}
	
	private void updateData (Professional oldProfessional, Professional newProfessional) {
		newProfessional.setName (oldProfessional.getName ());
		newProfessional.setAddress (oldProfessional.getAddress ());
		newProfessional.setContact (oldProfessional.getContact ());
		newProfessional.setCPF (oldProfessional.getCPF ());
	}
}
