package com.epam.clothes.controller;

import com.epam.clothes.dto.ClothModel;
import com.epam.clothes.exception.InvalidClothRequestException;
import com.epam.clothes.service.ClothService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/clothes")
@RequiredArgsConstructor
public class ClothController {

    private final ClothService clothService;

    @GetMapping
    public ResponseEntity<List<ClothModel>> findAll() {
        List<ClothModel> clothModels = clothService.findAll();

        return ResponseEntity.ok().body(clothModels);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClothModel> findById(@PathVariable Long id) {
        Optional<ClothModel> clothModel = clothService.findById(id);

        return clothModel.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/type/{type}")
    public ResponseEntity<List<ClothModel>> findByType(@PathVariable String type) {
        List<ClothModel> clothModels = clothService.findByType(type);

        return ResponseEntity.ok().body(clothModels);
    }

    @PostMapping
    public ResponseEntity<ClothModel> create(@Validated @RequestBody ClothModel clothModel, BindingResult bindingResult) {
        checkForRequestErrors(bindingResult);

        ClothModel savedCloth = clothService.create(clothModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCloth);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clothService.delete(id);

        return ResponseEntity.noContent().build();
    }

    private void checkForRequestErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> messages = bindingResult.getFieldErrors()
                    .stream().map(this::fieldErrorToMessage)
                    .collect(Collectors.toList());

            throw new InvalidClothRequestException("Invalid product request", messages);
        }
    }

    private String fieldErrorToMessage(FieldError fieldError) {
        return fieldError.getField() + " - " + fieldError.getDefaultMessage();
    }

}
