package com.machado.fabiano.recruitmenttinnova.dto;

import com.machado.fabiano.recruitmenttinnova.model.Veiculo;

import java.time.LocalDateTime;

public class VeiculoCompletoDto {

    private String veiculo;

    private String marca;

    private Integer ano;

    private String descricao;

    private String vendido;

    private LocalDateTime created;

    private LocalDateTime updated;

    public VeiculoCompletoDto(Veiculo veiculo) {

        if (veiculo.getVendido()) {
            this.vendido = "Sim";
        } else {
            this.vendido = "Não";
        }

        this.veiculo = veiculo.getVeiculo();
        this.marca = veiculo.getMarca();
        this.ano = veiculo.getAno();
        this.descricao = veiculo.getDescricao();
        this.created = veiculo.getCreated();
        this.updated = veiculo.getUpdated();
    }

    public String getVeiculo() {
        return veiculo;
    }

    public String getMarca() {
        return marca;
    }

    public Integer getAno() {
        return ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getVendido() {
        return vendido;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }
}