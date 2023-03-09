package com.banc.bancwebapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CompteEntity {

    @Id
    private String iban;
    private long saldo;
    @ManyToOne()
    private ClientEntity compteClientEntity;
}
