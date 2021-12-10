package com.example.where2go.converter;

import com.example.where2go.entity.Establishment;
import com.example.where2go.entity.Review;
import com.example.where2go.entity.User;
import com.example.where2go.model.ReviewModel;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter extends BaseConverter<ReviewModel, Review>{
    public ReviewConverter() {
        super(ReviewConverter::convertToEntity, ReviewConverter::convertToModel);
    }

    private static ReviewModel convertToModel(Review entityToConvert){
        if (entityToConvert == null) return null;
        return ReviewModel.builder()
                .review(entityToConvert.getReview())
                .userId(entityToConvert.getUser().getId())
                .establishmentId(entityToConvert.getEstablishment().getId())
                .build();
    }

    private static Review convertToEntity(ReviewModel modelToConvert){
        if (modelToConvert == null) return null;

        Review reviewToReturn = new Review();

        reviewToReturn.setReview(modelToConvert.getReview());

        User user = new User();
        user.setId(modelToConvert.getUserId());
        reviewToReturn.setUser(user);

        Establishment establishment = new Establishment();
        establishment.setId(modelToConvert.getEstablishmentId());
        reviewToReturn.setEstablishment(establishment);

        return reviewToReturn;
    }
}
