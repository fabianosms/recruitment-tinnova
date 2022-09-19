package com.machado.fabiano.recruitmenttinnova.service;

import com.machado.fabiano.recruitmenttinnova.dto.*;
import com.machado.fabiano.recruitmenttinnova.model.Marca;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import com.machado.fabiano.recruitmenttinnova.repository.MarcaRepository;
import com.machado.fabiano.recruitmenttinnova.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    /**
     * Método para listar todos os veículos
     * @return List<VeiculoCompletoDto>
     */
    public List<Veiculo> listarVeiculos () {
        return veiculoRepository.findAll();
    }

    /**
     * Método que retorna a descrição de um veículo pelo id
     * @param id
     * @return VeiculoCompletoDto
     */
    public VeiculoCompletoDto buscarUmVeiculo(Long id) {

        Veiculo veiculo = veiculoRepository.getReferenceById(id);
        return new VeiculoCompletoDto(veiculo);
    }

    public List<Veiculo> buscarVeiculosUltimaSemana() {
        return veiculoRepository.findByCreatedBetween(LocalDateTime.now().minusWeeks(1), LocalDateTime.now());
    }

    public Long contarVeiculosNaoVendidos() {
        return veiculoRepository.countByVendidoFalse();
    }

    public Long contarVeiculosPorDecada(@PathVariable Integer decada) {

        Integer inicio = decada;
        Integer fim = decada + 9;

        return veiculoRepository.countByAnoBetween(inicio, fim);
    }

    public Map<String, Long> contarVeiculosPorMarca() {

        List<Marca> listaMarcas = marcaRepository.findAll();
        HashMap<String, Long> map = new HashMap<>();

        for (Marca marca : listaMarcas) {
            String marcaNome = marca.getNome();
            if (veiculoRepository.countByMarca(marca) != 0) {
                map.put(marcaNome, veiculoRepository.countByMarca(marca));
            }
        }

        return map;
    }

    @Transactional
    public ResponseEntity<VeiculoCadastroDto> cadastrarVeiculo(@RequestBody VeiculoCadastroForm form) {

        Veiculo veiculo = form.toVeiculo(marcaRepository);
        veiculoRepository.save(veiculo);

        return ResponseEntity.status(HttpStatus.CREATED).body(new VeiculoCadastroDto(veiculo));
    }

    @Transactional
    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoAtualizacaoForm form) {

        Veiculo veiculo = form.atualizar(id, veiculoRepository, marcaRepository);

        return ResponseEntity.ok(new VeiculoCompletoDto(veiculo));
    }

    @Transactional
    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculoParte(@PathVariable Long id, @RequestBody VeiculoAtualizacaoParcialForm form) {

        Veiculo veiculo = form.atualizarParcial(id, veiculoRepository, marcaRepository);

        return ResponseEntity.ok(new VeiculoCompletoDto(veiculo));
    }

    @Transactional
    public ResponseEntity<?> removerVeiculo(@PathVariable Long id) {
        veiculoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}