package com.facens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.facens.entity.Attendance;
import com.facens.entity.Client;
import com.facens.entity.Professional;

@Repository
public interface IAttendanceRepository extends JpaRepository<Attendance, Integer> {

	@Transactional (readOnly = true)
	public List<Attendance> findAllByProfessionalsContaining (Professional professional);
	
	@Transactional (readOnly = true)
	public List<Attendance> findAllByClient (Client client);
	
}
