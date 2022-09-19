package com.machado.fabiano.recruitmenttinnova.controller;

import com.machado.fabiano.recruitmenttinnova.dto.*;
import com.machado.fabiano.recruitmenttinnova.model.Marca;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import com.machado.fabiano.recruitmenttinnova.repository.MarcaRepository;
import com.machado.fabiano.recruitmenttinnova.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/veiculos")
public class VeiculosController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping
    public List<Veiculo> listarVeiculos () {
        return veiculoRepository.findAll();
    }

    @GetMapping("/{id}")
    public VeiculoCompletoDto buscarUmVeiculo(@PathVariable Long id) {

        Veiculo veiculo = veiculoRepository.getReferenceById(id);
        return new VeiculoCompletoDto(veiculo);
    }

    @GetMapping("/ultimasemana")
    public List<Veiculo> buscarVeiculosUltimaSemana() {
        return veiculoRepository.findByCreatedBetween(LocalDateTime.now().minusWeeks(1), LocalDateTime.now());
    }

    @GetMapping("/naovendidos")
    public Long contarVeiculosNaoVendidos() {
        return veiculoRepository.countByVendidoFalse();
    }

    @GetMapping("/decada/{decada}")
    public Long contarVeiculosPorDecada(@PathVariable Integer decada) {

        Integer inicio = decada;
        Integer fim = decada + 9;

        return veiculoRepository.countByAnoBetween(inicio, fim);
    }

    @GetMapping("/marcas")
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

    @PostMapping
    public ResponseEntity<VeiculoCadastroDto> cadastrarVeiculo(@RequestBody VeiculoCadastroForm form) {

        Veiculo veiculo = form.toVeiculo(marcaRepository);
        veiculoRepository.save(veiculo);

        return ResponseEntity.status(HttpStatus.CREATED).body(new VeiculoCadastroDto(veiculo));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoAtualizacaoForm form) {

        Veiculo veiculo = form.atualizar(id, veiculoRepository, marcaRepository);

        return ResponseEntity.ok(new VeiculoCompletoDto(veiculo));
    }

    @PatchMapping ("/{id}")
    @Transactional
    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculoParte(@PathVariable Long id, @RequestBody VeiculoAtualizacaoParcialForm form) {

        Veiculo veiculo = form.atualizarParcial(id, veiculoRepository, marcaRepository);

        return ResponseEntity.ok(new VeiculoCompletoDto(veiculo));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> removerVeiculo(@PathVariable Long id) {
        veiculoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
