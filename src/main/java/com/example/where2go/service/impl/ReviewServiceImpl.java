package com.example.where2go.service.impl;

import com.example.where2go.converter.ReviewConverter;
import com.example.where2go.entity.Establishment;
import com.example.where2go.entity.Review;
import com.example.where2go.exceptions.ApiException;
import com.example.where2go.model.ReviewModel;
import com.example.where2go.repository.ReviewRepository;
import com.example.where2go.service.ReviewService;
import com.example.where2go.service.UserService;
import com.example.where2go.specification.EstablishmentSpecification;
import com.example.where2go.specification.ReviewSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewConverter reviewConverter;

    @Autowired
    private UserService userService;

    @Override
    public ReviewModel createReview(ReviewModel reviewModel) {
        if (reviewModel.getReview().isEmpty()) throw new ApiException("Введите отзыв", HttpStatus.BAD_REQUEST);
        if (reviewModel.getEstablishmentId() == null) throw new ApiException("Такого заведения не существует", HttpStatus.BAD_REQUEST);
        reviewModel.setUserId(userService.getCurrentUser().getId());
        reviewRepository.save(reviewConverter.convertFromModel(reviewModel));
        return reviewModel;
    }

    @Override
    public Page<ReviewModel> getPage(Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findAll(pageable);
        if (reviewPage.getContent().isEmpty()) throw new ApiException("Список пуст", HttpStatus.BAD_REQUEST);
        return reviewPage.map(reviewConverter::convertFromEntity);
    }

    @Override
    public Page<ReviewModel> getPageSortedByEstablishmentId(Long id, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findAllByEstablishmentId(id, pageable);
        if (reviewPage.getContent().isEmpty()) throw new ApiException("Список пуст", HttpStatus.BAD_REQUEST);
        return reviewPage.map(reviewConverter::convertFromEntity);
    }

    @Override
    public Page<ReviewModel> getSortedPage(ReviewModel reviewModel, Pageable pageable) {
        ReviewSpecification reviewSpecification = new ReviewSpecification(reviewModel);
        Page<Review> reviewPage = reviewRepository.findAll(reviewSpecification, pageable);
        if (reviewPage.getContent().isEmpty()) throw new ApiException("Список пуст", HttpStatus.BAD_REQUEST);
        return reviewPage.map(reviewConverter::convertFromEntity);
    }


    @Override
    public ReviewModel getById(Long id) {
        ReviewModel reviewModel = reviewConverter.convertFromEntity(reviewRepository.findById(id).orElse(null));
        if (reviewModel == null) throw new ApiException("Не нашли отзыв по id " + id, HttpStatus.BAD_REQUEST);
        return reviewModel;
    }

    @Override
    public ReviewModel updateReview(ReviewModel reviewModel) {
        ReviewModel reviewModelForUpdate = getById(reviewConverter.convertFromModel(reviewModel).getId());

        if (reviewModel.getReview() != null) reviewModelForUpdate.setReview(reviewModel.getReview());
        if (reviewModel.getEstablishmentId() != null) reviewModelForUpdate.setEstablishmentId(reviewModel.getEstablishmentId());

        reviewRepository.save(reviewConverter.convertFromModel(reviewModelForUpdate));
        return reviewModelForUpdate;
    }

    @Override
    public ReviewModel deleteById(Long id) {
        ReviewModel reviewModelForDelete = getById(id);
        if (reviewModelForDelete != null) reviewRepository.delete(reviewConverter.convertFromModel(reviewModelForDelete));

        return reviewModelForDelete;
    }
}
