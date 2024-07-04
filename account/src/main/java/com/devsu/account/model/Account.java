package com.devsu.account.model;

import com.devsu.account.enums.AccountStatus;
import com.devsu.account.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("numero")
    private String number;
    @Getter
    @JsonProperty("clienteId")
    private Long clientId;
    @Setter
    @JsonIgnore
    private String clientName;
    @JsonProperty("tipoCuenta")
    private AccountType accountType;
    @Setter
    @JsonIgnore
    private BigDecimal balance;
    @JsonProperty("saldoInicial")
    private BigDecimal initialBalance;
    @JsonProperty("estadoCuenta")
    private AccountStatus accountStatus;
    @Setter
    @OneToMany(mappedBy = "account")
    @JsonProperty("movimientos")
    @JsonManagedReference
    private List<Movement> movements;

    @PrePersist
    protected void prePersist() {
        if (this.balance == null) {
            this.balance = this.initialBalance;
        }
    }

    @JsonProperty("saldo")
    public BigDecimal getBalance() {
        return balance;
    }

    @JsonProperty("nombreCliente")
    public String getClientName() {
        return clientName;
    }
}
