package com.facens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facens.entity.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Integer> {

}
