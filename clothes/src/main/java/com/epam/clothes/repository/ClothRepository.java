package com.epam.clothes.repository;

import com.epam.clothes.domain.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClothRepository extends JpaRepository<Cloth, Long> {

    List<Cloth> findAllByType(String type);
}
