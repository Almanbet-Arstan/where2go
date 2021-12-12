package com.example.where2go.converter;

import com.example.where2go.entity.Booking;
import com.example.where2go.entity.EstablishmentTable;
import com.example.where2go.entity.User;
import com.example.where2go.model.BookingModel;
import org.springframework.stereotype.Component;

@Component
public class BookingConverter extends BaseConverter<BookingModel, Booking>{
    public BookingConverter() {
        super(BookingConverter::convertToEntity, BookingConverter::convertToModel);
    }

    private static BookingModel convertToModel(Booking entityToConvert){
        if (entityToConvert == null) return null;
        return BookingModel.builder()
                .bookingTime(entityToConvert.getBookingTime())
                .tableId(entityToConvert.getEstablishmentTable().getId())
                .userId(entityToConvert.getUser().getId())
                .build();
    }

    private static Booking convertToEntity(BookingModel modelToConvert){
        if (modelToConvert == null) return null;

        Booking bookingToReturn = new Booking();

        bookingToReturn.setBookingTime(modelToConvert.getBookingTime());

        EstablishmentTable establishmentTable = new EstablishmentTable();
        establishmentTable.setId(modelToConvert.getTableId());

        bookingToReturn.setEstablishmentTable(establishmentTable);

        User user = new User();
        user.setId(modelToConvert.getUserId());

        bookingToReturn.setUser(user);

        return bookingToReturn;
    }
}
