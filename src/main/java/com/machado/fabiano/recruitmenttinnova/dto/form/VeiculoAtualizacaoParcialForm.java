package com.machado.fabiano.recruitmenttinnova.dto.form;

import com.machado.fabiano.recruitmenttinnova.model.Marca;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import com.machado.fabiano.recruitmenttinnova.repository.MarcaRepository;
import com.machado.fabiano.recruitmenttinnova.repository.VeiculoRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Objects;

public class VeiculoAtualizacaoParcialForm extends Form {

    @Override
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
}