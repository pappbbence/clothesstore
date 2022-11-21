package com.epam.clothes.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "Cloth")
public class Cloth {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "sex")
    private String sex;

    @Column(name = "season")
    private String season;

    @Column(name = "size")
    private char size;

    @Column(name = "colour")
    private String colour;

}
