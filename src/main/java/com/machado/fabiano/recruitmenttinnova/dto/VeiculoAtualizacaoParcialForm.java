package com.machado.fabiano.recruitmenttinnova.dto;

import com.machado.fabiano.recruitmenttinnova.model.Marca;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import com.machado.fabiano.recruitmenttinnova.repository.VeiculoRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Objects;

public class VeiculoAtualizacaoParcialForm {

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

    public Veiculo atualizar(Long id, VeiculoRepository veiculoRepository) {

        if (Objects.equals(vendido, "Sim")) {
            vendido = "true";
        } else {
            vendido = "false";
        }

        Marca novaMarca = new Marca(marca);

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (this.veiculo != null) {
            veiculo.setVeiculo(this.veiculo);
        }

        if (this.ano != null) {
            veiculo.setAno(this.ano);
        }

        if (this.marca != null) {
            veiculo.setMarca(novaMarca);
        }

        if (this.descricao != null) {
            veiculo.setDescricao(this.descricao);
        }

        if (this.vendido != null) {
            veiculo.setVendido(Boolean.parseBoolean(this.vendido));
        }

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