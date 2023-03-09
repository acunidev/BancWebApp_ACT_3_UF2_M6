package com.banc.bancwebapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClientEntity {

    @OneToMany(mappedBy = "compteClientEntity")
    List<CompteEntity> comptes;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long idClient;
    private String nom;
    private String DNI;
    private String pais;
    private String email;

    public ClientEntity(String nom, String DNI, String pais, String email) {
        this.nom = nom;
        this.DNI = DNI;
        this.pais = pais;
        this.email = email;
    }
}
