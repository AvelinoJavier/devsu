package com.devsu.account.model;

import com.devsu.account.enums.MovementType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "movements")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    private Date date;
    @Getter
    @JsonProperty("tipoMovimiento")
    private MovementType movementType;
    @Getter
    @JsonProperty("valor")
    private BigDecimal value;
    @Getter
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonProperty("cuenta")
    @JsonBackReference
    private Account account;

    @PrePersist
    protected void onCreate() {
        this.date = new Date();
    }
}
