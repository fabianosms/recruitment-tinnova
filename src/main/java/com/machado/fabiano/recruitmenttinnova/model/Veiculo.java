package com.machado.fabiano.recruitmenttinnova.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String veiculo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Marca marca;

    private Integer ano;

    private String descricao;

    private Boolean vendido;

    private LocalDateTime created;

    private LocalDateTime updated;

    public Veiculo() {
    }

    public Veiculo(String veiculo, Marca marca, Integer ano, String descricao, Boolean vendido) {
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.vendido = vendido;
        this.created = LocalDateTime.now();
    }

//    public Veiculo(String veiculo, String marca, Integer ano, String descricao, Boolean vendido, LocalDateTime updated) {
//        this.veiculo = veiculo;
//        this.marca = marca;
//        this.ano = ano;
//        this.descricao = descricao;
//        this.vendido = vendido;
//        this.updated = LocalDateTime.now();
//    }

    public Long getId() {
        return id;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public Marca getMarca() {
        return marca;
    }

    public Integer getAno() {
        return ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean getVendido() {
        return vendido;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}