package com.devsu.person.model;

import com.devsu.person.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("nombre")
    private String name;
    @JsonProperty("genero")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @JsonProperty("edad")
    private int age;
    @JsonProperty("identificacion")
    private String identification;
    @JsonProperty("direccion")
    private String address;
    @JsonProperty("telefono")
    private String phone;
}
