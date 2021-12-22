package com.example.where2go.service.impl;

import com.example.where2go.converter.BookingConverter;
import com.example.where2go.entity.Booking;
import com.example.where2go.exceptions.ApiException;
import com.example.where2go.model.BookingModel;
import com.example.where2go.repository.BookingRepository;
import com.example.where2go.service.BookingService;
import com.example.where2go.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingConverter bookingConverter;

    @Autowired
    private UserService userService;


    @Override
    public BookingModel createBooking(BookingModel bookingModel) {
        if (bookingModel.getBookingTimeFrom() == null) throw new ApiException("Введите время бронирования от", HttpStatus.BAD_REQUEST);
        if (bookingModel.getBookingTimeTill() == null) throw new ApiException("Введите время бронирования до", HttpStatus.BAD_REQUEST);
        if (bookingModel.getTableId() == null) throw new ApiException("Введите столик", HttpStatus.BAD_REQUEST);
        List<Booking> bookings = bookingRepository.findBookingsByEstablishmentTableId(bookingModel.getTableId());
        for (Booking booking:bookings) {
            Booking bookingFromClient = bookingConverter.convertFromModel(bookingModel);
            if (booking.getBookingTimeFrom().isEqual(bookingFromClient.getBookingTimeFrom()) || booking.getBookingTimeTill().isEqual(bookingFromClient.getBookingTimeTill())) throw new ApiException("На это время уже есть бронь", HttpStatus.BAD_REQUEST);
        }
        bookingModel.setUserId(userService.getCurrentUser().getId());
        bookingRepository.save(bookingConverter.convertFromModel(bookingModel));
        return bookingModel;
    }

    @Override
    public List<BookingModel> getAll() {
        List<BookingModel> bookingModels = new ArrayList<>();
        for (Booking booking:bookingRepository.findAll()) {
            bookingModels.add(bookingConverter.convertFromEntity(booking));
        }
        if (bookingModels.isEmpty()) throw new ApiException("Список пуст", HttpStatus.BAD_REQUEST);
        return bookingModels;
    }

    @Override
    public BookingModel getById(Long id) {
        BookingModel bookingModel = bookingConverter.convertFromEntity(bookingRepository.findById(id).orElse(null));
        if (bookingModel == null) throw new ApiException("Не нашли бронь по id " + id, HttpStatus.BAD_REQUEST);
        return bookingModel;
    }

    @Override
    public BookingModel updateBooking(BookingModel bookingModel) {
        BookingModel bookingModelForUpdate = getById(bookingConverter.convertFromModel(bookingModel).getId());

        if (bookingModel.getBookingTimeFrom() != null) bookingModelForUpdate.setBookingTimeFrom(bookingModel.getBookingTimeFrom());
        if (bookingModel.getBookingTimeTill() != null) bookingModelForUpdate.setBookingTimeTill(bookingModel.getBookingTimeTill());
        if (bookingModel.getTableId() != null) bookingModelForUpdate.setTableId(bookingModel.getTableId());

        bookingRepository.save(bookingConverter.convertFromModel(bookingModelForUpdate));
        return bookingModelForUpdate;
    }

    @Override
    public BookingModel deleteById(Long id) {
        BookingModel bookingModelForDelete = getById(id);
        if (bookingModelForDelete != null) bookingRepository.delete(bookingConverter.convertFromModel(bookingModelForDelete));

        return bookingModelForDelete;
    }
}
