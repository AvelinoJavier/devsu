package com.devsu.person.model;

import com.devsu.person.enums.ClientStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Person {
    @JsonProperty("contrasena")
    private String password;
    @JsonProperty("estado")
    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus;
}
