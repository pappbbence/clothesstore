package com.epam.clothes.service.impl;

import com.epam.clothes.domain.Cloth;
import com.epam.clothes.dto.ClothModel;
import com.epam.clothes.exception.ClothNotFoundException;
import com.epam.clothes.repository.ClothRepository;
import com.epam.clothes.service.ClothService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClothServiceImpl implements ClothService {

    private final ClothRepository clothRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ClothModel> findAll() {
        return clothRepository.findAll()
                .stream()
                .map(cloth -> modelMapper.map(cloth, ClothModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClothModel> findById(long id) {
        return clothRepository.findById(id)
                .map(cloth -> modelMapper.map(cloth, ClothModel.class));
    }

    @Override
    public List<ClothModel> findByType(String type) {
        return clothRepository.findAllByType(type)
                .stream()
                .map(cloth -> modelMapper.map(cloth, ClothModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClothModel create(ClothModel clothModel) {
        clothModel.setId(null);

        Cloth clothToSave = modelMapper.map(clothModel, Cloth.class);
        Cloth savedCloth = clothRepository.save(clothToSave);

        return modelMapper.map(savedCloth, ClothModel.class);
    }

    @Override
    public void delete(long id) {
        Optional<Cloth> clothToDelete = clothRepository.findById(id);

        if (clothToDelete.isPresent()) {
            clothRepository.delete(clothToDelete.get());
        } else {
            throw new ClothNotFoundException(id);
        }
    }
}
