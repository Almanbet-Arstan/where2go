package com.example.where2go.converter;

import com.example.where2go.entity.Booking;
import com.example.where2go.entity.EstablishmentTable;
import com.example.where2go.entity.User;
import com.example.where2go.model.BookingModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class BookingConverter extends BaseConverter<BookingModel, Booking>{
    public BookingConverter() {
        super(BookingConverter::convertToEntity, BookingConverter::convertToModel);
    }

    private static BookingModel convertToModel(Booking entityToConvert){
        if (entityToConvert == null) return null;
        String bookingTimeFrom = String.valueOf(entityToConvert.getBookingTimeFrom());
        String bookingTimeTill = String.valueOf(entityToConvert.getBookingTimeTill());
        return BookingModel.builder()
                .bookingTimeFrom(bookingTimeFrom)
                .bookingTimeTill(bookingTimeTill)
                .tableId(entityToConvert.getEstablishmentTable().getId())
                .userId(entityToConvert.getUser().getId())
                .build();
    }

    private static Booking convertToEntity(BookingModel modelToConvert){
        if (modelToConvert == null) return null;

        Booking bookingToReturn = new Booking();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime bookingTimeFrom = LocalDateTime.parse(modelToConvert.getBookingTimeFrom(), dateTimeFormatter);
        LocalDateTime bookingTimeTill = LocalDateTime.parse(modelToConvert.getBookingTimeTill(), dateTimeFormatter);

        bookingToReturn.setBookingTimeFrom(bookingTimeFrom);
        bookingToReturn.setBookingTimeTill(bookingTimeTill);

        EstablishmentTable establishmentTable = new EstablishmentTable();
        establishmentTable.setId(modelToConvert.getTableId());

        bookingToReturn.setEstablishmentTable(establishmentTable);

        User user = new User();
        user.setId(modelToConvert.getUserId());

        bookingToReturn.setUser(user);

        return bookingToReturn;
    }
}
