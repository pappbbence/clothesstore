package com.epam.clothes.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ClothModel {
    private Long id;
    private String sex;
    private String season;
    private char size;
    private String colour;
}
