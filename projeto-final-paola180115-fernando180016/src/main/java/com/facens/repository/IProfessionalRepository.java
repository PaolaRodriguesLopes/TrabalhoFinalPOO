package com.facens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facens.entity.Professional;

@Repository
public interface IProfessionalRepository extends JpaRepository<Professional, Integer> {

}
