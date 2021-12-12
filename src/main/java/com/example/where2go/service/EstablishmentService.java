package com.example.where2go.service;

import com.example.where2go.exceptions.ApiException;
import com.example.where2go.model.EstablishmentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EstablishmentService {
    EstablishmentModel createEstablishment(EstablishmentModel establishmentModel);
    EstablishmentModel getById(Long id);
    EstablishmentModel updateEstablishment(EstablishmentModel establishmentModel);
    EstablishmentModel deleteById(Long id);
    Page<EstablishmentModel> getPage(Pageable pageable);
    Page<EstablishmentModel> getPageSortedByCategory(Long id, Pageable pageable);
    Page<EstablishmentModel> getSortedPage(EstablishmentModel establishmentModel, Pageable pageable);
    ApiException saveImages(List<MultipartFile> images, Long establishmentId);
}
