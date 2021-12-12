package com.example.where2go.service;

import com.example.where2go.model.ReviewModel;
import com.example.where2go.model.ReviewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    ReviewModel createReview(ReviewModel reviewModel);
    Page<ReviewModel> getPage(Pageable pageable);
    Page<ReviewModel> getPageSortedByEstablishmentId(Long id, Pageable pageable);
    Page<ReviewModel> getSortedPage(ReviewModel reviewModel, Pageable pageable);
    ReviewModel getById(Long id);
    ReviewModel updateReview(ReviewModel reviewModel);
    ReviewModel deleteById(Long id);
}
