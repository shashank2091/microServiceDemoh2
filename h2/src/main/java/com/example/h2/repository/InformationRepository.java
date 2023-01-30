package com.example.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.h2.model.Information;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {

}
