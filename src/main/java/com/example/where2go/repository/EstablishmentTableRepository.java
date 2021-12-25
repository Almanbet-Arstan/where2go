package com.example.where2go.repository;

import com.example.where2go.entity.EstablishmentTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EstablishmentTableRepository extends JpaRepository<EstablishmentTable, Long> {
    List<EstablishmentTable> findEstablishmentTablesByEstablishmentId(Long id);
}
