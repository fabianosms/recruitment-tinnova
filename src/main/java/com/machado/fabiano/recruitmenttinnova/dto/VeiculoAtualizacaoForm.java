package com.machado.fabiano.recruitmenttinnova.dto;

import com.machado.fabiano.recruitmenttinnova.model.Marca;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import com.machado.fabiano.recruitmenttinnova.repository.VeiculoRepository;

import java.time.LocalDateTime;
import java.util.Objects;

public class VeiculoAtualizacaoForm {

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

    public Veiculo atualizar(Long id, VeiculoRepository veiculoRepository) {

        if (Objects.equals(vendido, "Sim")) {
            vendido = "true";
        } else {
            vendido = "false";
        }

        Marca novaMarca = new Marca(marca);

        Veiculo veiculo = veiculoRepository.getReferenceById(id);
        veiculo.setVeiculo(this.veiculo);
        veiculo.setMarca(novaMarca);
        veiculo.setAno(this.ano);
        veiculo.setDescricao(this.descricao);
        veiculo.setVendido(Boolean.parseBoolean(this.vendido));
        veiculo.setUpdated(LocalDateTime.now());

        return veiculo;
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