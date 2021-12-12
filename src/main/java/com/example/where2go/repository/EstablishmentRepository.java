package com.example.where2go.repository;

import com.example.where2go.entity.Establishment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface EstablishmentRepository extends JpaRepository<Establishment, Long>, JpaSpecificationExecutor<Establishment> {
    Page<Establishment> findAllByCategoryId(Long id, Pageable pageable);
}
