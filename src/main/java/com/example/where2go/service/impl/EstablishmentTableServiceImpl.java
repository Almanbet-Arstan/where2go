package com.example.where2go.service.impl;

import com.example.where2go.converter.BookingConverter;
import com.example.where2go.converter.EstablishmentTableConverter;
import com.example.where2go.entity.Booking;
import com.example.where2go.entity.Establishment;
import com.example.where2go.entity.EstablishmentTable;
import com.example.where2go.exceptions.ApiException;
import com.example.where2go.model.EstablishmentTableModel;
import com.example.where2go.model.EstablishmentTableModel;
import com.example.where2go.repository.BookingRepository;
import com.example.where2go.repository.EstablishmentRepository;
import com.example.where2go.repository.EstablishmentTableRepository;
import com.example.where2go.service.EstablishmentTableService;
import com.example.where2go.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstablishmentTableServiceImpl implements EstablishmentTableService {

    @Autowired
    private EstablishmentTableRepository establishmentTableRepository;

    @Autowired
    private EstablishmentTableConverter establishmentTableConverter;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private UserService userService;


    @Override
    public EstablishmentTableModel createTable(EstablishmentTableModel tableModel) {
        if (tableModel.getEstablishmentId() == null) throw new ApiException("Введите заведение", HttpStatus.BAD_REQUEST);
        if (tableModel.getTableNumber() == null) throw new ApiException("Введите номер столика", HttpStatus.BAD_REQUEST);
        if (tableModel.getSeats() == null) throw new ApiException("Введите количество посадочных мест", HttpStatus.BAD_REQUEST);
        Establishment establishment = establishmentRepository.findEstablishmentById(tableModel.getEstablishmentId()).orElse(null);
        if (!establishment.getUser().getId().equals(userService.getCurrentUser().getId())) throw new ApiException("Вы не можете вносить изменения в это заведение", HttpStatus.BAD_REQUEST);;
        List<EstablishmentTable> establishmentTables = establishmentTableRepository.findEstablishmentTablesByEstablishmentId(tableModel.getEstablishmentId());
        for (EstablishmentTable establishmentTable:establishmentTables) {
            if (tableModel.getTableNumber().equals(establishmentTable.getTableNumber())) throw new ApiException("Такой стол с таким номером уже существует", HttpStatus.BAD_REQUEST);
        }
        establishmentTableRepository.save(establishmentTableConverter.convertFromModel(tableModel));
        return tableModel;
    }

    @Override
    public List<EstablishmentTableModel> getAll() {
        List<EstablishmentTableModel> tableModels = new ArrayList<>();
        for (EstablishmentTable establishmentTable:establishmentTableRepository.findAll()) {
            tableModels.add(establishmentTableConverter.convertFromEntity(establishmentTable));
        }
        if (tableModels.isEmpty()) throw new ApiException("Список пуст", HttpStatus.BAD_REQUEST);
        return tableModels;
    }

    @Override
    public EstablishmentTableModel getById(Long id) {
        EstablishmentTableModel tableModel = establishmentTableConverter.convertFromEntity(establishmentTableRepository.findById(id).orElse(null));
        if (tableModel == null) throw new ApiException("Не нашли столик по id " + id, HttpStatus.BAD_REQUEST);
        return tableModel;
    }

    @Override
    public EstablishmentTableModel updateTable(EstablishmentTableModel tableModel) {
        EstablishmentTableModel tableModelForUpdate = getById(establishmentTableConverter.convertFromModel(tableModel).getId());

        if (tableModel.getEstablishmentId() != null) tableModelForUpdate.setEstablishmentId(tableModel.getEstablishmentId());
        if (tableModel.getTableNumber() != null) tableModelForUpdate.setTableNumber(tableModel.getTableNumber());
        if (tableModel.getSeats() != null) tableModelForUpdate.setSeats(tableModel.getSeats());

        establishmentTableRepository.save(establishmentTableConverter.convertFromModel(tableModelForUpdate));
        return tableModelForUpdate;
    }

    @Override
    public EstablishmentTableModel deleteById(Long id) {
        EstablishmentTableModel tableModelForDelete = getById(id);
        if (tableModelForDelete != null) establishmentTableRepository.delete(establishmentTableConverter.convertFromModel(tableModelForDelete));

        return tableModelForDelete;
    }
}
