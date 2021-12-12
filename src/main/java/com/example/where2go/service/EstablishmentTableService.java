package com.example.where2go.service;

import com.example.where2go.model.EstablishmentTableModel;

import java.util.List;

public interface EstablishmentTableService {
    EstablishmentTableModel createTable(EstablishmentTableModel tableModel);
    List<EstablishmentTableModel> getAll();
    EstablishmentTableModel getById(Long id);
    EstablishmentTableModel updateTable(EstablishmentTableModel tableModel);
    EstablishmentTableModel deleteById(Long id);
}
