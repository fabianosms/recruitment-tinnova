package com.machado.fabiano.recruitmenttinnova.dto.form;

import com.machado.fabiano.recruitmenttinnova.model.Marca;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import com.machado.fabiano.recruitmenttinnova.repository.MarcaRepository;
import com.machado.fabiano.recruitmenttinnova.repository.VeiculoRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Objects;

public class Form {

    protected String veiculo;

    protected String marca;

    protected Integer ano;

    protected String descricao;

    protected String vendido;

    public Veiculo toVeiculo(MarcaRepository marcaRepository) {
        if (Objects.equals(vendido, "Sim")) {
            vendido = "true";
        } else {
            vendido = "false";
        }

        Marca novaMarca = marcaRepository.findByNome(marca);

        if (novaMarca == null) {
            throw new EntityNotFoundException("Nome de marca inválido");
        }

        return new Veiculo(veiculo, novaMarca, ano, descricao, Boolean.parseBoolean(vendido));
    }

    public Veiculo atualizar(Long id, VeiculoRepository veiculoRepository, MarcaRepository marcaRepository) {

        if (Objects.equals(vendido, "Sim")) {
            vendido = "true";
        } else {
            vendido = "false";
        }

        Marca novaMarca = marcaRepository.findByNome(marca);

        if (novaMarca == null) {
            throw new EntityNotFoundException("Nome de marca inválido");
        }

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