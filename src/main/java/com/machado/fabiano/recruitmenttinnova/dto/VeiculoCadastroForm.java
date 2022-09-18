package com.machado.fabiano.recruitmenttinnova.dto;

import com.machado.fabiano.recruitmenttinnova.model.Veiculo;

import java.util.Objects;

public class VeiculoCadastroForm {

    private String veiculo;

    private String marca;

    private Integer ano;

    private String descricao;

    private String vendido;

    public Veiculo toVeiculo() {
        if (Objects.equals(vendido, "Sim")) {
            vendido = "true";
        } else {
            vendido = "false";
        }

        return new Veiculo(veiculo, marca, ano, descricao, Boolean.parseBoolean(vendido));
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
}