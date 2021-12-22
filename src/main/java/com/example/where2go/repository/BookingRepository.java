package com.example.where2go.repository;

import com.example.where2go.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingsByEstablishmentTableId(Long id);
}
