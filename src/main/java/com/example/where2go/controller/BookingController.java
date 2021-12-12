package com.example.where2go.controller;

import com.example.where2go.model.BookingModel;
import com.example.where2go.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<BookingModel> getAll(){
        return bookingService.getAll();
    }

    @GetMapping("/{id}")
    public BookingModel getById(@PathVariable Long id){
        return bookingService.getById(id);
    }

    @PostMapping
    public BookingModel createCategory(@RequestBody BookingModel categoryModel){
        return bookingService.createBooking(categoryModel);
    }

    @PutMapping
    public BookingModel updateCategory(@RequestBody BookingModel categoryModel){
        return bookingService.updateBooking(categoryModel);
    }

    @DeleteMapping("/{id}")
    public BookingModel deleteById(@PathVariable Long id){
        return bookingService.deleteById(id);
    }
}
