package com.example.where2go.service;

import com.example.where2go.model.BookingModel;

import java.util.List;

public interface BookingService {
    BookingModel createBooking(BookingModel bookingModel);
    List<BookingModel> getAll();
    BookingModel getById(Long id);
    BookingModel updateBooking(BookingModel bookingModel);
    BookingModel deleteById(Long id);
}
