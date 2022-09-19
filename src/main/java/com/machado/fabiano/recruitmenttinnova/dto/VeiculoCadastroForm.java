package com.machado.fabiano.recruitmenttinnova.dto;

import com.machado.fabiano.recruitmenttinnova.model.Marca;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import com.machado.fabiano.recruitmenttinnova.repository.MarcaRepository;

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

        Marca novaMarca = new Marca(marca);

        return new Veiculo(veiculo, novaMarca, ano, descricao, Boolean.parseBoolean(vendido));
    }

    public Marca toMarca() {
        return new Marca(marca);
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