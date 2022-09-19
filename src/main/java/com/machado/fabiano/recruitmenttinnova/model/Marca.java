package com.machado.fabiano.recruitmenttinnova.model;

import javax.persistence.*;

@Entity
@Table(name = "marcas")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Marca() {
    }

    public Marca(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
