package com.facens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facens.entity.Provider;

@Repository
public interface IProviderRepository extends JpaRepository<Provider, Integer> {

}
