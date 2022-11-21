package com.epam.clothes.service;

import com.epam.clothes.dto.ClothModel;

import java.util.List;
import java.util.Optional;

public interface ClothService {

    List<ClothModel> findAll();
    Optional<ClothModel> findById(long id);
    List<ClothModel> findByType(String type);
    ClothModel create(ClothModel clothModel);
    void delete(long id);
}
