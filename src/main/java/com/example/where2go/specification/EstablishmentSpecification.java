package com.example.where2go.specification;

import com.example.where2go.entity.Establishment;
import com.example.where2go.model.EstablishmentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class EstablishmentSpecification implements Specification<Establishment> {

    private final EstablishmentModel establishmentModel;

    @Override
    public Predicate toPredicate(Root<Establishment> establishmentRoot, CriteriaQuery<?> establishmentQuery, CriteriaBuilder establishmentCriteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (establishmentModel.getName() != null) {
            predicates.add(establishmentCriteriaBuilder.like(establishmentCriteriaBuilder.lower(establishmentRoot.get("name")), "%" + establishmentModel.getName() + "%"));
        }


        if (establishmentModel.getAddress() != null) {
            predicates.add(establishmentCriteriaBuilder.like(establishmentCriteriaBuilder.lower(establishmentRoot.get("address")), "%" + establishmentModel.getAddress() + "%"));
        }

        if (establishmentModel.getWorkSchedule() != null) {
            predicates.add(establishmentCriteriaBuilder.like(establishmentCriteriaBuilder.lower(establishmentRoot.get("work_schedule")), "%" + establishmentModel.getWorkSchedule() + "%"));
        }

        if (establishmentModel.getCategoryId() != null) {
            predicates.add(establishmentCriteriaBuilder.like(establishmentCriteriaBuilder.lower(establishmentRoot.get("category_id")), "%" + establishmentModel.getCategoryId() + "%"));
        }

        return establishmentCriteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


}
