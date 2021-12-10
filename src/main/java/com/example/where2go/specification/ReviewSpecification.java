package com.example.where2go.specification;

import com.example.where2go.entity.Establishment;
import com.example.where2go.entity.Review;
import com.example.where2go.model.EstablishmentModel;
import com.example.where2go.model.ReviewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ReviewSpecification implements Specification<Review> {

    private final ReviewModel reviewModel;

    @Override
    public Predicate toPredicate(Root<Review> reviewRoot, CriteriaQuery<?> reviewQuery, CriteriaBuilder reviewCriteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (reviewModel.getReview() != null) {
            predicates.add(reviewCriteriaBuilder.like(reviewCriteriaBuilder.lower(reviewRoot.get("review")), "%" + reviewModel.getReview() + "%"));
        }

        if (reviewModel.getEstablishmentId() != null) {
            predicates.add(reviewCriteriaBuilder.like(reviewCriteriaBuilder.lower(reviewRoot.get("establishment_id")), "%" + reviewModel.getEstablishmentId() + "%"));
        }

        if (reviewModel.getUserId() != null) {
            predicates.add(reviewCriteriaBuilder.like(reviewCriteriaBuilder.lower(reviewRoot.get("user_id")), "%" + reviewModel.getUserId() + "%"));
        }

        return reviewCriteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
