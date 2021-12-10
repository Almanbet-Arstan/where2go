package com.example.where2go.controller;

import com.example.where2go.model.ReviewModel;
import com.example.where2go.model.ReviewModel;
import com.example.where2go.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public Page<ReviewModel> getPage(Pageable pageable){
        return reviewService.getPage(pageable);
    }

    @PostMapping("/pageable")
    public Page<ReviewModel> getSortedPage(@RequestBody ReviewModel reviewModel, Pageable pageable) {
        return reviewService.getSortedPage(reviewModel, pageable);
    }

    @GetMapping("/{id}")
    public ReviewModel getById(@PathVariable Long id){
        return reviewService.getById(id);
    }

    @PostMapping
    public ReviewModel createUserRole(@RequestBody ReviewModel reviewModel){
        return reviewService.createReview(reviewModel);
    }

    @PutMapping
    public ReviewModel updateUserRole(@RequestBody ReviewModel reviewModel){
        return reviewService.updateReview(reviewModel);
    }

    @DeleteMapping("/{id}")
    public ReviewModel deleteById(@PathVariable Long id){
        return reviewService.deleteById(id);
    }
}
